class Filter extends SugarMP implements Runnable {
private MessagePassing in = null, out = null;
private int prime = 0, countIn = 0, countOut = 0;
public Filter(MessagePassing in, MessagePassing out) {
this.in = in; this.out = out;
}
private void print() {
System.out.println("age()=" + age() + " received prime " + prime
+ ", countIn=" + countIn + ", countOut=" + countOut);
}
public void run () {
if (in == null) { // source thread
int number = 3;
while (true) {
try { send(out, new Integer(number)); }
catch (InterruptedException e) { return; }
number += 2;
}
} else {
// filter threads
int number = 0;
try { prime = ((Integer) receive(in)).intValue(); }
catch (InterruptedException e) { return; }
while (true) {
if (Thread.interrupted()) { print(); return; }
try {
number = ((Integer) receive(in)).intValue(); countIn++;
if (number % prime != 0) {
send(out, new Integer(number)); countOut++;
}
} catch (InterruptedException e) { print(); return; }
}
}
}
}
class ParallelSieve extends SugarMP {
public static void main(String[] args) {
int n = 8;
try { n = Integer.parseInt(args[0]); }
catch (Exception e) { /* use default */ }
if (n < 1) {
System.out.println("Generate at least one prime number.");
System.exit(1);
}
System.out.println("ParallelSieve: generating the first "
+ n + " prime numbers greater than 2");
MessagePassing in = null, out = null;
Thread[] filter = new Thread[n];
for (int i = 0; i < n; i++) {
// Use capacity control so the early threads do
// not get way ahead of what is needed by the
// latter threads and fill up JVM memory.
out = new MessagePassing(n);
filter[i] = new Thread(new Filter(in, out));
in = out;
}
//new PseudoTimeSlicing(); // for Solaris, not Windows 95/NT
for (int i = 0; i < n; i++) filter[i].start();
try {
int prime = ((Integer) receive(out)).intValue();
for (int i = 0; i < n; i++) filter[i].interrupt();
for (int i = 0; i < n; i++) filter[i].join();
System.out.println("age()=" + age() + " lastprime " + prime);
} catch (InterruptedException e) { /* ignored */ }
System.exit(0);
}
}

