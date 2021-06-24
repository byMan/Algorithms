package lecture.FirstDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문제_어설픈모내기_2교시_02 {
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<y; i++) {
			int cnt = 0;
			String str = br.readLine();
			for(int j=0; j<x; j++) {
				int val = str.charAt(j) - '0';
				if(val > 0) cnt ++;
			}
			System.out.println(cnt);
		}
	}

}
