import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Inversions {
	int[] arr = { 4, 3, 2, 1 };
	ArrayList<Integer> sorted = new ArrayList<Integer>();

	public Inversions() {
		try {
			// read data from file
			if (true) {
				PrintStream ps = new PrintStream(new FileOutputStream(new File(
						"IntegerArrayOut.txt")));
				System.setOut(ps);

				Scanner scanner = new Scanner(new File(
						"/home/shchang/IntegerArray.txt"));

				arr = new int[100000];
				for (int i = 0; i < 100000; i++)
					arr[i] = scanner.nextInt();

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println(countInv(0, arr.length - 1));
	}

	public long countInv(int start, int end) {
		System.out.format("inv start=%s end=%s\n", start, end);
		if (start == end)
			return 0;
		else {
			long inv = 0;
			inv += countInv(start, (end - start) / 2 + start);
			inv += countInv((end - start) / 2 + start + 1, end);
			inv += countSplitInv(start, end);

			return inv;
		}
	}

	public long countSplitInv(int start, int end) {
		System.out.format("split start=%s end=%s", start, end);
		if (start == end) {
			return 0;
		} else {
			sorted.clear();
			long inv = 0;

			int left = start;
			int right = (end - start) / 2 + start + 1;

			while (sorted.size() < end - start + 1) {
				if (arr[left] < arr[right]) {
					sorted.add(arr[left]);
					arr[left] = Integer.MAX_VALUE;

					if (left < (end - start) / 2 + start + 1)
						left++;

				} else {
					sorted.add(arr[right]);
					inv += ((end - start) / 2 + start + 1) - left;
					arr[right] = Integer.MAX_VALUE;

					if (right < end)
						right++;
				}
			}
			System.out.format(" inv=%d", inv);
			// System.out.format(" sorted=%s", sorted.toString());

			for (int num : sorted) {
				arr[start++] = num;
			}
			// System.out.format(" arr=%s", Arrays.toString(arr));
			System.out.println();

			return inv;
		}
	}
}
