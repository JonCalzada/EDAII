class BufferItem {
public volatile double value = 0;
public volatile boolean occupied = false;
public volatile Thread thread = null;
}
class BoundedBuffer {
// designed for a single producer thread and
// a single consumer thread
private int numSlots = 0;
private BufferItem[] buffer = null;
private int putIn = 0, takeOut = 0;
public BoundedBuffer(int numSlots) {
if (numSlots <= 0)
throw new IllegalArgumentException("numSlots <= 0");
this.numSlots = numSlots;
buffer = new BufferItem[numSlots];
for (int i = 0; i < numSlots; i++)
buffer[i] = new BufferItem();
}
public void deposit(double value)
throws InterruptedException {
if (buffer[putIn].occupied) {
Thread producer = Thread.currentThread();
buffer[putIn].thread = producer;
// context switch possible here
producer.suspend();
buffer[putIn].thread = null;
}
buffer[putIn].value = value;
buffer[putIn].occupied = true;
Thread consumer = buffer[putIn].thread;
putIn = (putIn + 1) % numSlots;
if (consumer != null) consumer.resume();
}
public double fetch()
throws InterruptedException {
double value;
if (!buffer[takeOut].occupied) {
Thread consumer = Thread.currentThread();
buffer[takeOut].thread = consumer;
// context switch possible here
consumer.suspend();
buffer[takeOut].thread = null;
}
value = buffer[takeOut].value;
buffer[takeOut].occupied = false;
Thread producer = buffer[takeOut].thread;
takeOut = (takeOut + 1) % numSlots;
if (producer != null) producer.resume();
return value;
}
}