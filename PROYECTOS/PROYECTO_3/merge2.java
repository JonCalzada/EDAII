import java.util.List;
import java.util.ArrayList;

public class ParallelMergeSorter implements Runnable {

	private List<Integer> list;
	private int numAvailableThreads;

	public ParallelMergeSorter(List<Integer> list, int numAvailableThreads) {
		super();
		this.list = list;
		this.numAvailableThreads = numAvailableThreads;
	}

	public List<Integer> getList() {
		return list;
	}

	public List<Integer> parallelMergeSort(List<Integer> list, int cores)
			throws InterruptedException {
		if (cores <= 1) {
			return mergeSort(list);
		}
		int middleIndex = list.size() / 2;
		ParallelMergeSorter leftSorter = new ParallelMergeSorter(list.subList(
				0, middleIndex), cores / 2);
		ParallelMergeSorter rightSorter = new ParallelMergeSorter(list.subList(
				middleIndex, list.size()), cores / 2);
		Thread leftThread = new Thread(leftSorter);
		Thread rightThread = new Thread(rightSorter);
		leftThread.start();
		rightThread.start();
		leftThread.join();
		rightThread.join();

		return merge(leftSorter.getList(), rightSorter.getList());
	}

	public List<Integer> mergeSort(List<Integer> list) {
		if (list.size() <= 1) {
			return list;
		}
		int middleIndex = list.size() / 2;
		List<Integer> left = mergeSort(list.subList(0, middleIndex));
		List<Integer> right = mergeSort(list.subList(middleIndex, list.size()));
		return merge(left, right);
	}

	public List<Integer> merge(List<Integer> list1, List<Integer> list2) {
		int x = 0;
		int y = 0;
		List<Integer> sortedList = new ArrayList<>(list1.size() + list2.size());
		while (x < list1.size() && y < list2.size()) {
			if (list1.get(x) < list2.get(y)) {
				sortedList.add(list1.get(x));
				x++;
			} else {
				sortedList.add(list2.get(y));
				y++;
			}
		}
		while (x < list1.size()) {
			sortedList.add(list1.get(x));
			x++;
		}
		while (y < list2.size()) {
			sortedList.add(list2.get(y));
			y++;
		}
		return sortedList;
	}

	@Override
	public void run() {
		try {
			this.list = parallelMergeSort(this.list, numAvailableThreads);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}