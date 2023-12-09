public class Prime {
	public static boolean prime(int k) {
		if (k < 2) return false;
		int limit = k/2;
		for (int i = 2; i <= k/2; i++) {
			if ((k % i) == 0) return false;
		}
		return true;
	}

public static void main(String[] args) {
	int n = 0;
	try {
		n = Integer.parseInt(args[0]);
	} catch (NumberFormatException e) {
		System.out.println("improper format");
		System.exit(1);
	} catch (ArrayIndexOutOfBoundsException e) {
		System.out.println("no command line argument");
		System.exit(1);
	}
	if (n < 2) {
		System.out.println("command line argument " + n+ " is too small");
		System.exit(1);
	}
	System.out.println("printing primes from 2 to " + n);
	for (int i = 2; i <= n; i++) {
		if (Prime.prime(i)) System.out.println(i + " is prime");
		}
	}
}


//Unit testing of Prime.java
/*
% javac Prime.java
% java Prime
no command line argument
% java Prime abc
improper format
% java Prime 0
command line argument 0 is too small
% java Prime 10
printing primes from 2 to 10
2 is prime
3 is prime
5 is prime
7 is prime
*/