package algo.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bj_2579_계단오르기 {
    static int N;
    static int[] arr, sum;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        sum = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }


    }
}
