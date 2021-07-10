package algo.Pro원정대.DP특강;

import java.util.Scanner;

public class DP04_슈퍼마리오_TopDown {
    static int n;
    static int[] arr;

    static int[] memo;  //메모이제이션

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n + 10];
        memo = new int[n + 10];

        for (int i = 0; i < n + 10; i++) {
            memo[i] = -99999999;
        }

        for (int i = 0; i < n; i++) {
            arr[i + 1] = sc.nextInt();
        }
        sc.close();

        int max = -9999999;
        for (int i = n + 1; i <= n + 7; i++) {
            int ret = getMaxPoint(i);
            if (ret > max) max = ret;
        }
        System.out.println(max);
    }


    static int getMaxPoint(int idx) {
        if (idx == 0) return 0;
        if (idx < 0) return -9999999;
        if (memo[idx] != -99999999) return memo[idx];

        int a = getMaxPoint(idx - 2);
        int b = getMaxPoint(idx - 7);

        int maxab = a;
        if (maxab < b) maxab = b;

        memo[idx] = maxab + arr[idx];
        return memo[idx];
    }
}
