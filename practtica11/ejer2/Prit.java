class PrimeThread extends Thread
{
    private int m = 0;
    PrimeThread(int m)
    {
        this.m = m;
    }
    public void run()
    {
        if (Prime.prime(m))
            System.out.println(m + " is prime");
    }
}
class TestPrimeThreads
{
    public static void main(String[] args)
    {
        int n1 = 0, n2 = 0;
        try
        {
            n1 = Integer.parseInt(args[0]);
            n2 = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e)
        {
            System.out.println("improper format");
            System.exit(1);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("not enough command line arguments");
            System.exit(1);
        }
        if (n1 < 2 || n2 < 2 || n1 > n2)
        {
            System.out.println("illegal command line arguments "+ n1 + ", " + n2 );
            System.exit(1);
        }
        System.out.println("printing primes from "+ n1 + " to " + n2);
        new PseudoTimeSlicing(); // for Solaris, not Windows 95/NT
        for (int i = n1; i <= n2; i++)
        {
            Thread t = new PrimeThread(i);
            t.start();
        }
    }
}
//Sample run of prit.java
/*
% javac prit.java
% java TestPrimeThreads 10 20
printing primes from 10 to 20
Java version=1.3.0
Java vendor=IBM Corporation
OS name=Linux
OS arch=i586
OS version=#1 Mon Sep 27 10:25:54 EDT 1999.2.2.12-20
No PseudoTimeSlicing needed
11 is prime
13 is prime
17 is prime
19 is prime
% java TestPrimeThreads 1000000 1000060
printing primes from 1000000 to 1000060
Java version=1.3.0
Java vendor=IBM Corporation
OS name=Linux
OS arch=i586
OS version=#1 Mon Sep 27 10:25:54 EDT 1999.2.2.12-20
No PseudoTimeSlicing needed
1000003 is prime
1000033 is prime
1000037 is prime
1000039 is prime
*/
