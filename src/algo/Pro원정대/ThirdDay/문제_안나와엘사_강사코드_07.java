package algo.Pro원정대.ThirdDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 문제_안나와엘사_강사코드_07 {
	static int de;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static class Node { // 탐색할 노드 정보
		int ay, ax;
		int ey, ex;
		int level;

		Node(int a, int b, int c, int d, int e) {
			ay = a;
			ax = b;
			ey = c;
			ex = d;
			level = e;
		}
	}

	static int N;
	static char map[][];
	static int dy[] = { -1, 1, 0, 0, 0 };
	static int dx[] = { 0, 0, -1, 1, 0 };
	static int ay, ax, ey, ex;

	public static void main(String[] args) throws IOException {
		input();
		Queue<Node> qu = new LinkedList<Node>();
		int visited[][][][] = new int[N][N][N][N];
		visited[ay][ax][ey][ex] = 1;
		qu.add(new Node(ay, ax, ey, ex, 0));

		while (!qu.isEmpty()) {
			Node now = qu.poll();
			if ((now.ay == now.ey) && (now.ax == now.ex)) {
				// 탐색(방문)되었을때 , 같은지 확인처리
				System.out.println(now.level);
				return;
			}

			for (int t1 = 0; t1 < 5; t1++) { // 다음 노드 경우 25가지
				int nay = now.ay + dy[t1];
				int nax = now.ax + dx[t1];
				if (nay < 0 || nax < 0 || nay >= N || nax >= N)
					continue;
				if (map[nay][nax] == '#')
					continue;
				for (int t2 = 0; t2 < 5; t2++) {
					int ney = now.ey + dy[t2];
					int nex = now.ex + dx[t2];
					if (ney < 0 || nex < 0 || ney >= N || nex >= N)
						continue;
					if (map[ney][nex] == '#')
						continue;
					if (visited[nay][nax][ney][nex] == 1)
						continue;
					visited[nay][nax][ney][nex] = 1;
					qu.add(new Node(nay, nax, ney, nex, now.level + 1));
				}
			}
		}
	}

	static void input() throws IOException {

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int y = 0; y < N; y++) {
			String str = br.readLine();
			for (int x = 0; x < N; x++) {
				map[y][x] = str.charAt(x);
			}
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		ay = Integer.parseInt(st.nextToken());
		ax = Integer.parseInt(st.nextToken());
		ey = Integer.parseInt(st.nextToken());
		ex = Integer.parseInt(st.nextToken());
	}
}
