class BoundedBuffer {
	// designed for multiple producer threads and
// multiple consumer threads
private int numSlots = 0;
private double[] buffer = null;
private int putIn = 0, takeOut = 0;
private int count = 0;
public BoundedBuffer(int numSlots) {
if (numSlots <= 0)
throw new IllegalArgumentException("numSlots <= 0");
this.numSlots = numSlots;
buffer = new double[numSlots];
}
public synchronized void deposit(double value)
throws InterruptedException {
while (count == numSlots) wait();
buffer[putIn] = value;
putIn = (putIn + 1) % numSlots;
count++;
// wake up all those waiting due to
notifyAll(); // signal-and-continue and barging
}
public synchronized double fetch()
throws InterruptedException {
double value;
while (count == 0) wait();
value = buffer[takeOut];
takeOut = (takeOut + 1) % numSlots;
count--;
// wake up all those waiting due to
notifyAll(); // signal-and-continue and barging
return value;
}
}
