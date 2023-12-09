class BufferItem
{
// multiple threads access so make these 'volatile'
    public volatile double value = 0;
    public volatile boolean occupied = false;
}
class BoundedBuffer
{
// designed for a single producer thread and
// a single consumer thread
    private int numSlots = 0;
    private BufferItem[] buffer = null;
    private int putIn = 0, takeOut = 0;
    public BoundedBuffer(int numSlots)
    {
        if (numSlots <= 0)
            throw new IllegalArgumentException("numSlots <= 0");
        this.numSlots = numSlots;
        buffer = new BufferItem[numSlots];
        for (int i = 0; i < numSlots; i++)
            buffer[i] = new BufferItem();
    }
    public void deposit(double value)
    throws InterruptedException
    {
        while (buffer[putIn].occupied) // busy wait
            Thread.currentThread().yield();
        buffer[putIn].value = value;
        buffer[putIn].occupied = true;
        putIn = (putIn + 1) % numSlots;
    }
    public double fetch()
    throws InterruptedException
    {
        double value;
        while (!buffer[takeOut].occupied) // busy wait
            Thread.currentThread().yield();
        value = buffer[takeOut].value;
        buffer[takeOut].occupied = false;
        takeOut = (takeOut + 1) % numSlots;
        return value;
    }
}
