package algo.Pro원정대.SecondDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문제_할아버지의유산_01 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int map[][];
	static int N, M;

	static int isExist(int y1, int x1, int y2, int x2) {
		// (y1,x1) ~ (y2,x2)
		for (int y = y1; y <= y2; y++) {
			for (int x = x1; x <= x2; x++) {
				if (map[y][x] == 0)
					return 1;
			}
		}
		return 0;
	}

	static int getScore(int y1, int x1, int y2, int x2) {
		int sum = 0;
		for (int y = y1; y <= y2; y++) {
			for (int x = x1; x <= x2; x++) {
				sum += map[y][x];
			}
		}
		return sum;
	}

	public static void main(String args[]) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1. 직사각형 하나 잡고
		// 2. 잡은 직사각형이 0을 포함하는지 -> 0이 없으면 점수구하기
		int maxSum = (int) -21e8;
		// (y1,x1) ~ (y2,x2)
		for (int y1 = 0; y1 < N; y1++) {
			for (int x1 = 0; x1 < M; x1++) {
				for (int y2 = y1; y2 < N; y2++) {
					for (int x2 = x1; x2 < M; x2++) {
						// 0 유무 체크
						int ret = isExist(y1, x1, y2, x2);
						if (ret == 0) {
							// 점수계산
							int sum = getScore(y1, x1, y2, x2);
							if (sum > maxSum) {
								maxSum = sum;
							}
						}

					}
				}
			}
		}
		System.out.println(maxSum);

	}

}