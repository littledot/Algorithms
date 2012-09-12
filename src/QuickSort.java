import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {
	boolean pretty = false;
	int[] arr = { 3, 8, 2, 5, 1, 4, 7, 6 };

	public QuickSort() {
		try {
			PrintStream ps = null;

			if (pretty) {
				ps = new PrintStream(new FileOutputStream(new File(
						"QuickSortOut.txt")));

				System.setOut(ps);

				Scanner scanner = new Scanner(new File(
						"/home/shchang/QuickSort.txt"));

				arr = new int[10000];
				for (int i = 0; i < 10000; i++)
					arr[i] = scanner.nextInt();
			}

			System.out.format("total comp=%s\n", qsortLast(0, arr.length - 1));
			if (!pretty)
				System.out.format("%s\n", Arrays.toString(arr));
			else
				ps.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public int qsortFirst(int left, int right) {
		System.out.format("qsort left=%d right=%d", left, right);

		if (left == right) {
			System.out.print('\n');
			return 0;
		}

		int temp, j;
		int pivot = arr[left];
		int i = left + 1;

		System.out.format(" p=%d", pivot);
		if (!pretty)
			System.out.format(" arr=%s", Arrays.toString(arr));

		for (j = left + 1; j <= right; j++) {
			if (arr[j] < pivot) {
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;

				i++;
			}
		}

		temp = arr[left];
		arr[left] = arr[i - 1];
		arr[i - 1] = temp;

		int comp = right - left;
		System.out.format(" comp=%d\n", comp);
		comp += qsortFirst(left, i - 2 < left ? left : i - 2);
		comp += qsortFirst(i > right ? right : i, right);
		return comp;
	}

	public int qsortLast(int left, int right) {
		System.out.format("qsort left=%d right=%d", left, right);

		if (left == right) {
			System.out.print('\n');
			return 0;
		}

		int temp, j;
		int pivot = arr[right];
		int i = left;

		System.out.format(" p=%d", pivot);
		if (!pretty)
			System.out.format(" arr=%s", Arrays.toString(arr));

		for (j = left; j <= right - 1; j++) {
			if (arr[j] < pivot) {
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;

				i++;
			}
		}

		temp = arr[right];
		arr[right] = arr[i - 1];
		arr[i - 1] = temp;

		int comp = right - left;
		System.out.format(" comp=%d\n", comp);
		comp += qsortLast(left, i - 2 < left ? left : i - 2);
		comp += qsortLast(i > right ? right : i, right);
		return comp;
	}
}
