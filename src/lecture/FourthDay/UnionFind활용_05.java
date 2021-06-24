package lecture.FourthDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UnionFind활용_05 {

	static int de;
	static String str;
	static int[] left, right;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char parent[] = new char[100];

	public static void main(String[] args) throws IOException {
		for (char ch = 'A'; ch <= 'E'; ch++)
			parent[ch] = ch;

//		parent['E'] = 'D';
//		parent['D'] = 'C';
//		parent['C'] = 'B';
//		parent['B'] = 'A';
		de = -1;

		char pa = getBoss(parent['E']); // 최종 부모 찾기

		System.out.println((char) pa);
		union('A', 'B');
		union('C', 'D');
		union('D', 'A');
		union('B', 'D');
		System.out.println(Arrays.toString(parent));
	}

	private static char getBoss(char ch) {
		if (ch == parent[ch]) {
			return ch;
		}
		return getBoss(parent[ch]);
	}

	private static void union(char a, char b) {
		char pa = getBoss(a);
		char pb = getBoss(b);
		if (pa != pb) {
			parent[pb] = pa;
		}
	}
}
