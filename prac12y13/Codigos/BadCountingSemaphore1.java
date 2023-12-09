public class BadCountingSemaphore1 {
private int value = 0;
public BadCountingSemaphore1(int initial)
{ if (initial > 0) value = initial; }
public synchronized void P()
throws InterruptedException {
	while (value == 0) wait();
value--;
}
public synchronized void V() {
if (value == 0) notify(); // barging causes problems
value++;
}
}