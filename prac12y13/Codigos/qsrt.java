class Task {
public int left = -1, right = -1;
public Task(int left, int right)
{ this.left = left; this.right = right; }
}
class QuickSort extends SugarMP implements Runnable {
private static int N = 10;

private static int RANGE = 100;
private static int NCPU = 4;
private static final MessagePassing doneCount
= new MessagePassing();
private static final MessagePassing task
= new MessagePassing();
private static int[] nums = null;
private String name = null;
private int id = -1;
private Thread me = null;
private QuickSort(int id) {
this.name = "Worker" + id;
this.id = id;
(me = new Thread(this)).start();
}
private static void quickSort
(int worker, int left, int right)
throws InterruptedException {
int pivot = nums[left];
int l = left, r = right;
boolean done = false;
Integer doneMessage = new Integer(worker);
while (!done) {
if (nums[l+1] > pivot) {
while (r > l+1 && nums[r] > pivot) { r--; }
if (r > l+1) { l++;
int temp = nums[r]; nums[r] = nums[l];
nums[l] = temp;
done = l >= r-1;
} else done = true;
} else { l++; done = l >= r; }
}
int temp = nums[left]; nums[left] = nums[l];
nums[l] = temp;
if (right-(l+1) > 0) send(task, new Task(l+1, right));
else if (right-(l+1) == 0) send(doneCount, doneMessage);
send(doneCount, doneMessage);
if ((l-1)-left > 0) send(task, new Task(left, l-1));
else if ((l-1)-left == 0) send(doneCount, doneMessage);
}
public void timeToQuit() { me.interrupt(); }
public void pauseTilDone() throws InterruptedException
{ me.join(); }
public void run() {
Task m = null;
while (true) {
if (Thread.interrupted()) {
System.out.println("age=" + age() + ", " + name
+ " interrupted");
return;
}
try {
m = (Task) receive(task);
quickSort(id, m.left, m.right);
} catch (InterruptedException e) {
System.out.println("age=" + age() + ", " + name
+ " interrupted out of send/receive");
return;
}
}
}
public static void main(String[] args) {
try {
N = Integer.parseInt(args[0]);
RANGE = Integer.parseInt(args[1]);
NCPU = Integer.parseInt(args[2]);
} catch (Exception e) { /* use defaults */ }
System.out.println("Quick sorting " + N
+ " random numbers between 1 and " + RANGE
+ " using " + NCPU + " CPUs");
nums = new int[N];
for (int i = 0; i < N; i++)
nums[i] = 1 + (int) (random()*RANGE);
System.out.println("Original numbers:");
for (int i = 0; i < N; i++)
System.out.print(" " + nums[i]); System.out.println();
// create the workers with self-starting threads
QuickSort[] q = new QuickSort[NCPU];
new PseudoTimeSlicing(); // for Solaris, not Windows 95/NT
for (int i = 0; i < NCPU; i++) q[i] = new QuickSort(i);
try {
send(task, new Task(0, N-1));
// wait for enough "singletons" to be produced
for (int i = 0; i < N; i++) receive(doneCount);
System.out.println("Sorted numbers:");
for (int i = 0; i < N; i++)
System.out.print(" " + nums[i]);
System.out.println();
for (int i = 0; i < NCPU; i++) q[i].timeToQuit();
Thread.sleep(1000);
for (int i = 0; i < NCPU; i++) q[i].pauseTilDone();
} catch (InterruptedException e) { /* ignored */ }
System.out.println("age()=" + age() + ", done");
System.exit(0);
}
}