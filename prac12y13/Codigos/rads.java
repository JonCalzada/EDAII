class Result { public int number, count;
public Result(int n, int c) { number = n; count = c; }
}
class Peer extends SugarMP implements Runnable {
private int N = -1, id = -1, mine = 0;
private MessagePassing[] channel = null;
private MessagePassing reply = null;
public Peer(int N, int id, int mine, MessagePassing[] channel,
MessagePassing reply) {
this.N = N;
this.id = id;
this.mine = mine;
this.channel = channel;
this.reply = reply;
new Thread(this).start();
}
public void run() {
int count = 0, other = 0;
try {
// Send my number to all the other workers.
for (int i = 0; i < N; i++)
if (i != id) send(channel[i], new Integer(mine));
// Of the N-1 numbers sent to me by the other workers,
// count how many are less than my number.
for (int i = 1; i < N; i++) {
other = ((Integer) receive(channel[id])).intValue();
if (other < mine) count++;
}
// Send my count of less-than-mine-seen back to main().
send(reply, new Result(mine, count));
} catch (InterruptedException e) { return; }
}
}
class RadixSort extends SugarMP {
public static void main(String[] args) {
int N = 15;
int RANGE = 1000;
int[] nums = null;
MessagePassing[] channel = null;
MessagePassing reply = null;
try {
N = Integer.parseInt(args[0]);
RANGE = Integer.parseInt(args[1]);
} catch (Exception e) { /* use defaults */ }
System.out.println("Radix sorting " + N
+ " random integers between 1 and " + RANGE);
nums = new int[N];
for (int i = 0; i < N; i++)
nums[i] = 1 + (int)random(RANGE);
System.out.println("Original numbers:");
for (int i = 0; i < N; i++)
System.out.print(" " + nums[i]); System.out.println();
// Set up the reply channel.
reply = new MessagePassing();
channel = new MessagePassing[N];
// Set up the communication channels.
for (int i = 0; i < N; i++)
channel[i] = new MessagePassing();
// Start the worker threads.
for (int i = 0; i < N; i++)
new Peer(N, i, nums[i], channel, reply);
int[] tallyCounts = new int[N];
for (int i = 0; i < N; i++) tallyCounts[i] = 0;
try {
// Gather the results.
for (int i = 0; i < N; i++) {
Result r = (Result) receive(reply);
// Put the number where it belongs in the sorted order,
// which is the value of the counter in which it recorded
// the number of less-than-its-own numbers it saw.
nums[r.count] = r.number;
tallyCounts[r.count]++;
}
} catch (InterruptedException e) { /* ignored */ }
System.out.println("Sorted numbers:");
for (int i = 0; i < N; i++)
System.out.print(" " + nums[i]); System.out.println();
for (int i = 0; i < N; i++)
// Zeros show where duplicates have occured.
System.out.print(" " + tallyCounts[i]); System.out.println();
System.out.println("age()=" + age() + ", done");
System.exit(0);
}
}