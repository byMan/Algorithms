package lecture.FifthDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 문제_FireFighter_강사코드 {
	static int de;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static class Node {
		int y, x;
		int level;

		Node(int a, int b, int c) {
			y = a;
			x = b;
			level = c;
		}
	}

	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
	static int N;
	static char map[][];
	static int mincoY, mincoX;
	static int fy, fx;

	static int bfs(int sy, int sx, int ey, int ex, int type) { // type 불을 못가도록
		int visited[][] = new int[N][N];
		Queue<Node> qu = new LinkedList<Node>();
		visited[sy][sx] = 1;
		qu.add(new Node(sy, sx, 0));

		while (!qu.isEmpty()) {
			Node now = qu.poll();
			if (now.y == ey && now.x == ex)
				return now.level;
			for (int t = 0; t < 4; t++) {
				int ny = now.y + dy[t];
				int nx = now.x + dx[t];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N)
					continue;
				if (visited[ny][nx] == 1)
					continue;
				if (map[ny][nx] == '#')
					continue;
				if (map[ny][nx] == '$' && type == 0)
					continue;
				visited[ny][nx] = 1;
				qu.add(new Node(ny, nx, now.level + 1));
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		input();
		int min = (int) 21e8;
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (map[y][x] == 'A') {
					int res = bfs(y, x, mincoY, mincoX, 0);
					res += bfs(y, x, fy, fx, 1);

					if (res < min) {
						min = res;
					}
				}
			}
		}

		System.out.println(min);
	}

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int y = 0; y < N; y++) {
			map[y] = br.readLine().toCharArray();
		}
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {

				if (map[y][x] == '$') {
					fy = y;
					fx = x;
				}
			}
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		mincoY = Integer.parseInt(st.nextToken());
		mincoX = Integer.parseInt(st.nextToken());
	}
}
