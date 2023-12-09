class DiningServer extends SugarSM {
private int numPhils = 0;
private int[] state = null;
private static final int THINKING = 0, HUNGRY = 1, EATING = 2;
private BinarySemaphore[] self = null;
private BinarySemaphore mutex = null;
public DiningServer(int numPhils) {
this.numPhils = numPhils;
state = new int[numPhils];
for (int i = 0; i < numPhils; i++) state[i] = THINKING;
self = new BinarySemaphore[numPhils];
for (int i = 0; i < numPhils; i++)
self[i] = new BinarySemaphore(0);
mutex = new BinarySemaphore(1);
}
public void dine(String name, int id, int napEat)
throws InterruptedException {
try {
takeForks(id);
eat(name, napEat);
} finally {
// Make sure we return the
putForks(id); // forks if interrupted
}
}
private final int left(int i)
{ return (numPhils + i - 1) % numPhils;}
private final int right(int i)
{ return (i + 1) % numPhils; }
private void test(int k) {
if (state[left(k)] != EATING && state[k] == HUNGRY &&
state[right(k)] != EATING) {
state[k] = EATING;
V(self[k]);
}
}
private void eat(String name, int napEat)
throws InterruptedException {
int napping;
napping = 1 + (int) random(napEat);
System.out.println("age=" + age() + ", " + name
+ " is eating for " + napping + " ms");
Thread.sleep(napping);
}
private void takeForks(int i)
throws InterruptedException {
P(mutex); state[i] = HUNGRY; test(i); V(mutex);
P(self[i]);
}
private void putForks(int i)
throws InterruptedException {
if (state[i] != EATING) return;
P(mutex);
state[i] = THINKING; test(left(i)); test(right(i));
V(mutex);
}
}