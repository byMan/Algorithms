package lecture.FourthDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UnionFind활용_06 {
	static int de;
	static String str;
	static int groupCnt;
	static int[] left, right;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char parent[] = new char[100];
	
	public static void main(String[] args) throws Exception {
			
		for(char ch = 'A'; ch <= 'Z'; ch ++) {
			parent[ch] = ch;
			groupCnt++;
		}
		
        // 26 개 그룹 
        setUnion('A', 'B'); 
        setUnion('C', 'D'); 
        setUnion('D', 'A'); 
        setUnion('K', 'G'); 
        setUnion('H', 'I'); 
        setUnion('I', 'G'); 
        setUnion('G', 'D'); 
        
        
        System.out.println(groupCnt);
        System.out.println(Arrays.toString(parent));
	}

	
	private static char find(char ch) {
		if (ch == parent[ch]) {
			return ch;
		}
		
		//경로 압축으로 검색 속도를 logN으로 줄인다.
		parent[ch] = find(parent[ch]);
		
		return parent[ch];
	}

	private static void setUnion(char a, char b) {
		char pa = find(a);
		char pb = find(b);
		if (pa != pb) {
			parent[pb] = pa;
			groupCnt--;
		}
	}
}
