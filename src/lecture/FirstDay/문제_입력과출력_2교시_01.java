package lecture.FirstDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class 문제_입력과출력_2교시_01 {
	static int type, N;
	static int[] arr;
	static String[] strArr;
	static int[][] arr2;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		type = Integer.parseInt(br.readLine());
		
		if(type == 1) {
			type1Input();
		}else if(type == 2) {
			type2Input();
		}else {
			type3Input();
		}
		
		
	}
	private static void type3Input() throws IOException {
		int min = Integer.MAX_VALUE;
		int[] arrVal = new int[101];
		st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		arr2 = new int[y][x];
		for(int i=0; i<y; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<x; j++) {
				int val = Integer.parseInt(st.nextToken());
				arr2[i][j] = val;
				if(min > val) {
					min = val;
				}
				arrVal[val]++;
			}
		}
		System.out.println(min);
		System.out.println(arrVal[min] + "개");
	}
	private static void type2Input() throws IOException {
		N = Integer.parseInt(br.readLine());
		strArr = new String[N];
		String minStr="", maxStr="", str = "";
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			str = br.readLine();
			strArr[i] = str;
			if(max < str.length()) {
				max = str.length();
				maxStr = str;
			}
			if(min > str.length()) {
				min = str.length();
				minStr = str;
			}
		}
		System.out.println(maxStr);
		System.out.println(minStr);
	}
	private static void type1Input() throws IOException {
		N = Integer.parseInt(br.readLine());
		long hap=0, gop=1;
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			hap += arr[i];
			gop *= arr[i];
		}
		System.out.println(hap + " " + gop);
	}

}

/*
입력과 출력
설명

Build Test 및 입출력 연습을 위한 문제입니다.



C++은 빠른 입출력을 위해 scanf / printf 를 사용하고,

Java는 빠른 입출력을 위해 BufferedReader / BufferedWriter 사용을 권합니다.



Type을 입력받고,

Type에 맞는 입력과 출력을 해주세요.


입력
첫 줄에는 Type을 입력받습니다.

각 Type에 맞는 입출력 처리를 해주시면 됩니다.



[Type 1]

수 n을 입력 받습니다. (1 <= n <= 10)

다음 줄에는 n개의 수를 입력받습니다. (-9 <= 수 <= 9)

출력결과는전체 합과 전체 곱입니다.



[Type 2]

숫자 n을 입력 받고, n개의 모두 다른 길이의 문자열을 입력받습니다.(1 <= n <= 100)

가장긴 문자열과, 가장 길이가짧은 문자열을 출력합니다.



[Type 3]

Y, X 두 수를 입력 받습니다.(1 <= Y, X <= 100)

다음 줄 부터는 (Y * X) 개의 2차원 배열을 입력 받습니다.(0 <= 배열 값 <= 100)

2차원 배열에서가장 작은 값을 찾아 출력합니다.

다음 줄에는가장 작은 수의 개수를 출력합니다.


출력
Type 1, 2, 3 에 대한 결과를 출력하세요.


[입력1]
1
5
1 2 3 4 5

[출력1]
15 120

[입력2]
2
4
FIGHT
show
OPERATION
DISONW

[출력2]
OPERATION
show

[입력3]
3
3 4
0 4 2 5
7 5 0 3
2 4 2 0

[출력3]
*/