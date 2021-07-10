package algo.Pro원정대.FourthDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 이분탐색활용_02 {

	static int de;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		// 정렬된 데이터
		int vect[] = { 1, 2, 3, 5, 7, 10, 15 };

		int target = 7;
		int s = 0;
		int e = 6;

		int flag = 0;
		while (s <= e) {
			int mid = (s + e) / 2; // mid 확인후

			if (vect[mid] == 7) {
				flag = 1;
				System.out.println("찾았다");
			}
			if (vect[mid] <= 7) {
				// mid -> 7이 여기있을거 같다 [mid + 1 ~ e]
				s = mid + 1;
			} else if (vect[mid] > 7) {
				// 7이 여기있을거 같다 <- mid [s ~ mid -1]
				e = mid - 1;
			}
		}

		if (flag == 0) {
			System.out.println("없다");
		}

	}

}