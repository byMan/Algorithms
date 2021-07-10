package algo.Pro원정대.DP특강;

import java.util.Scanner;

public class DP05_동전교환_TopDown {
    static int target;
    static int[] coin;
    static int n;
    static int[] memo;  //메모이제이션

    static int getMinCnt(int tar)
    {
        if (tar == 0) return 0;
        if (tar < 0) return (int)21e8;
        if (memo[tar] != -1) return memo[tar];

        int min = (int)21e8; //21억
        for (int i = 0; i<n; i++) {
            int ret = getMinCnt(tar - coin[i]) + 1;
            if (min > ret) min = ret;
        }

        memo[tar] = min;
        return min;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        target = sc.nextInt();
        n = sc.nextInt();

        coin = new int[n];
        memo = new int[3300];

        for (int i = 0; i < 3300; i++) memo[i] = -1;

        for (int i = 0; i<n; i++) {
            coin[i] = sc.nextInt();
        }
        sc.close();

        int ret = getMinCnt(target);
        if (ret == (int)21e8) {
            System.out.println("impossible");
            return;
        }
        System.out.println(ret);
    }
}
