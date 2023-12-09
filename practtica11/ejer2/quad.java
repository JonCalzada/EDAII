interface TheFunction
{
    public double evaluate(double x);
    public String toString();
}
class MyFunction implements TheFunction
{
    public double evaluate(double x)
    {
        return x*x;
    }
    public String toString()
    {
        return " x**2";
    }
}
class Area extends Thread
{
    private double p, q, epsilon, result;
    private TheFunction f;
    public Area(double a, double b, double eps, TheFunction fn)
    {
        p = a;
        q = b;
        epsilon = eps;
        f = fn;
    }
    public double getResult()
    {
        return result;
    }
    private static double trapezoidArea
    (double p, double q, TheFunction f)
    {
        double area =
            (Math.abs(q-p))/2 * (f.evaluate(p) + f.evaluate(q));
        return area;
    }
    public void run()
    {
        double bigArea = trapezoidArea(p, q, f);
        double leftSmallArea = trapezoidArea (p, ((p+q)/2), f);
        double rightSmallArea = trapezoidArea(((p+q)/2), q, f);
        double sumOfAreas = leftSmallArea + rightSmallArea;
        double relError = Math.abs(bigArea - sumOfAreas);
        if (relError <= (epsilon * sumOfAreas))
            result = bigArea;
        else
        {
            Area leftArea = new Area(p, (p+q)/2, epsilon, f);
            leftArea.start();
            Area rightArea = new Area((p+q)/2, q, epsilon, f);
            rightArea.start();
            try
            {
                leftArea.join();
            }
            catch (InterruptedException e) { /* ignored */ }
            try
            {
                rightArea.join();
            }
            catch (InterruptedException e) { /* ignored */ }
            result = leftArea.getResult() + rightArea.getResult();
        }
    }
}
class AdaptiveQuadrature
{
    public static void main(String[] args)
    {
        double a = 0, b = 0, epsilon = 0;
        try
        {
            a = (Double.valueOf(args[0])).doubleValue();
            b = (Double.valueOf(args[1])).doubleValue();
            epsilon = (Double.valueOf(args[2])).doubleValue();
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
        if (b <= a || epsilon <= 0)
        {
            System.err.println("b <= a || epsilon <=0, exit");
            System.exit(1);
        }
        TheFunction fn = new MyFunction();
        System.out.println("Adaptive Quadrature of" + fn + " from "
                           + a + " to " + b + " with relative error " + epsilon);
        Area area = new Area(a, b, epsilon, fn);
        new PseudoTimeSlicing(); // for Solaris, not Windows 95/NT
        area.start();
        try
        {
            area.join();
        }
        catch (InterruptedException e) { /* ignored */ }
        double result = area.getResult();
        System.out.println("Result for" + fn + " = " + result);
        System.exit(0);
    }
}

/*
% javac quad.java
% java AdaptiveQuadrature 0.5 1.5 0.001
Adaptive Quadrature of x**2 from 0.5 to 1.5 with relative error 0.0010
Java version=1.3.0
Java vendor=IBM Corporation
OS name=Linux
OS arch=i586
OS version=#1 Mon Sep 27 10:25:54 EDT 1999.2.2.12-20
No PseudoTimeSlicing needed
Result for x**2 = 1.084136962890625
*/
