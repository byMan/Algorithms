package algo.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 정올_1411_두줄로타일깔기_BottomUp {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] memo = new int[1000001];
        memo[1] = 1;
        memo[2] = 3;
        for(int i=3; i<=n; i++){
            memo[i] = memo[i-1] + (2 * memo[i-2]);
        }
        System.out.println(memo[n] % 20100529);
    }
}
