package algo.Pro원정대.FourthDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문제_순환회로_강사코드_08 {

	static int de;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int map[][];
	static int parent[];

	static int getFind(int a) {
		if (a == parent[a])
			return a;
		int ret = getFind(parent[a]);
		parent[a] = ret;
		return ret;
	}

	static void setUnion(int a, int b) {
		int pa = getFind(a);
		int pb = getFind(b);
		if (pa != pb) {
			// pa <- pb
			parent[pb] = pa;
		}
	}

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		parent = new int[N]; // 0~N-1
		for (int i = 0; i < N; i++)
			parent[i] = i;
		StringTokenizer st;
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		de = -1;

		int flag = 0;
		for (int y = 0; y < N; y++) {
			for (int x = y; x < N; x++) {
				if (map[y][x] == 1) {
					int pa = getFind(y);
					int pb = getFind(x);
					if (pa == pb) {
						flag = 1;
						break;
					}
					setUnion(y, x);
				}
			}
			if (flag == 1)
				break;
		}

		if (flag == 1)
			System.out.println("WARNING");
		else
			System.out.println("STABLE");
	}

}