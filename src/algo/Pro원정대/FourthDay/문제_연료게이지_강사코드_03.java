package algo.Pro원정대.FourthDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 문제_연료게이지_강사코드_03 {
	static int de;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static String str;

	static int bSearch() {
		int s = 0;
		int e = str.length() - 1;
		int ans = -1;
		while (s <= e) {
			int mid = (s + e) / 2;

			if (str.charAt(mid) == '#') {
				// S #[s # E]
				ans = mid;
				s = mid + 1;
			}
			if (str.charAt(mid) == '_') {
				// [S e]_ E
				e = mid - 1;
			}
		}
		if (ans == -1)
			return -1;

		return ans;
	}

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		for (int t = 0; t < N; t++) {
			str = br.readLine();
			int ret = bSearch();
			if (ret == -1) {
				// # 못발견
				System.out.println("0%");
			} else {
				int ans = (ret + 1) * 100 / str.length();
				System.out.println(ans + "%");
			}
		}

	}

}