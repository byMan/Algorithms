package lecture.FourthDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문제_순환회로검사_07 {
	static int de, n;
	static int[][]map;
	static int[] parent;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		parent = new int[n];
		for(int i=0; i<n; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		de = -1;
		for(int i=0; i<n; i++) {
			//인접행렬의 반쪽만 읽어서 처리한다.
			for(int j=i; j<n; j++) {
				if(map[i][j] == 1) {
					int pa = find(i);
					int pb = find(j);
					if(pa == pb) {
						System.out.println("WARNING");
						return;
					}
					union(i,j);
				}
			}
		}
		
		System.out.println("STABLE");
	}

	
	private static int find(int a) {
		if(a == parent[a]) return a;
		return parent[a] = find(parent[a]);
	}
	
	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa != pb) {
			parent[pb] = pa;
		}
	}
}
