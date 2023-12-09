class BoundedBuffer extends SugarSM {
// designed for a single producer thread and
// a single consumer thread
private int numSlots = 0;
private double[] buffer = null;
private int putIn = 0, takeOut = 0;
private int count = 0;
private BinarySemaphore mutex = null;
private CountingSemaphore elements = null;
private CountingSemaphore spaces = null;
public BoundedBuffer(int numSlots) {
if (numSlots <= 0)
throw new IllegalArgumentException("numSlots <= 0");
this.numSlots = numSlots;
buffer = new double[numSlots];
mutex = new BinarySemaphore(1);
elements = new CountingSemaphore(0);
spaces = new CountingSemaphore(numSlots);
}
public void deposit(double value)
throws InterruptedException {
P(spaces);
buffer[putIn] = value;
putIn = (putIn + 1) % numSlots;
P(mutex); count++; V(mutex);
V(elements);
}
public double fetch()
throws InterruptedException {
double value;
P(elements);
value = buffer[takeOut];
takeOut = (takeOut + 1) % numSlots;
P(mutex); count--; V(mutex);
V(spaces);
return value;
}
}