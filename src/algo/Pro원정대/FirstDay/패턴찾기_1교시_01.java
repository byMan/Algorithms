package algo.Pro원정대.FirstDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 패턴찾기_1교시_01 {
	static int[] in = new int[] { 1, 2, 3 };
	static int[] arr = new int[] {1,2,3,1,2,3,1,2};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int flag = 0;
		for(int i=0; i<arr.length; i++) {
			if(arr[i] == 1 && (i+1) < arr.length && arr[i+1] != 2) {
				flag = 1;
				break;
			}
			if(arr[i] == 2 && (i+1) < arr.length && arr[i+1] != 3) {
				flag = 1;
				break;
			}
			if(arr[i] == 3 && (i+1) < arr.length && arr[i+1] != 1) {
				flag = 1;
				break;
			}
		}
		
		if(flag == 1) {
			System.out.println("패턴이 일치하지 않음");
		}else {
			System.out.println("패턴이 일치");
		}
	}
}

/*

in 배열의 숫자 패턴이 일치하는지 여부를 판단하는 프로그램을 작성하세요.

*/