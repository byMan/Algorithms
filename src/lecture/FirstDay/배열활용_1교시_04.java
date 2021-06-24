package lecture.FirstDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 배열활용_1교시_04 {
	static int[] arr = new int[] {3,2,1,7,4,5};
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int in = Integer.parseInt(br.readLine());
		System.out.println(existYn(in));
	}

	private static String existYn(int in) {
		for(int i=0; i<arr.length; i++) {
			if(in == arr[i]) {
				return "존재";
			}
		}
		
		return "미존재";
	}
}
