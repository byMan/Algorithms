package algo.인덱스트리;

import java.io.*;
import java.util.StringTokenizer;

public class Bj_2042_구간합구하기 {

    static int N, M, K;
    static long[] arr, tree;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        tree = new long[N * 4];

        for (int i = 1; i <= N; i++) {
            long x = Integer.parseInt(br.readLine());
            arr[i] = x;
            update(i, x);
        }

        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (count++ < M + K) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                update(b, c - arr[b]);
                arr[b] = c;
            } else {
                sb.append(sum(b, (int) c)).append("\n");
            }
        }

        System.out.println(sb);
    }

    /**
     * i 번째 수를 dif만큼 더하는 함수
     *
     * @param i
     * @param dif
     */
    private static void update(int i, long dif) {
        while (i <= N) {
            tree[i] += dif;
            i += (i & -i);  //i가 홀수이면 1을 더하는 결과가 나오고, i가 짝수이면 자기 자신값이 나온다.
        }
    }

    private static long prefixSum(int i) {
        long result = 0;
        while (i > 0) {
            result += tree[i];
            i -= (i & -i);
        }
        return result;
    }

    private static long sum(int start, int end) {
        return prefixSum(end) - prefixSum(start - 1);
    }
}
