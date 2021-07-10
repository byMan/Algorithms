package algo.Pro원정대.ThirdDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 문제_안나와엘사M_07 {

	static int de, n;
	static int[][] map;
	static int[][] visit;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0, 0, 1, -1};
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static class Node{
		int y, x, sec, gbn;
		Node(int a, int b, int c, int d){
			y = a;
			x = b;
			sec = c;
			gbn = d;
		}
	}
	
	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		visit = new int[n][n];
		
		String str = "";
		for(int i=0; i<n; i++) {
			str = br.readLine();
			for(int j=0; j<n; j++) {
				char ch = str.charAt(j);
				if(ch == '#') {
					map[i][j] = -1;					
				}
			}
		}
		
		Queue<Node> q = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		q.add(new Node(y, x, 0, 2));
		visit[y][x] = 2;
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		q.add(new Node(y, x, 0, 3));
		visit[y][x] = 3;
		
		
		int minSec = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			Node node = q.poll();
			int cy = node.y;
			int cx = node.x;
			int sec = node.sec;
			int gbn = node.gbn;
			for(int i=0; i<4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
				
				if(visit[ny][nx] > 0) {
					//교차지점(교차지점에서 1초를 더해야 만나는 지점이다.. 그러므로 마지막에 결과 출력할때 1초 더하자.
					if(visit[cy][cx] != visit[ny][nx]) {
						minSec = Math.min(minSec, sec);
					}
					continue;
				}
				
				if(map[ny][nx] == 0 && visit[ny][nx] == 0) {
					visit[ny][nx] = gbn;
					q.add(new Node(ny, nx, sec + 1, gbn));
				}
			}
		}
		
		System.out.println(minSec + 1);
	}

}
