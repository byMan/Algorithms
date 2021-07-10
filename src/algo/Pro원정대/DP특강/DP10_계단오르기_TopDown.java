package algo.Pro원정대.DP특강;

import java.util.Scanner;

public class DP10_계단오르기_TopDown {
    static int n;
    static int[] map;
    static int[][] memo;

    static int getMaxPoint(int idx, int cnt) {
        if (idx == 0) return 0;
        if (idx < 0) return (int) -21e8;
        if (memo[cnt][idx] != 0) return memo[cnt][idx];

        int a = 0;
        int b = 0;
        if (cnt == 0) {
            a = getMaxPoint(idx - 2, 0);
            b = getMaxPoint(idx - 2, 1);
        } else if (cnt == 1) {
            a = getMaxPoint(idx - 1, 0);
        }

        int maxab = a;
        if (maxab < b) maxab = b;

        memo[cnt][idx] = maxab + map[idx];
        return memo[cnt][idx];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            map[i] = sc.nextInt();
        }
        sc.close();

        memo = new int[2][n + 2];

        int r1 = getMaxPoint(n + 1, 0);
        int r2 = getMaxPoint(n + 1, 1);

        if (r1 > r2) System.out.println(r1);
        else System.out.println(r2);
    }
}

