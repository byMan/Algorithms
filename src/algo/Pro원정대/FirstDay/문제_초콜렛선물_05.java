package algo.Pro원정대.FirstDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 문제_초콜렛선물_05 {
	
	static char[] arr = new char[200];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		for(int i=0; i<str.length(); i++) {
			char aa = str.charAt(i);
			arr[aa] = 1;
		}
		
		StringBuilder sb = new StringBuilder();
		for(char ch='A'; ch<='Z'; ch++) {
			if(arr[ch] > 0) {
				sb.append(ch);
			}
		}
		
		System.out.println(sb);
	}

}
