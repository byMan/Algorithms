package algo.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 정올_3522_Tutorial동적계획법_TopDown {
    static long[] memo = new long[100001];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        System.out.println(fibonachi(n) % 1000000007);
    }

    private static long fibonachi(int n){
        if(n==0) return 0;
        if(n==1) return 1;
        if(memo[n] != 0) return memo[n];

        long a = fibonachi(n-1);
        long b = fibonachi(n-2);

        memo[n] = a + b;
        return memo[n];
    }
}
