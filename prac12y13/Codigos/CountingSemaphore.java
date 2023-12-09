public final class CountingSemaphore extends Semaphore {
public CountingSemaphore() { super(); } // constructors
public CountingSemaphore(int initial) { super(initial); }
public final synchronized void V() { super.Vc(); }
}