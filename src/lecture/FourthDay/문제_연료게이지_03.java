package lecture.FourthDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 문제_연료게이지_03 {

	static int n;
	static int[] arr;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(br.readLine());
		
		String str = "";
		for(int i=0; i<n; i++) {
			str = br.readLine();
			int size = str.length();
			arr = new int[size];
			int cnt = 1;
			for(int j=0; j<size; j++) {
				char ch = str.charAt(j);
				if(ch == '#') {
					arr[j] = 0; 
				}else {
					arr[j] = cnt++;
				}
			}
			
			Arrays.sort(arr);
			
			int idx = -1;
			if(arr[arr.length-1] == 0) {
				idx = -2;
			}else if(arr[0] == 1){
				idx = -1;
			} else {
				idx = find(1);
			}
			
			if(idx == -1) {
				System.out.println("0%");
			}else if(idx == -2){
				System.out.println("100%");
			}else {
				System.out.println((idx * 100) / arr.length + "%");
			}
		}
	}

	
	private static int find(int val) {
		int start = 0;
		int end = arr.length;
		int mid = (start + end) / 2;
		
		while(start <= end) {
			if(arr[mid] == val) {
				return mid;
			}
			
			if(arr[mid] < val) {
				start = mid + 1;
			}else if(arr[mid] > val) {
				end = mid - 1;
			}
			
			mid = (start + end) / 2;
		}
		
		return -1;
	}
}
