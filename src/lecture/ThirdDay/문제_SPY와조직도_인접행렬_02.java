package lecture.ThirdDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문제_SPY와조직도_인접행렬_02 {

	static int N;
	static int[] numArr= {1004, 1680, 9941, 3367, 3261, 2976, 4889, 1234, 6461, 7329, 5518};
	static int[][] map;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	
	public static void main(String[] args) throws Exception {
		N = 11;
		map = new int[N+1][N+1];
		map[0][1] = 1;
    	map[0][2] = 1;
    	map[1][3] = 1;
    	map[1][4] = 1;
    	map[2][5] = 1;
    	map[2][6] = 1;
    	map[4][7] = 1;
    	map[4][8] = 1;
    	map[5][9] = 1;
    	map[5][10] = 1;
    	
    	int empNo = Integer.parseInt(br.readLine());
		
    	int nowIdx = 0;
    	
    	boolean notExistYn = true;
    	for(int i=0; i<N; i++) {
    		if(empNo == numArr[i]) {
    			nowIdx = i;
    			notExistYn = false;
    			break;
    		}
    	}
    	
    	if(notExistYn) {
    		System.out.println("no person");
    		return;
    	}
    	
    	int bossIdx=0, company=0, junior=0;
    	
    	//boss 찾기
    	if(nowIdx - 1 < 0) {
    		System.out.println("no boss");
    	}else {
    		bossIdx = findBoss(nowIdx);
    		System.out.println(numArr[bossIdx]);
    	}
    	
    	//company 찾기
    	if(nowIdx - 1 < 0) {
    		System.out.println("no company");
    	}else {
    		findCompany(bossIdx, nowIdx);
    	}
    	
    	//부하직원 찾기
    	if(nowIdx + 1 >= N || nowIdx >= N ) {
    		System.out.println("no junior");
    	}else {
    		findJunior(nowIdx);
    	}
	}

	
	private static int findBoss(int nowIdx) {
		for(int i=0; i<=N; i++) {
			if(map[i][nowIdx] == 1) {
				return i;
			}
		}
		return -1;
	}
	
	private static void findCompany(int bossIdx, int nowIdx) {
		boolean noOne = true;
		for(int i=0; i<=N; i++) {
			if(map[bossIdx][i] == 1 && i != nowIdx) {
				noOne = false;
				System.out.print(numArr[i] + " ");
			}
		}
		if(noOne) {
			System.out.print("no company");
		}
		System.out.println();
	}
	
	private static void findJunior(int nowIdx) {
		boolean noOne = true;
		for(int i=0; i<=N; i++) {
			if(map[nowIdx][i] == 1) {
				noOne = false;
				System.out.print(numArr[i] + " ");
			}
		}
		
		if(noOne) {
			System.out.print("no junior");
		}
		System.out.println();
	}
}
