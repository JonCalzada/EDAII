public class BadCountingSemaphore2 {
private int value = 0;
public BadCountingSemaphore2(int initial)
{ if (initial > 0) value = initial; }
public synchronized void P()
throws InterruptedException {
value--;
if (value < 0) wait();
}
public synchronized void V() {
value++;
if (value <= 0) notify(); // interrupt causes problems
}
}