class Intersection extends Sugar {
public static final int LEFT = 0, RIGHT = 1;
private int[] waiting = {0, 0};
private int lastToCross = 0;
private boolean crossing = false;
public String how(int direction) {
if (direction == LEFT) return "left";
else if (direction == RIGHT) return "right";
else return "invalid";
}
public void crossIntersection
(String name, int direction, int cNap)
throws InterruptedException {
wantToCross(direction);
try {
cross(name, direction, cNap);
} finally {
// If we are interrupted while crossing, we must do this.
doneCrossing();
}
}
private int other(int direction) {
if (direction == LEFT) return RIGHT;
else if (direction == RIGHT) return LEFT;
else return -1;
}
private synchronized void wantToCross (int direction)
throws InterruptedException {
waiting[direction]++;
try {
while (crossing || (waiting[other(direction)] > 0
&& lastToCross == direction))
wait();
lastToCross = direction;
crossing = true;
} finally {
// If we are interrupted while
waiting[direction]--; // waiting to cross, do this.
}
}
private void cross(String name, int direction, int cNap)
throws InterruptedException {
int napping;
napping = 1 + (int) random(cNap);
System.out.println("age=" + age() + ", " + name
+ " CROSSING " + how(direction) + " for "
+ napping + " ms");
Thread.sleep(napping);
}
private synchronized void doneCrossing () {
crossing = false;
notifyAll();
}
}