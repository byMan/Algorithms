package algo.Pro원정대.FifthDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 문제_무서운시어머니_06 {

	static int de;
	static final int INF = (int) 21e8;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static class Node implements Comparable<Node> {
		int y, x;
		int cost; // 피로도 누적합 (시작점 부터 )

		Node(int a, int b, int c) {
			y = a;
			x = b;
			cost = c;
		}

		@Override
		public int compareTo(Node target) {
			return this.cost - target.cost;
		}
	}

	static int map[][];
	static int N;
	static int sy, sx;
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
	static int dist[][];
	static int ans = (int) -21e8;

	static void dijkstra(int sy, int sx) {
		dist = new int[N][N];
		for (int y = 0; y < N; y++)
			for (int x = 0; x < N; x++)
				dist[y][x] = INF;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(sy, sx, map[sy][sx]));

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (dist[now.y][now.x] < INF)
				continue;
			dist[now.y][now.x] = now.cost; // 시작->now 최소비용 결정
			ans = now.cost;
			for (int t = 0; t < 4; t++) {
				int ny = now.y + dy[t];
				int nx = now.x + dx[t];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N)
					continue;
				if (map[ny][nx] == -1)
					continue;
				pq.add(new Node(ny, nx, now.cost + map[ny][nx]));// (시작->now까지) + (now->next)
			}
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		dijkstra(sy, sx);
		de = -1;
		System.out.println(ans);
	}

	static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		sy = Integer.parseInt(st.nextToken());
		sx = Integer.parseInt(st.nextToken());

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
