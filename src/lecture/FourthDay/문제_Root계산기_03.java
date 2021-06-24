package lecture.FourthDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 문제_Root계산기_03 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());
		System.out.println(find(n));
	}

	
	
	private static int find(int val) {
		int start = 0;
		int end = 10000;
		int mid = 0;
		int res = 0;
		while(start <= end) {
			mid = (start + end) / 2;
			if(mid * mid <= val) {
				res = mid;
				start = mid + 1;
			}else if (mid * mid > val) {
				end = mid - 1;
			}
		}
		return res;
	}
}
