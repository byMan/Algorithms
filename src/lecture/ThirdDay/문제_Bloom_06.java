package lecture.ThirdDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 문제_Bloom_06 {
	
	static int de;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0, 0, 1, -1};
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static class Node {
		int y, x;
		int level;
		Node(int a, int b, int c){
			y = a;
			x = b;
			level = c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		
		map = new int[row][col];
		visit = new boolean[row][col];
		
		Queue<Node> q = new LinkedList<>();
		
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
	
		visit[a][b] = true;
		q.add(new Node(a, b, 1));
		
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		visit[a][b] = true;
		q.add(new Node(a, b, 1));
		
		int days = 0;
		while(!q.isEmpty()) {
			Node node = q.poll();
			int y = node.y;
			int x = node.x;
			map[y][x] = node.level;
			int level = node.level;
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || ny < 0 || nx >= col || ny >= row) continue;
				if(visit[ny][nx]) continue;
				visit[ny][nx] = true;
				q.add(new Node(ny, nx, level + 1));
			}
			days = level;
		}
		
		System.out.println(days);
	}

}
