class ParallelPrimes implements Runnable {
private static int n1, n2, nChecked, nThreads, next;
private static boolean[] taken, isPrime;
private Object mutex = this; // or = new Object();
public void run() {
int mine = 0;
while (true) {
synchronized (mutex) {
while (next < nChecked && taken[next]) next++;
mine = next;
if (mine >= nChecked) return;
taken[mine] = true;
}
if (Prime.prime(n1 + mine)) isPrime[mine] = true;
}
}
public static void main(String[] args) {
try {
n1 = Integer.parseInt(args[0]);
n2 = Integer.parseInt(args[1]);
nThreads = Integer.parseInt(args[2]);
} catch (NumberFormatException e) {
System.out.println("improper format");
System.exit(1);
} catch (ArrayIndexOutOfBoundsException e) {
System.out.println("not enough command line arguments");
System.exit(1);
}
System.out.println("printing primes from " + n1 + " to "
+ n2 + " using " + nThreads + " threads");
nChecked = n2 - n1 + 1;
if (nChecked < 1 || nThreads > nChecked) {
System.out.println("bad command line arguments");
System.exit(1);
}
taken = new boolean[nChecked];
isPrime = new boolean[nChecked];
for (int i = 0; i < nChecked; i++)
taken[i] = isPrime[i] = false;
next = 0;
Thread[] t = new Thread[nThreads];
// All threads execute inside the SAME object and thus
// SHARE all data.
Runnable a = new ParallelPrimes();
for (int i = 0; i < nThreads; i++) t[i] = new Thread(a);
//new PseudoTimeSlicing(); // for Solaris, not Windows 95/NT
for (int i = 0; i < nThreads; i++) t[i].start();
try {
for (int i = 0; i < nThreads; i++) t[i].join();
} catch (InterruptedException e) { /* ignored */ }
for (int i = 0; i < nChecked; i++)
if (isPrime[i])
System.out.println((n1 + i) + " is prime");
}
}
