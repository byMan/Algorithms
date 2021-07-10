package algo.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bj_11726_2xn타일링_BottomUp {
    static int[] memo = new int[1001];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(bottomUp(n));
//        System.out.println(topDown(n));
    }


    private static int bottomUp(int n){
        memo[0] = 1;
        memo[1] = 1;
        for(int i=2; i<=n; i++){
            memo[i] = memo[i-1] + memo[i-2];
            memo[i] %= 10007;
        }
        return memo[n];
    }


    private static int topDown(int n){
        if(n == 0 || n == 1) return 1;

        //메모이제이션으로 불필요한 반복 재귀 호출을 줄여준다.
        if(memo[n] != 0) return memo[n];

        int a = topDown(n-1);
        int b = topDown(n-2);

        memo[n] = a + b;

        return memo[n] % 10007;
    }
}
