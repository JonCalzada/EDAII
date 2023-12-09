public abstract class SugarMP extends Sugar {
// syntactic sugar for message passing
protected static final void send(MessagePassing mp, Object o)
throws InterruptedException { mp.send(o); }
protected static final Object receive(MessagePassing mp)
throws InterruptedException { return mp.receive(); }
}