import java.util.Random;
public abstract class Sugar {
	private static final long startTime= System.currentTimeMillis();
	private static final Random rnd = new Random();
		// utility methods
	protected static final long age() {
		return System.currentTimeMillis() - startTime;
	}
	protected static final double random() {
		return rnd.nextDouble(); // in range [0, 1)
	}
	protected static final double random(int ub) {
		return rnd.nextDouble()*ub; // in range [0, ub)
	}
	protected static final double random(int lb, int ub) {
		return lb + rnd.nextDouble()*(ub - lb); // in range [lb, ub)
	}
}
