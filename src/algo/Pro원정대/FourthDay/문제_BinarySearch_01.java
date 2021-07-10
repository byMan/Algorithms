package algo.Pro원정대.FourthDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 문제_BinarySearch_01 {
	
	static int de;
	static int in, k;
	static int[] arr, find;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		in = Integer.parseInt(br.readLine());
		arr = new int[in];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<in; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		k = Integer.parseInt(br.readLine());
		find = new int[k];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<k; i++) {
			find[i] = Integer.parseInt(st.nextToken());
			if(findVal(find[i], in)) {
				System.out.print("O");
			}else {
				System.out.print("X");
			}
		}
	}

	
	private static boolean findVal(int findVal, int len) {
		int start = 0;
		int end = len;
		int mid = (start + end) / 2;
		
		while(start <= end) {
			if(arr[mid] == findVal) {
				return true;
			}
			
			if(arr[mid] > findVal) {
				end = mid - 1;
			}else if(arr[mid] < findVal) {
				start = mid + 1;
			}
			
			mid = (start + end) / 2;
		}
		return false;
	}
}
