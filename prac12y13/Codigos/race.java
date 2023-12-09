class Racer implements Runnable {
// these two fields are shared by both threads since
// there is only ONE object created from this class
private int M = 0;
private volatile long sum = 0; // note `volatile'
public Racer(int M) { this.M = M; }
private long fn(long j, int k) {
long total = j;
for (int i = 1; i <= k; i++) total += i;
return total;
}
public void run() {
for (int m = 1; m <= M; m++) sum = fn(sum, m);
System.out.println("sum = " + sum);
}
}
class Racing {
	public static void main(String[] args) {
Racer racerObject = new Racer(2000);
Thread racerThread1 = new Thread(racerObject);
Thread racerThread2 = new Thread(racerObject);
//new PseudoTimeSlicing(); // for Solaris, not Windows 95/NT
racerThread1.start(); racerThread2.start();
try { racerThread1.join(); racerThread2.join(); }
catch (InterruptedException e) { /* ignored */ }
}
}