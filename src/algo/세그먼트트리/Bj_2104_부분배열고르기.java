package algo.세그먼트트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_2104_부분배열고르기 {
    private static int N;
    private static long result;
    private static int[] input;
    private static long[] preSum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        input = new int[N + 1];
        preSum = new long[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            preSum[i] = preSum[i-1] + input[i];
        }

        searchMaxScore(1, N);

        System.out.println(result);
    }


    private static void searchMaxScore(int start, int end) {
        int minScoreIndex = end;
        for (int i = start; i < end; i++) {
            if(input[minScoreIndex] > input[i]) minScoreIndex = i;
        }

        long score = (preSum[end] - preSum[start - 1]) * input[minScoreIndex];

        result = Math.max(result, score);

        //minScoreIndex 좌측 구간에서 다시 한번 더 확인한다.
        if (start < minScoreIndex) {
            searchMaxScore(start, minScoreIndex - 1);
        }

        //minScoreIndex 우측 구간에서 다시 한번 더 확인한다.
        if (minScoreIndex < end) {
            searchMaxScore(minScoreIndex + 1, end);
        }
    }
}
