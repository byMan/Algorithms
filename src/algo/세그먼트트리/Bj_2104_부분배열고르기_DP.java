package algo.세그먼트트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_2104_부분배열고르기_DP {
    static int N;
    static int[] input;
    static long[] preSum;
    static long result = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        input = new int[N + 1];
        input[0] = 0;
        preSum = new long[N + 1];
        preSum[0] = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            preSum[i] = preSum[i - 1] + input[i];
        }

        searchMaxScore(1, N);

        System.out.print(result);
    }

    private static void searchMaxScore(int i, int j) {
        int minScoreIndex = j;
        for (int k = i; k < j; k++) {
            if (input[minScoreIndex] > input[k]) minScoreIndex= k;
        }

        long score = (preSum[j] - preSum[i - 1]) * input[minScoreIndex];
//        if (result < score) result = score;
        result = Math.max(result, score);

        //왼쪽에 더 남아 있다면
        if (i < minScoreIndex) searchMaxScore(i, minScoreIndex - 1);

        //오른쪽에 더 남아 있다면
        if (minScoreIndex < j) searchMaxScore(minScoreIndex + 1, j);
    }
}
