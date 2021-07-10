package algo.Pro원정대.FirstDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문제_사원입출력관리시스템_03 {

	static int N;
	static int[] empNo = new int[10000];
	static String[] empNm = new String[10000];
	static boolean[] visit = new boolean[10000];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		String nm;
		for(int k=0; k<N; k++) {
			st = new StringTokenizer(br.readLine());
			int val = Integer.parseInt(st.nextToken());
			if(val == 1) {
				int num = Integer.parseInt(st.nextToken());
				nm = st.nextToken();
				if(exist(num)) {
					System.out.println(num + " ERROR");
				}else {
					empNo[num] = num;
					empNm[num] = nm;
					System.out.println(num + " OK");
				}
			}else {
				int num = Integer.parseInt(st.nextToken());
				if(empNo[num] == 0) {
					System.out.println(num + " ERROR");
					continue;
				}
				if(visit[num] == false) {
					visit[num] = true;
					System.out.println(num + " " + empNm[num] + " ENTER");
				}else {
					visit[num] = false;
					System.out.println(num + " " + empNm[num] + " EXIT");
				}
			}
		}
	}
	
	private static boolean exist(int num) {
		if(empNo[num] != 0)
			return true;
		return false;
	}
}
