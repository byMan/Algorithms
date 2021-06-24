package lecture.SecondDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 문제_연쇄폭탄_08 {
	static int de;
	static int N;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][] map;
	static boolean[][] visit;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static class Node implements Comparable<Node> {
		int num;
		int row;
		int col;

		public Node(int num, int row, int col) {
			this.num = num;
			this.row = row;
			this.col = col;
		}

		@Override
		public int compareTo(Node o) {
			return this.num - o.num;
		}
	}

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		visit = new boolean[N][N];

		PriorityQueue<Node> q = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int val = Integer.parseInt(st.nextToken());
				map[i][j] = val;
				q.add(new Node(val, i, j));
			}
		}

		int val = 0;
		while (!q.isEmpty()) {
			Node node = q.poll();
			int num = node.num;
			int y = node.row;
			int x = node.col;
			if(visit[y][x]) continue;
			if(num == 16) {de = -1;}
			val = num;
			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >= N)
					continue;
				
				visit[ny][nx] = true;
			}
			visit[y][x] = true;
		}

		System.out.println(val + "초");
	}

}
