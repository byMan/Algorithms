package lecture.FifthDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문제_동전던지기_UnionFind상급 {
	static int de;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static class Node {
		int y, x;

		Node(int a, int b) {
			y = a;
			x = b;
		}

	}

	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
	static int N, M, Q;
	static char map[][];
	static Node parent[][];
	static int memCnt[][];
	static int score[];// k점수가 영역이 몇개 인가 score[k] ?
	static int invalidCmd[];// 동전 이미 있는데 올려둔 경우

	static Node getFind(Node a) {
		Node tmp = parent[a.y][a.x];
		if (a.y == tmp.y && a.x == tmp.x)
			return a;
		Node ret = getFind(tmp);
		parent[a.y][a.x] = ret;
		return ret;
	}

	static void setUnion(Node a, Node b) {
		Node pa = getFind(a);
		Node pb = getFind(b);
		if (pa.y != pb.y || pa.x != pb.x) {
			parent[pb.y][pb.x] = pa;
		}
	}

	static void init_coin() {
		parent = new Node[N][M];
		memCnt = new int[N][M];
		score = new int[370000];

		// UF init
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {

				if (map[y][x] == '0') {
					parent[y][x] = new Node(y, x);
					memCnt[y][x] = 1;
					score[memCnt[y][x] * 2]++;
				}
			}
		}

		// 초기상태 setUnion
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (map[y][x] == '0') {
					for (int t = 0; t < 4; t++) {
						int ny = y + dy[t];
						int nx = x + dx[t];
						if (ny < 0 || nx < 0 || ny >= N || nx >= M)
							continue;
						if (map[ny][nx] != '0')
							continue;

						Node pa = getFind(new Node(y, x));
						Node pb = getFind(new Node(ny, nx));
						if (pa.y != pb.y || pa.x != pb.x) {
							setUnion(new Node(y, x), new Node(ny, nx));
							score[memCnt[pa.y][pa.x] * 2]--;
							score[memCnt[pb.y][pb.x] * 2]--;
							memCnt[pa.y][pa.x] += memCnt[pb.y][pb.x];
							score[memCnt[pa.y][pa.x] * 2]++;
							memCnt[pb.y][pb.x] = 0;
						}
					}
				}
			}
		}
	}

	static void init_bin() {
		parent = new Node[N][M];
		memCnt = new int[N][M];
		score = new int[370000];
		// UF init
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (map[y][x] == '_') {
					parent[y][x] = new Node(y, x); // 내가 대표
					memCnt[y][x] = 1;
					score[memCnt[y][x] * 1]++;
				}
			}
		}

		// 초기상태 setUnion
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (map[y][x] == '_') {
					for (int t = 0; t < 4; t++) {
						int ny = y + dy[t];
						int nx = x + dx[t];
						if (ny < 0 || nx < 0 || ny >= N || nx >= M)
							continue;
						if (map[ny][nx] != '_')
							continue;

						Node pa = getFind(new Node(y, x));
						Node pb = getFind(new Node(ny, nx));
						if (pa.y != pb.y || pa.x != pb.x) {
							setUnion(new Node(y, x), new Node(ny, nx));
							score[memCnt[pa.y][pa.x] * 1]--;
							score[memCnt[pb.y][pb.x] * 1]--;
							memCnt[pa.y][pa.x] += memCnt[pb.y][pb.x];
							score[memCnt[pa.y][pa.x] * 1]++;
							memCnt[pb.y][pb.x] = 0;
						}
					}
				}
			}
		}
	}

	static void change(int y, int x, char type) {
		if (type == '0') {
			if (map[y][x] == '0') {
				return;
			}
			map[y][x] = '0';
			parent[y][x] = new Node(y, x);
			memCnt[y][x] = 1;
			score[memCnt[y][x] * 2]++;
			for (int t = 0; t < 4; t++) {
				int ny = y + dy[t];
				int nx = x + dx[t];
				if (ny < 0 || nx < 0 || ny >= N || nx >= M)
					continue;
				if (map[ny][nx] != '0')
					continue;

				Node pa = getFind(new Node(y, x));
				Node pb = getFind(new Node(ny, nx));
				if (pa.y != pb.y || pa.x != pb.x) {
					setUnion(pa, pb);
					score[memCnt[pa.y][pa.x] * 2]--;
					score[memCnt[pb.y][pb.x] * 2]--;
					memCnt[pa.y][pa.x] += memCnt[pb.y][pb.x];
					memCnt[pb.y][pb.x] = 0;
					score[memCnt[pa.y][pa.x] * 2]++;
				}
			}
		} else if (type == '_') {
			if (map[y][x] == '_')
				return;
			map[y][x] = '_';
			parent[y][x] = new Node(y, x);
			memCnt[y][x] = 1;
			score[memCnt[y][x] * 1]++;
			for (int t = 0; t < 4; t++) {
				int ny = y + dy[t];
				int nx = x + dx[t];
				if (ny < 0 || nx < 0 || ny >= N || nx >= M)
					continue;
				if (map[ny][nx] != '_')
					continue;
				Node pa = getFind(new Node(y, x));
				Node pb = getFind(new Node(ny, nx));
				if (pa.y != pb.y || pa.x != pb.x) {
					setUnion(pa, pb);
					score[memCnt[pa.y][pa.x] * 1]--;
					score[memCnt[pb.y][pb.x] * 1]--;
					memCnt[pa.y][pa.x] += memCnt[pb.y][pb.x];
					memCnt[pb.y][pb.x] = 0;
					score[memCnt[pa.y][pa.x] * 1]++;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		invalidCmd = new int[Q];
		long ans = 0;
		init_coin();
		for (int q = 0; q < Q; q++) {
			if (cmd[q][0] == 1) {
				if (map[cmd[q][1]][cmd[q][2]] == '0') {
					invalidCmd[q] = 1;
					continue;
				}
				change(cmd[q][1], cmd[q][2], '0');
			}
			if (cmd[q][0] == 2)
				ans += score[cmd[q][1]];
		}
		de = -1;

		init_bin();
		for (int q = Q - 1; q >= 0; q--) {
			if (cmd[q][0] == 1 && invalidCmd[q] == 0)
				change(cmd[q][1], cmd[q][2], '_');
			if (cmd[q][0] == 2)
				ans += score[cmd[q][1]];
		}

		System.out.println(ans);
	}

	static int cmd[][];

	static void input() throws IOException {
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int y = 0; y < N; y++) {
			String str = br.readLine();
			map[y] = str.toCharArray();
		}
		Q = Integer.parseInt(br.readLine());
		cmd = new int[Q][3];
		for (int y = 0; y < Q; y++) {
			st = new StringTokenizer(br.readLine());
			cmd[y][0] = Integer.parseInt(st.nextToken());
			if (cmd[y][0] == 1) {
				cmd[y][1] = Integer.parseInt(st.nextToken());
				cmd[y][2] = Integer.parseInt(st.nextToken());
			}
			if (cmd[y][0] == 2) {
				cmd[y][1] = Integer.parseInt(st.nextToken());
			}
		}
	}
}

/*
 * 입력조건 첫 줄에는 Height, Width가 입력됩니다. (3 <= H, W <= 600) 다음 줄 부터 Height x Width 개수
 * 만큼 게임 판 상태가 입력됩니다. 언더바('_')는 빈칸이며, 숫자 0은 동전이 올려진 위치입니다.
 * 
 * 그리고 Query 의 개수 Q가 입력됩니다. (1 <= Q <= 100,000) 다음 줄부터 Q 줄의 명령이 입력되며 Format은 다음과
 * 같습니다. 1 Y X : 좌표 (Y,X)에 동전을 던집니다. (좌표는 왼쪽 상단이 0, 0 이며 Y, X 순서대로 표기합니다.) 2 K
 * :영역점수가 K인 영역이 몇 개인지 알아냅니다. (1 <= K <= 360,000)
 * 
 * 
 * 출력조건 영역의 점수가 K 인 영역이 몇 개 인지에 대한 답을 모두 합한 값을 출력합니다.
 * 
 * 
 * 
 * [입력] 4 4 ___0 __0_ ___0 ___0 5 1 1 0 1 1 1 2 6 2 3 2 4
 * 
 * [출력] 4
 * 
 * 
 */