package algo.Pro_기출문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Pro_0507_자물쇠 {
    static int[][] arr, sum;
    static int row, col, ans;
    static ArrayList<Integer>[] list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str;
        int t = Integer.parseInt(br.readLine());
        for(int k=1; k<=t; k++) {
            st = new StringTokenizer(br.readLine());
            col = Integer.parseInt(st.nextToken());
            row = Integer.parseInt(st.nextToken());

            ans = Integer.MAX_VALUE;
            arr = new int[row][col];
            sum = new int[row][col];

            list = new ArrayList[col];
            for(int i=0; i<col; i++){
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < row; i++) {
                str = br.readLine();
                for (int j = 0; j < col; j++) {
                    int v = str.charAt(j) - '0';
                    arr[i][j] = v;
                    sum[i][j] = Integer.MAX_VALUE;
                    if(v == 1) {
                        list[j].add(i);     //열에 해당하는 row값을 저장
                    }
                }
            }

            for(int i=0; i<col; i++){
                //해당 열에 1의 갯수만큼 반복, rowIdx는 1이 있는 row index 값
                for(int rowIdx : list[i]) {
                    //1이 위치한 곳과 row간 단순 거리차이
                    for (int j = 0; j < row; j++) {
                        sum[j][i] = Math.min(sum[j][i], Math.abs(rowIdx - j));
                    }

                    //0번인덱스에서 맨 아래 인덱스로 가는 경우 더 짧은 거리인지 체크
                    int v = 1;
                    for (int j = row-1; j >= 0; j--) {
                        sum[j][i] = Math.min(sum[j][i], rowIdx + v++);
                    }

                    //맨 아래 인덱스에서 0번째 인덱스 방향으로 회전하는 경우
                    v = row;
                    for(int j=0; j<row; j++){
                        sum[j][i] = Math.min(sum[j][i], v - rowIdx);
                    }
                }
            }

            for(int i=0; i<row; i++){
                int tmp = 0;
                for(int j=0; j<col; j++){
                    if(arr[i][j] == 0) {
                        tmp += sum[i][j];
                    }
                }
                ans = Math.min(ans, tmp);
            }

            System.out.println("#" + k + " " + ans);

            //for debug용
//            for(int i=0; i<row; i++){
//                for(int j=0; j<col; j++){
//                    System.out.print(sum[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();

        }
    }

}


/*

자물쇠의 현재 상태를 입력 받아 자물쇠를 열기 위한 총 클릭수의 최솟값을 계산하는 프로그램을 작성하라.

[제한 조건]
링의 개수 N은 1이상 100,000 이하의 정수
각 링의 칸의 개수 K는 1이상 100,000 이하의 정수
N * K 는 최대 1,000,000 임을 보장한다.
각 링에는 1이 최소 1칸 이상 표시되어 있다.

[입력 조건]
첫째줄에서 테스트 케이스 T 가 입력 된다.
두번째 줄에는 링의 개수 N, 링의 칸의 개수 K 가 입력된다.
다음줄부터 K 줄 까지 자물쇠의 정보가 입력 된다.

[입력]
4
5 4
01000
00110
10001
01001
6 7
000110
000010
010000
000010
100010
001011
000011
10 3
1000010010
0011000101
0100111001
4 5
0010
1001
0100
0100
0011
6 7
010110
100010
010000
000010
100010
001011
001011


[출력]
#1 3
#2 6
#3 5
#4 2
#5 3
 */