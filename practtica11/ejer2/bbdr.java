class Producer extends Sugar implements Runnable
{
    private String name = null;
    private int pNap = 0; // milliseconds
    private BoundedBuffer bb = null;
    private Thread me = null;
    public Producer(String name, int pNap, BoundedBuffer bb)
    {
        this.name = name;
        this.pNap = pNap;
        this.bb = bb;
        (me = new Thread(this)).start();
    }
    public void timeToQuit()
    {
        me.interrupt();
    }
    public void pauseTilDone() throws InterruptedException
    { me.join(); }
    public void run()
    {
        if (Thread.currentThread() != me)
            return;
        double item;
        int napping;
        while (true)
        {
            if (Thread.interrupted())
            {
                System.out.println("age=" + age() + ", " + name
                                   + " interrupted");
                return;
            }
            napping = 1 + (int) random(pNap);
            System.out.println("age=" + age() + ", " + name
                               + " napping for " + napping + " ms");
            try
            {
                Thread.sleep(napping);
            }
            catch (InterruptedException e)
            {
                System.out.println("age=" + age() + ", " + name
                                   + " interrupted from sleep");
                return;
            }
            item = random();
            System.out.println("age=" + age() + ", " + name
                               + " produced item " + item);
            try
            {
                bb.deposit(item);
            }
            catch (InterruptedException e)
            {
                System.out.println("age=" + age() + ", " + name
                                   + " interrupted from deposit");
                return;
            }
            System.out.println("age=" + age() + ", " + name
                               + " deposited item " + item);
        }
    }
}
class Consumer extends Sugar implements Runnable
{
    private String name = null;
    private int cNap = 0; // milliseconds
    private BoundedBuffer bb = null;
    private Thread me = null;
    public Consumer(String name, int cNap, BoundedBuffer bb)
    {
        this.name = name;
        this.cNap = cNap;
        this.bb = bb;
        (me = new Thread(this)).start();
    }
    public void timeToQuit()
    {
        me.interrupt();
    }
    public void pauseTilDone() throws InterruptedException
    { me.join(); }
    public void run()
    {
        if (Thread.currentThread() != me)
            return;
        double item;
        int napping;
        while (true)
        {
            if (Thread.interrupted())
            {
                System.out.println("age=" + age() + ", " + name
                                   + " interrupted");
                return;
            }
            napping = 1 + (int) random(cNap);
            System.out.println("age=" + age() + ", " + name
                               + " napping for " + napping + " ms");
            try
            {
                Thread.sleep(napping);
            }
            catch (InterruptedException e)
            {
                System.out.println("age=" + age() + ", " + name
                                   + " interrupted from sleep");
                return;
            }
            System.out.println("age=" + age() + ", " + name
                               + " wants to consume");
            try
            {
                item = bb.fetch();
            }
            catch (InterruptedException e)
            {
                System.out.println("age=" + age() + ", " + name
                                   + " interrupted from fetch");
                return;
            }
            System.out.println("age=" + age() + ", " + name
                               + " fetched item " + item);
        }
    }
}
class ProducersConsumers extends Sugar
{
    public static void main(String[] args)
    {
        int numSlots = 10;
        int numProducers = 1;
        int numConsumers = 1;
        int pNap = 2; // defaults
        int cNap = 2; // in
        int runTime = 60; // seconds
// following set true in srbb.java runs
// so as not to try to join with a
// suspended thread and thus deadlock
        boolean doJoin = true;
        try
        {
            numSlots = Integer.parseInt(args[0]);
            numProducers = Integer.parseInt(args[1]);
            numConsumers = Integer.parseInt(args[2]);
            pNap = Integer.parseInt(args[3]);
            cNap = Integer.parseInt(args[4]);
            runTime = Integer.parseInt(args[5]);
            doJoin = args[6].equals("yes");
        }
        catch (Exception e) { /* use defaults */ }
        System.out.println("ProducersConsumers:\n numSlots="
                           + numSlots + ", numProducers=" + numProducers
                           + ", numConsumers=" + numConsumers + ", pNap="
                           + pNap + ", cNap=" + cNap + ", runTime=" + runTime);
// create the bounded buffer
        BoundedBuffer bb = new BoundedBuffer(numSlots);
// start the Producers and Consumers
// (they have self-starting threads)
        Producer[] p = new Producer[numProducers];
        Consumer[] c = new Consumer[numConsumers];
        new PseudoTimeSlicing(); // for Solaris, not Windows 95/NT
        for (int i = 0; i < numProducers; i++)
            p[i] = new Producer("PRODUCER"+i, pNap*1000, bb);
        for (int i = 0; i < numConsumers; i++)
            c[i] = new Consumer("Consumer"+i, cNap*1000, bb);
        System.out.println("All threads started");
// let them run for a while
        try
        {
            Thread.sleep(runTime*1000);
            System.out.println("age=" + age()
                               + ", time to terminate the threads and exit");
            for (int i = 0; i < numProducers; i++)
                p[i].timeToQuit();
            for (int i = 0; i < numConsumers; i++)
                c[i].timeToQuit();
            Thread.sleep(1000);
            if (doJoin)
            {
                for (int i = 0; i < numProducers; i++)
                    p[i].pauseTilDone();
                for (int i = 0; i < numConsumers; i++)
                    c[i].pauseTilDone();
            }
            else
                System.out.println(" skipping pauseTilDone()");
        }
        catch (InterruptedException e) { /* ignored */ }
        System.out.println("age=" + age()
                           + ", all threads are done");
        System.exit(0);
    }
}

/*
% javac bwbb.java bbdr.java
% java ProducersConsumers 10 1 1 2 2 5
ProducersConsumers:
numSlots=10, numProducers=1, numConsumers=1, pNap=2, cNap=2, runTime=5
Java version=1.3.0
Java vendor=IBM Corporation
OS name=Linux
OS arch=i586
OS version=#1 Mon Sep 27 10:25:54 EDT 1999.2.2.12-20
No PseudoTimeSlicing needed
age=47, PRODUCER0 napping for 1349 ms
age=66, Consumer0 napping for 201 ms
All threads started
age=286, Consumer0 wants to consume
age=1406, PRODUCER0 produced item 0.11775977233610768
age=1422, PRODUCER0 deposited item 0.11775977233610768
age=1423, PRODUCER0 napping for 1203 ms
age=1424, Consumer0 fetched item 0.11775977233610768
age=1426, Consumer0 napping for 39 ms
age=1475, Consumer0 wants to consume
age=2636, PRODUCER0 produced item 0.717652488961075
age=2637, PRODUCER0 deposited item 0.717652488961075
age=2638, PRODUCER0 napping for 143 ms
age=2640, Consumer0 fetched item 0.717652488961075
age=2641, Consumer0 napping for 1020 ms
age=2795, PRODUCER0 produced item 0.29972388090543556
age=2797, PRODUCER0 deposited item 0.29972388090543556
age=2798, PRODUCER0 napping for 1898 ms
age=3668, Consumer0 wants to consume
age=3668, Consumer0 fetched item 0.29972388090543556
age=3669, Consumer0 napping for 1046 ms
age=4708, PRODUCER0 produced item 0.5794441336470957
age=4709, PRODUCER0 deposited item 0.5794441336470957
age=4710, PRODUCER0 napping for 955 ms
age=4725, Consumer0 wants to consume
age=4726, Consumer0 fetched item 0.5794441336470957
age=4727, Consumer0 napping for 528 ms
age=5078, time to terminate the threads and exit
age=5081, PRODUCER0 interrupted from sleep
age=5082, Consumer0 interrupted from sleep
age=6088, all threads are done
*/
