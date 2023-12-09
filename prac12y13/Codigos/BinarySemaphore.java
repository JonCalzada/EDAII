public final class BinarySemaphore extends Semaphore {
public BinarySemaphore() { super(); } // constructors
public BinarySemaphore(int initial) {
super(initial);
if (initial > 1)
throw new IllegalArgumentException("initial > 1");
}
public final synchronized void V() { super.Vb(); }
}