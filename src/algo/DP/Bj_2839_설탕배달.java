package algo.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Bj_2839_설탕배달 {
    static int n;
    static int INF = (int)21E8;
    static int[] memo;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
//        solve1();
        bottomUp();
    }

    private static void bottomUp(){
        if(n < 5){
            if(n==3)
                System.out.println(1);
            else
                System.out.println(-1);
            return;
        }

        memo = new int[n+1];
        Arrays.fill(memo, INF);
        memo[3] = 1;
        memo[5] = 1;

        int a = 0, b = 0;
        for(int i=6; i<=n; i++){
            a = memo[i-3] + 1;
            b = memo[i-5] + 1;
            memo[i] = Math.min(a,b);
        }
        if(memo[n] >= INF){
            System.out.println(-1);
        }else{
            System.out.println(memo[n]);
        }
    }

    private static void solve1(){
        int cnt = 0;
        while(true){
            if(n % 5 == 0){
                System.out.println(4/5 + cnt);
                break;
            }else if(n < 0){
                System.out.println(-1);
                break;
            }
            n = n-3;
            cnt++;
        }
    }
}
