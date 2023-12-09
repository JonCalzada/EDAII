class DiningServer extends Sugar {
private int numPhils = 0;
private int[] state = null;
private static final int THINKING = 0, HUNGRY = 1, EATING = 2;
public DiningServer(int numPhils) {
this.numPhils = numPhils;
state = new int[numPhils];
for (int i = 0; i < numPhils; i++) state[i] = THINKING;
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
state[right(k)] != EATING)
state[k] = EATING;
}
private void eat(String name, int napEat)
throws InterruptedException {
int napping;
napping = 1 + (int) random(napEat);
System.out.println("age=" + age() + ", " + name
+ " is eating for " + napping + " ms");
Thread.sleep(napping);
}
private synchronized void takeForks(int i)
throws InterruptedException {
state[i] = HUNGRY; test(i);
while (state[i] != EATING) wait();
}
private synchronized void putForks(int i) {
if (state[i] != EATING) return;
state[i] = THINKING;
test(left(i)); test(right(i));
notifyAll();
}
}