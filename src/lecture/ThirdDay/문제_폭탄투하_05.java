package lecture.ThirdDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문제_폭탄투하_05 {
	static char[][] map;
	static boolean[][] visit;
	static int[] dx = {1, 1, -1, -1, 0, 0, 1 ,-1};
	static int[] dy = {1, -1, -1, 1, 1, -1, 0, 0}; 
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		map = new char[4][5];
		visit = new boolean[4][5];
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<5; j++) {
				map[i][j] = '_';
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		
		visit[y][x] = true;
		dfs(y, x);
		
		st = new StringTokenizer(br.readLine());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		
		visit[y][x] = true;
		dfs(y, x);
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<5; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

	private static void dfs(int y, int x) {
		for(int i=0; i<8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || ny < 0 || nx >= 5 || ny >= 4) continue;
			if(visit[ny][nx]) continue;
			visit[ny][nx] = true;
			map[ny][nx] = '#';
//			dfs(ny, nx);
		}
	}
}
