package algo.Pro원정대.FourthDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 문제_천지창조_01 {

	static int y, x, ans;
	static int[][] map;
	static int[][] visit;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static class Node{
		int y, x, cnt, dif;
		Node(int a, int b, int c, int d){
			y = a; x = b; cnt = c; dif = d;
		}
	}
	
	public static void main(String[] args) throws Exception {
		y = 8;
		x = 9;
		ans = Integer.MAX_VALUE;
		
		map = new int[y][x];
		visit = new int[y][x];
		
		String str = "";
		for(int i=0; i<y; i++) {
			str = br.readLine();
			for(int j=0; j<x; j++) {
				char ch = str.charAt(j);
				if(ch == '#') {
					map[i][j] = 1;
				}
			}
		}
		
//		//출력
//		for(int i=0; i<y; i++) {
//			for(int j=0; j<x; j++) {
//				System.out.print(map[i][j] + "\t");
//			}
//			System.out.println();
//		}
		
		//섬 번호 매기기
		int cnt = 2;
		for(int i=0; i<y; i++) {
			for(int j=0; j<x; j++) {
				if(map[i][j] == 1) {
					bfs(new Node(i, j, cnt++, 0));
				}
			}
		}
		

		
		
		visit = new int[y][x];
		for(int i=0; i<y; i++) {
			for(int j=0; j<x; j++) {
				if(map[i][j] > 0) {
					bfs2(new Node(i, j, map[i][j], 0));
				}
			}
		}
		
		System.out.println(ans);
	}

	
	private static int bfs2(Node pNode) {
		Queue<Node> q = new LinkedList<>();
		q.add(pNode);
		visit[pNode.y][pNode.x] = 1;
		
		int minDif = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			Node node = q.poll();
			int cx = node.x;
			int cy = node.y;
			int cnt = node.cnt;
			int dif = node.dif;
			map[cy][cx] = cnt;
			for(int i=0; i<4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if(nx < 0 || ny < 0 || nx >= x || ny >= y) continue;
				if(map[ny][nx] != 0) continue;
				if(visit[ny][nx] == 1) continue;
				if(visit[ny][nx] == 0) {
					//교차점
					if(map[cy][cx] != map[ny][nx]) {
						minDif = Math.min(minDif, dif);
					}
					continue;
				}
				visit[ny][nx] = 1;
				q.add(new Node(ny, nx, cnt, dif+1));
			}
		}
		
		return Math.min(ans, minDif);
	}
	
	
	
	/**
	 * 섬 번호 매기기
	 * @param pNode
	 */
	private static void bfs(Node pNode) {
		Queue<Node> q = new LinkedList<>();
		q.add(pNode);
		visit[pNode.y][pNode.x] = 1;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int cx = node.x;
			int cy = node.y;
			int cnt = node.cnt;
			map[cy][cx] = cnt;
			for(int i=0; i<4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if(nx < 0 || ny < 0 || nx >= x || ny >= y) continue;
				if(visit[ny][nx] == 1) continue;
				if(map[ny][nx] != 1) continue;
				visit[ny][nx] = cnt;
				q.add(new Node(ny, nx, cnt, 0));
			}
		}
	}
}

/*
[입력]
______###
______###
______###
_____####
____##___
#________
##_______
###______

[출력]
4


*/