package algo.두포인터;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Bj_3649_로봇프로젝트 {
    static int X, N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        while((str = br.readLine()) != null) {
            X = Integer.parseInt(str) * 10000000;
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(arr);

            boolean flag = false;
            int left = 0, right = N - 1;
            while (left < right) {
                int sum = arr[left] + arr[right];
                if (sum == X) {
                    System.out.println("yes " + arr[left] + " " + arr[right]);
                    flag = true;
                    break;
                } else if (sum < X) {
                    left++;
                } else {
                    right--;
                }
            }
            if (!flag)
                System.out.println("danger");
        }
    }
}
