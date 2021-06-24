package lecture.FirstDay;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 문제_카드발급기 {
	static int n;

	public static boolean run(int[] key, int[] num, int[] dat) {
		for (int i = 0; i < n; i++) {
			dat[num[i]] = key[i];
		}

		for (int i = 1; i < n; i++) {
			if (dat[i] < dat[i + 1])
				return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tc = Integer.parseInt(br.readLine());
		int[] key = new int[1100000];
		int[] num = new int[1100000];
		int[] dat = new int[1100000];
		StringTokenizer st;
		for (int i = 0; i < tc; i++) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; x++) {
				key[x] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; x++) {
				num[x] = Integer.parseInt(st.nextToken());
			}

			boolean result = run(key, num, dat);
			if (result == true)
				bw.write("YES\n");
			else
				bw.write("NO\n");
			;
		}

		br.close();
		bw.close();
	}
}