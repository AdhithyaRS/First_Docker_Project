import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class NthSmallestNumber {
    private static final Random RANDOM = new Random();
    private static List<Integer> generateRandom(int size) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= size * 10; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers.subList(0, size);
    }
    private static int BubbleSort(List<Integer> numbers, int n) {
        int size = numbers.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (numbers.get(j) < numbers.get(i)) {
                    Collections.swap(numbers, i, j);
                }
            }
        }
        return numbers.get(n - 1);
    }
    private static int QuickSort(List<Integer> numbers, int n) {
        if (numbers.size() == 1) {
            return numbers.get(0);
        }

        int pivotIndex = RANDOM.nextInt(numbers.size());
        int pivot = numbers.get(pivotIndex);
        List<Integer> lows = new ArrayList<>();
        List<Integer> highs = new ArrayList<>();
        List<Integer> pivots = new ArrayList<>();

        for (int number : numbers) {
            if (number < pivot) {
                lows.add(number);
            } else if (number > pivot) {
                highs.add(number);
            } else {
                pivots.add(number);
            }
        }

        if (n < lows.size()) {
            return QuickSort(lows, n);
        } else if (n < lows.size() + pivots.size()) {
            return pivots.get(0);
        } else {
            return QuickSort(highs, n - lows.size() - pivots.size());
        }
    }
    private static long findTimeTaken(List<Integer> numbers, int n, String algorithm) {
        long startTime = System.currentTimeMillis();
        if (algorithm.equals("bubble")) {
            BubbleSort(numbers, n);
        } else if (algorithm.equals("quick")) {
            QuickSort(numbers, n);
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
			int[] sizes = { 100, 1000, 10000 };

			for (int size : sizes) {
			    List<Integer> numbers = generateRandom(size);
			    System.out.println("List of unique random numbers of size " + size + " has been created");
			    System.out.print("Enter the value of n for finding the nth smallest number: ");
			    int n = scanner.nextInt();
			    long bubbleTime = findTimeTaken(new ArrayList<>(numbers), n, "bubble");
			    System.out.println("Time taken for finding the nth smallest number using bubble sort: " + bubbleTime + " ms");
				long quickTime = findTimeTaken(new ArrayList<>(numbers), n, "quick");
			    System.out.println("Time taken for finding the nth smallest number using quick sort: " + quickTime + " ms");
				if(bubbleTime<quickTime) System.out.println("The best algorithm is Bubble sort");
				else if(bubbleTime>quickTime) System.out.println("The best algorithm is Quick sort");
				else System.out.println("Both the Algorithm took same time");
			}
		}
	}
}