import java.util.Vector;
public final class MessagePassing {
// Implements asynchronous message passing:
// sends do not block (until the message is
// received), receives block of course until
// a message is received.
private int capacity = 0; // for capacity control
// messages are delivered FIFO (in the order they are sent)
private final Vector messages = new Vector();

// receivers get messages FIFO (in the order they call receive)
private final Vector receivers = new Vector();
public MessagePassing() { this(0); }
public MessagePassing(int c) { // capacity limit
super();
if (c < 0) throw new IllegalArgumentException("capacity < 0");
// zero means no limit imposed here
else if (c > 0) {
capacity = c;
messages.ensureCapacity(capacity);
}
}
public final synchronized void send(Object m)
throws InterruptedException {
if (m == null) throw new NullPointerException("null message");
if (capacity > 0)
while (messages.size() == capacity) wait();
messages.addElement(m); // add at end
notifyAll();
}
public final synchronized Object receive()
throws InterruptedException {
Object receivedMessage = null;
Thread me = Thread.currentThread();
receivers.addElement(me); // add at end
try {
while (messages.isEmpty() || me != receivers.elementAt(0))
wait();
// If we are interrupted after being notified and there is a
// message here for us, pretend we were interrupted before
// being notified and leave the message for someone else.
// Thus, there is no `catch (InterruptedException e) {...}'
// block here.
receivedMessage = messages.elementAt(0);
messages.removeElementAt(0);
return receivedMessage;
} finally {
// We need to do this if we get a message or if we were
// interrupted.
receivers.removeElement(me);
// The notifyAll is needed because several messages
// might be put in the messages vector before any
// waiting receivers get back in. The receiver who
// is first in the receivers vector might not get back
// in until last! So it needs to cause the waiting
// receivers to come back in again so the second in
// line can get a message.
notifyAll();
}
}
}