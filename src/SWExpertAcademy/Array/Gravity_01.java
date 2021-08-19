package SWExpertAcademy.Array;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Gravity_01 {
    static int T, N, M;
    static int[] boxTop;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());
        for (int k = 0; k < T; k++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            boxTop = new int[N];
            arr = new int[M][N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                boxTop[i] = Integer.parseInt(st.nextToken());
                int num = M - boxTop[i];
                for (int j = M - 1; j >= num; j--) {
                    arr[j][i] = 1;
                }
            }

            int maxCnt = 0;
            for (int i = 0; i < M; i++) {
                int idx = M-boxTop[i];
                if(idx >= M) continue;
                if(arr[idx][i] == 1){
                    int cnt = 0;
                    for (int j = i + 1; j < N; j++) {
                        if(arr[idx][j] == 0){
                            cnt++;
                        }
                    }
                    maxCnt = Math.max(maxCnt, cnt);
                }
            }

            System.out.println(maxCnt);
        }
    }
}

/*
입력
1
9 8
7 4 2 0 0 6 0 7 0

출력
7
 */