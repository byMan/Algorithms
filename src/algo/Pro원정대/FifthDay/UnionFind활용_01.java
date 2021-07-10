package algo.Pro원정대.FifthDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class UnionFind활용_01 {

	static int de;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int cmd[][] = { { 1, 2 }, { 0, 1 }, { 2, 1 }, { 1, 1 }, { 2, 3 }, { 3, 2 }, { 3, 3 } };		//입력 y,x 좌표

	static class Node {
		int y, x;

		Node(int a, int b) {
			y = a;
			x = b;
		}
	}

	static Node parent[][] = new Node[4][4]; // parent[y][x] = y,x 의 부모
	static int memCnt[][] = new int[4][4]; // memCnt[y][x] -> (y,x) 의 member수를 알려주는 dat

	static Node getFind(Node a) {
		if (parent[a.y][a.x].y == a.y && parent[a.y][a.x].x == a.x)
			return a; // parent == a
		Node pa = getFind(parent[a.y][a.x]);
		parent[a.y][a.x] = pa;
		return pa;
	}

	static void setUnion(Node a, Node b) {
		Node pa = getFind(a);
		Node pb = getFind(b);
		if (pa.y != pb.y || pa.x != pb.x) {
			// pa <- pb
			parent[pb.y][pb.x] = pa;
		}
	}

	static int map[][] = new int[4][4];

	public static void main(String[] args) throws IOException {
		// init
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				parent[y][x] = new Node(y, x); // init
				memCnt[y][x] = 1;
			}
		}
		// cmd 처리
		int dy[] = { -1, 1, 0, 0 };
		int dx[] = { 0, 0, -1, 1 };
		for (int k = 0; k < 7; k++) {

			int y = cmd[k][0];
			int x = cmd[k][1];
			map[y][x] = 1;
			for (int t = 0; t < 4; t++) {
				int ny = y + dy[t];
				int nx = x + dx[t];
				if (ny < 0 || nx < 0 || ny >= 4 || nx >= 4)
					continue;
				if (map[ny][nx] == 0)
					continue;

				Node pa = getFind(new Node(y, x));
				Node pb = getFind(new Node(ny, nx));
				if (pa.y != pb.y || pa.x != pb.x) {
					setUnion(new Node(pa.y, pa.x), new Node(pb.y, pb.x));
					memCnt[pa.y][pa.x] += memCnt[pb.y][pb.x];
					memCnt[pb.y][pb.x] = 0;
				}
			}
		}

		for(int i=0; i<4; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		
		
		System.out.println();
		
		for(int i=0; i<4; i++) {
			System.out.println(Arrays.toString(memCnt[i]));
		}
		de = -1;

	}

}


/*

문제> 4 X 4 배열에서 아래에 주어진 입력 좌표에 땅이 생긴다.
땅은 입력좌표가 아래와 같이 순서대로 주어진다.
좌표의 모양을 행렬로 나타내면 아래와 같다
[0, 1, 0, 0]
[0, 1, 1, 0]
[0, 1, 0, 1]
[0, 0, 1, 1]

위의 행렬에서 1로 상하좌우 연결된 땅은 한 덩어리의 땅이며 그 크기는 각각 1이 4개로 땅 크기가 4인 땅과
1이 3개로 땅 크기가 3인 땅 두 개로 나뉘어진 것을 볼 수 있다.
이것을 UnionFind로 같은 영역에 대한 땅의 크기 값을 구하는 프로그램을 작성하시오.

[입력]
1 2
0 1
2 1
1 1
2 3
3 2
3 3


[출력]
[1, 0, 1, 1]
[1, 4, 0, 1]
[1, 0, 1, 0]
[1, 1, 0, 3]

*/