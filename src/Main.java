import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	static long p, d;
	static long a[][] = new long[1000][1000];
	static long dp[][] = new long[1000][1000];
	static long lim = 0;

	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		System.out.println("Start Time: " + t1);
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 1000; j++) {
				dp[i][j] = -1;
			}
		}
		File file = new File("input.txt");
		Scanner scan;
		try {
			scan = new Scanner(file);
			String s1 = scan.nextLine();

			String arr1[] = s1.split(" ");

			d = Long.parseLong(arr1[0]);
			p = Long.parseLong(arr1[1]);

			for (int i = 0; i < d; i++) {
				lim |= (long) 1 << i;
			}
			for (int i = 0; i < d; i++) {
				String s = scan.nextLine();
				String[] arr = s.split(" ");
				for (int j = 0; j < p; j++) {
					a[i][j] = Long.parseLong(arr[j]);
				}
			}
			System.out.println(funt(0, 0));
			long t2 = System.currentTimeMillis();
			System.out.println("End Time: " + t2);
			System.out.println("Dif Time: " + (t2-t1));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static long funt(long ci, long vis) {
		if (ci == p) {
			return 0;
		}
		if (vis == lim) {
			return Integer.MAX_VALUE;
		}
		long ret = Integer.MAX_VALUE;
		for (int j = 0; j < d; j++) {
			if ((vis & ((long) 1 << j)) == 0) {
				vis |= ((long) 1 << j);
				long tot = 0;
				ret = min(ret, funt(ci, vis));
				for (int i = (int) ci; i < p; i++) {
					tot += a[j][i];
					ret = min(ret, tot + funt(i + 1, vis));
				}
				vis ^= ((long) 1 << j);
			}
		}
		return ret;
	}

	public static long min(long a, long b) {
		if (a > b) {
			return b;
		} else {
			return a;
		}
	}

	public static String convertToBinary(long n) {
		String x = "";
		while (n > 0) {
			long a = n % 2;
			x = a + x;
			n = n / 2;
		}
		while (x.length() < 10) {
			x = "0" + x;
		}
		return x;
	}
}
