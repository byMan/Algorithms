package algo.Pro원정대.ThirdDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문제_사랑의스튜디오_02 {
	static int N;
	static int[] numArr;
	static int[][] map;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		numArr = new int[N];
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int val = Integer.parseInt(st.nextToken());
				map[i][j] = val;
				if(val > 0) {
					numArr[j]++;
				}
			}
		}
		
		int minIdx = -1, maxIdx = -1;
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		
		for(int i=0; i<N; i++) {
			if(min > numArr[i]) {
				min = numArr[i];
				minIdx = i;
			}
			if(max < numArr[i]) {
				max = numArr[i];
				maxIdx = i;
			}
		}
		
		System.out.println(maxIdx + " " + minIdx);
	}

}
