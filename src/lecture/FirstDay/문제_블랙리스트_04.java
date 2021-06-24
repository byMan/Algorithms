package lecture.FirstDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문제_블랙리스트_04 {
	static int y, x;
	static int[][] List = new int[100001][2];
	static int[][] map;
	static int[][] blackList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		int aa = y;
		int bb = x;
		map = new int[y][x];
		for(int i=0; i<y; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<x; j++) {
				int val = Integer.parseInt(st.nextToken());
				map[i][j] = val;
				List[val][0]++;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		blackList = new int[y][x];
		
		for(int i=0; i<y; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<x; j++) {
				int val = Integer.parseInt(st.nextToken());
				blackList[i][j] = val;
				List[val][1] = 1;
			}
		}
		
		int blackListCnt = 0;
		for(int i=0; i<100001; i++) {
			if(List[i][1] > 0) {
				blackListCnt += List[i][0];
			}
		}
		
		
		System.out.println(blackListCnt);
		System.out.println(aa * bb - blackListCnt);
	}

}
