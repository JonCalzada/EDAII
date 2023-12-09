class Racer implements Runnable {
// these two fields are shared by both threads since
// there is only ONE object created from this class
private int M = 0;
private long sum = 0; // `volatile' no longer needed
public Racer(int M) { this.M = M; }
private long fn(long j, int k) {
long total = j;
for (int i = 1; i <= k; i++) total += i;
return total;
}
public void run() {
for (int m = 1; m <= M; m++)
synchronized (this) { // entry protocol
sum = fn(sum, m); // critical section
}
// exit protocol
System.out.println("sum = " + sum);
}
}