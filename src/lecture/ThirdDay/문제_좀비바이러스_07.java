package lecture.ThirdDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 문제_좀비바이러스_07 {
		static int de, r, c, tot;
		static int[][] map;
		static int[][] visit;
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
			c = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			
			map = new int[r][c];
			visit = new int[r][c];
			
			String str = "";
			for(int i=0; i<r; i++) {
				str = br.readLine();
				for(int j=0; j<c; j++) {
					int val = str.charAt(j) - '0';
					map[i][j] = val; 
					if(val == 1)
						tot++;
				}
			}
			
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			a--;
			b--;
			Queue<Node> q = new LinkedList<>();
			q.add(new Node(b, a, 3));
			visit[b][a] = 3;

			
			int days = 0;
			while(!q.isEmpty()) {
				Node node = q.poll();
				int y = node.y;
				int x = node.x;
				int level = node.level;
				tot--;
				for(int i=0; i<4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if(nx < 0 || ny < 0 || nx >= c || ny >= r) continue;
					if(visit[ny][nx] == 0 && map[ny][nx] == 1) {
						visit[ny][nx] = level + 1;
						q.add(new Node(ny, nx, level + 1));
					}
				}
				days = level;
			}
			
			de = -1;
			
			System.out.println(days);
			System.out.println(tot);
		}

	}
