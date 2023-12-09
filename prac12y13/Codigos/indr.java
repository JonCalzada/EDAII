class Car extends Sugar implements Runnable {
private String name = null;
private int dNap = 0; // milliseconds
private int cNap = 0; // milliseconds
private int direction;
private Intersection in = null;
private Thread me = null;
public Car(String name, int dNap, int cNap,
int direction, Intersection in) {
this.name = name;
this.dNap = dNap;
this.cNap = cNap;
this.direction = direction;
this.in = in;
(me = new Thread(this)).start();
}
public void timeToQuit() { me.interrupt(); }
public void pauseTilDone() throws InterruptedException
{ me.join(); }
public void run() {
if (Thread.currentThread() != me) return;
int napping;
while (true) {
if (Thread.interrupted()) {
System.out.println("age=" + age() + ", " + name
+ " interrupted");
return;
}
napping = 1 + (int) random(dNap);
System.out.println("age=" + age() + ", " + name
+ " napping for " + napping + " ms");
try { Thread.sleep(napping); }
catch (InterruptedException e) {
System.out.println("age=" + age() + ", " + name
+ " interrupted from sleep");
return;
}
System.out.println("age=" + age() + ", " + name
+ " wants to cross " + in.how(direction));
try { in.crossIntersection(name, direction, cNap); }
catch (InterruptedException e) {
System.out.println("age=" + age() + ", " + name
+ " interrupted from crossing");
return;
}
System.out.println("age=" + age() + ", " + name
+ " crossed " + in.how(direction));
}
}
}
class LeftRightCars extends Sugar {
public static void main(String[] args) {
int numLefts = 3;
int numRights = 3;
int lNap = 2;
// defaults
int rNap = 2;
// are
int cNap = 2;
// in
int runTime = 60; // seconds
try {
numLefts = Integer.parseInt(args[0]);
numRights = Integer.parseInt(args[1]);
lNap = Integer.parseInt(args[2]);
rNap = Integer.parseInt(args[3]);
cNap = Integer.parseInt(args[4]);
runTime = Integer.parseInt(args[5]);
} catch (Exception e) { /* use defaults */ }
System.out.println("LeftsRights:\n numLefts=" + numLefts
+ ", numRights=" + numRights + ", lNap=" + lNap
+ ", rNap=" + rNap + ", cNap=" + cNap
+ ", runTime=" + runTime);
// create the intersection
Intersection in = new Intersection();
// start the left crossing and right crossing
// cars (they have self-starting threads)
Car[] c = new Car[numLefts + numRights];
for (int i = 0; i < numLefts + numRights; i++)
c[i] = new Car("Car"+i, (i<numLefts?lNap:rNap)*1000,
cNap*1000, (i<numLefts?in.LEFT:in.RIGHT), in);
System.out.println("All threads started");
// let them run for a while
try {
Thread.sleep(runTime*1000);
System.out.println("age=" + age()
+ ", time to terminate the threads and exit");
for (int i = 0; i < numLefts + numRights; i++)
c[i].timeToQuit();
Thread.sleep(1000);
for (int i = 0; i < numLefts + numRights; i++)
c[i].pauseTilDone();
} catch (InterruptedException e) { /* ignored */ }
System.out.println("age=" + age()
+ ", all threads are done");
System.exit(0);
}
}