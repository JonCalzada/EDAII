class Philosopher extends Sugar implements Runnable {
private String name = null;
private int id = 0;
private int napThink = 0; // both are in
private int napEat = 0; // milliseconds
private DiningServer ds = null;
private Thread me = null;
public Philosopher(int id, int napThink, int napEat,
DiningServer ds) {
this.name = "Philosopher " + id;
this.id = id;
this.napThink = napThink;
this.napEat = napEat;
this.ds = ds;
(me = new Thread(this)).start();
}
public void timeToQuit() { me.interrupt(); }
public void pauseTilDone() throws InterruptedException
{ me.join(); }
private void think() throws InterruptedException {
int napping;
napping = 1 + (int) random(napThink);
System.out.println("age=" + age() + ", " + name
+ " is thinking for " + napping + " ms");
Thread.sleep(napping);

}
public void run() {
if (Thread.currentThread() != me) return;
while (true) {
if (Thread.interrupted()) {
System.out.println("age=" + age() + ", " + name
+ " interrupted");
return;
}
try {
think();
} catch (InterruptedException e) {
System.out.println("age=" + age() + ", " + name
+ " interrupted out of think");
return;
}
System.out.println("age=" + age() + ", " + name
+ " wants to dine");
try {
ds.dine(name, id, napEat);
} catch (InterruptedException e) {
System.out.println("age=" + age() + ", " + name
+ " interrupted out of dine");
return;
}
}
}
}
class DiningPhilosophers extends Sugar {
public static void main(String[] args) {
int numPhilosophers = 5;
int runTime = 60;
// seconds
int napThink = 8, napEat = 2;
try {
numPhilosophers = Integer.parseInt(args[0]);
runTime = Integer.parseInt(args[1]);
napThink = Integer.parseInt(args[2]);
napEat = Integer.parseInt(args[3]);
} catch (Exception e) { /* use defaults */ }
System.out.println("DiningPhilosophers: numPhilosophers="
+ numPhilosophers + ", runTime=" + runTime
+ ", napThink=" + napThink + ", napEat=" + napEat);
// create the DiningServer object
DiningServer ds = new DiningServer(numPhilosophers);
// create the Philosophers
// (they have self-starting threads)
Philosopher[] p = new Philosopher[numPhilosophers];
for (int i = 0; i < numPhilosophers; i++) p[i] =
new Philosopher(i, napThink*1000, napEat*1000, ds);
System.out.println("All Philosopher threads started");
// let the Philosophers run for a while
try {
Thread.sleep(runTime*1000);
System.out.println("age=" + age()
+ ", time to terminate the Philosophers and exit");
for (int i = 0; i < numPhilosophers; i++)
p[i].timeToQuit();
Thread.sleep(1000);
for (int i = 0; i < numPhilosophers; i++)
p[i].pauseTilDone();
} catch (InterruptedException e) { /* ignored */ }
System.out.println("age=" + age()
+ ", all Philosophers are done");
System.exit(0);
}
}