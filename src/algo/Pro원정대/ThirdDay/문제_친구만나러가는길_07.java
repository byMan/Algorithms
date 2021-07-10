package algo.Pro원정대.ThirdDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 문제_친구만나러가는길_07 {

	static int de, y=3, x=5;
	static int[][] map = {
			{0, 0, 0, 0, -1},
			{-1, 0, -1, 0, 0},
			{0, 0, 0, 0, -1}
	};
	static int[][] visit;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0, 0, 1, -1};
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static class Node{
		int sy, sx, ey, ex, d;
		Node(int a, int b, int y, int x, int c){
			sy = a; sx = b; ey = y; ex = x; d = c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		visit = new int[y][x];
		
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int first = bfs(new Node(0, 0, a, b, 0));
		
		st = new StringTokenizer(br.readLine());
		int ty = Integer.parseInt(st.nextToken());
		int tx = Integer.parseInt(st.nextToken());

		visit = new int[y][x];
		int second = bfs(new Node(a, b, ty, tx, 0));
		
		System.out.println(first + second);
		
	}

	
	private static int bfs(Node pNode) {
		Queue<Node> q = new LinkedList<>();
		q.add(pNode);
		visit[pNode.sy][pNode.sx] = 1;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int cx = node.sx;
			int cy = node.sy;
			int dif = node.d;
			int tx = node.ex;
			int ty = node.ey;
			if(cx==2 && cy==0 || cx == 3 && cy == 1) {
				de = -1;
			}
			//목표지점 도달인 경우
			if(cx == tx && cy == ty) {
				return dif;
			}
			for(int i=0; i<4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if(nx < 0 || ny < 0 || nx >= x || ny >= y) continue;
				if(visit[ny][nx] == 1) continue;
				if(map[ny][nx] == -1) continue;
				visit[ny][nx] = 1;
				q.add(new Node(ny, nx, ty, tx, dif+1));
			}
		}
		
		return 0;
	}
}
