package algo.세그먼트트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_2042_구간합구하기_인덱스트리 {

    private static int n, m, k;
    private static long[] arr = new long[10];
    private static long[] tree = new long[10];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            long val = Long.parseLong(br.readLine());
            arr[i] = val;
            update(i, val);
        }

        int count = 0;
        while (count++ < m + k) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());

            if (op == 1) {
                int index = Integer.parseInt(st.nextToken());
                long val = Long.parseLong(st.nextToken());
                //바뀐 크기(dif)만큼 적용
                update(index, val - arr[index]);
                arr[index] = val;
            } else {
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                System.out.println(intervalSum(start, end));
            }
        }
    }


    // i번째 수를 dif만큼 더하는 함수
    private static void update(int i, long dif) {
        while (i <= n) {
            tree[i] += dif;
            i += (i & -i);
        }
    }

    //start부터 end까지의 구간 합을 계산하는 함수
    private static long intervalSum(int start, int end) {
        return prefixSum(end) - prefixSum(start - 1);
    }


    // i번째 수까지의 누적 합을 계산하는 함수
    private static long prefixSum(int i) {
        long result = 0;
        while (i > 0) {
            result += tree[i];
            //0이 아닌 마지막 비트만큼 빼가면서 이동
            i -= (i & -i);
        }
        return result;
    }
}
