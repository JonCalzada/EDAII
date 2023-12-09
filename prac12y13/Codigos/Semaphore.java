public abstract class Semaphore {
private int value = 0;
public Semaphore() {} // constructors
public Semaphore(int initial) {
if (initial >= 0) value = initial;
else throw new IllegalArgumentException("initial < 0");
}
public final synchronized void P()
throws InterruptedException {
while (value == 0) wait();
value--;
}
protected final synchronized void Vc() {
value++; notifyAll();
}
protected final synchronized void Vb() {
this.Vc(); if (value > 1) value = 1;
}
public abstract void V();
public String toString() { return ".value=" + value; }
}