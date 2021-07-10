package algo.Pro원정대.FourthDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 문제_천지창조_강사코드_01 {

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
	static char map[][] = new char[8][9];
	static int visited[][] = new int[8][9];
	static Queue<Node> startQu = new LinkedList<Node>();

	static void getStart(int y, int x) {
		Queue<Node> qu1 = new LinkedList<Node>();
		qu1.add(new Node(y, x, 0));
		visited[y][x] = 1;

		while (!qu1.isEmpty()) {
			Node now = qu1.poll();
			// 탐색 될때
			startQu.add(now);

			// 다음노드 큐등록
			for (int t = 0; t < 4; t++) {
				int ny = now.y + dy[t];
				int nx = now.x + dx[t];
				if (ny < 0 || nx < 0 || ny >= 8 || nx >= 9)
					continue;
				if (map[ny][nx] != '#')
					continue;
				if (visited[ny][nx] == 1)
					continue;
				visited[ny][nx] = 1;
				qu1.add(new Node(ny, nx, 0));
			}
		}

	}

	static int go() {
		// startQu이용해서 빈칸을 탐색 , 빈칸중에서 # 근처에있는거 발견시 return

		while (!startQu.isEmpty()) {
			Node now = startQu.poll();
			// '_' 이 탐색된다.
			for (int t = 0; t < 4; t++) {
				int ny = now.y + dy[t];
				int nx = now.x + dx[t];
				if (ny < 0 || nx < 0 || ny >= 8 || nx >= 9)
					continue;
				if (map[ny][nx] == '#' && visited[ny][nx] == 0)
					return now.level;
			}

			for (int t = 0; t < 4; t++) {
				int ny = now.y + dy[t];
				int nx = now.x + dx[t];
				if (ny < 0 || nx < 0 || ny >= 8 || nx >= 9)
					continue;
				if (map[ny][nx] != '_')
					continue;
				if (visited[ny][nx] == 1)
					continue;
				visited[ny][nx] = 1;
				startQu.add(new Node(ny, nx, now.level + 1));
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		for (int y = 0; y < 8; y++) {
			String str = br.readLine();
			for (int x = 0; x < 9; x++) {
				map[y][x] = str.charAt(x);
			}
		}

		int flag = 0;
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 9; x++) {
				if (map[y][x] == '#') {
					getStart(y, x);
					flag = 1;
					break;
				}
			}
			if (flag == 1)
				break;
		}

		int ret = go(); // 큐에 있는 start 지점부터 ~ 다음 그림까지 탐색
		System.out.println(ret);
	}

}
