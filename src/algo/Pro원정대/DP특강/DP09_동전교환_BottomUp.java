package algo.Pro원정대.DP특강;

import java.util.Scanner;

public class DP09_동전교환_BottomUp {
    static int[] coin;
    static int target;
    static int n;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        target = sc.nextInt();
        n = sc.nextInt();
        coin = new int[n];
        for (int i = 0; i < n; i++) {
            coin[i] = sc.nextInt();
        }
        sc.close();

        dp = new int[n + 1][target + 1];

        for (int i = 0; i <= target; i++) {
            dp[0][i] = (int) (21e8);
        }

        for (int y = 1; y <= n; y++) {

            for (int x = 1; x <= target; x++) {
                dp[y][x] = dp[y - 1][x];

                if (x < coin[y - 1]) continue;

                int up = dp[y - 1][x];
                int left = dp[y][x - coin[y - 1]] + 1;

                if (up > left) dp[y][x] = left;
            }

        }

        if (dp[n][target] == (int) 21e8) {
            System.out.println("impossible");
            return;
        }

        System.out.println(dp[n][target]);
    }
}