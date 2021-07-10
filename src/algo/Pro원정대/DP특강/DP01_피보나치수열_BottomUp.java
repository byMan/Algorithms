package algo.Pro원정대.DP특강;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DP01_피보나치수열_BottomUp {
    static int N;
    static int[] num;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        num = new int[N+1];

        num[0] = 0;
        num[1] = 1;
        for(int i=2; i<=N; i++){
            num[i] = num[i-1] + num[i-2];
        }

        System.out.println(num[N-1]);
    }
}
