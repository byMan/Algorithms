package algo.Pro원정대.DP특강;

import java.util.Scanner;

public class DP03_슈퍼마리오_BottomUp {
    static int n;
    static int[] arr;
    static int[] best;

    public static void main(String[] args) {

        Scanner sc =new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n + 10];
        best = new int[n + 10];

        for (int i = 0; i < n; i++) {
            arr[i + 1] = sc.nextInt();
        }
        sc.close();

        //깃발에 도달하려면 N번째 위치가 아니라 N번째 다음으로 한번 더 점프를 해야하므로 최대 점프하는 7까지만큼을 더 구해야 한다.
        for (int i = 1; i <= n + 7; i++) {
            int a = -99999999;
            int b = -99999999;
            if (i - 2 >= 0) a = best[i - 2];
            if (i - 7 >= 0) b = best[i - 7];

            int sum = arr[i];
            if (a >= b) sum += a;
            else sum += b;

            best[i] = sum;
        }

        int max = -9999999;
        for (int i = n; i <= n + 7; i++) {
            if (max < best[i]) max = best[i];
        }

        System.out.println(max);
    }
}
