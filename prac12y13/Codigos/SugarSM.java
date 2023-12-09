public abstract class SugarSM extends Sugar {
// syntactic sugar for semaphores
protected static final void P(Semaphore s)
throws InterruptedException { s.P(); }
protected static final void V(Semaphore s) { s.V(); }
}