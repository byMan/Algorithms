package algo.Pro원정대.FifthDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문제_FireFighter {
	
	static int n, fy, fx ;
	static int[][]map, visit;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static class Node{
		int y, x, d1;
		Node(int a, int b, int c){
			y = a; x = b; d1 = c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		visit = new int[n][n];
		
		Node[] node = new Node[3];
		
		int nodeCnt = 0;
		String str = "";
		for(int i=0; i<n; i++) {
			str = br.readLine();
			for(int j=0; j<n; j++) {
				char ch = str.charAt(j);
				if(ch == '#') {
					map[i][j] = -1;
				}else if(ch == 'A') {
					node[nodeCnt++] = new Node(i, j, 0);
				}else if(ch == '$') {
					fy = i;
					fx = j;
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
	}

}
