package lecture.FirstDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문제_아파트인테리어패턴_04 {

	static int[][] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
	
		arr = new int[y][x];
		
		int cnt = 1;
		for(int i=0; i<y; i++) {
			boolean isSame = true;
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<x; j++) {
				int val = Integer.parseInt(st.nextToken());
				arr[i][j] = val;
				if(i>0 && arr[0][j] != arr[i][j]) {
					isSame = false;
				}
			}
			if(i>0 && isSame) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}
