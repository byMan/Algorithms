package algo.두포인터;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_2559_수열 {
    static int N, K;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int maxSum = 0, sum = 0;
        //최초 연속 K일수만큼의 합을 구한다.
        for (int i = 0; i < K; i++) {
            sum += arr[i];
        }

        maxSum = sum;

        int start = 0, end = start + K - 1;
        while (end < N - 1) {
            sum += arr[++end];
            sum -= arr[start++];
            maxSum = Math.max(maxSum, sum);
        }

        System.out.println(maxSum);
    }
}
