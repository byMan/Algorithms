package algo.Pro원정대.FirstDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 배열활용_1교시_01 {

	static int[] arr = new int[] { 3,2,1,1,5 };
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int in = Integer.parseInt(br.readLine());
		System.out.println(count(in) + "개");
	}

	private static int count(int in) {
		int result = 0;
		
		for(int i=0; i<arr.length; i++)
			if(in == arr[i])
				result++;
		
		return result;
	}
}
