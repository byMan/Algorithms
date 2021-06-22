package algo.세그먼트트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_2042_구간합구하기 {

    static long[] arr, tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }


//        int k = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
//        int size = (int) Math.pow(2, k);
//        tree = new long[size];

        tree = new long[N * 4];

        treeInit(1, N, 1);

        System.out.println(Arrays.toString(tree));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                long diff = c - arr[b];
                arr[b] = c;
                update(1, N, 1, b, diff);
            } else if (a == 2) {
                sb.append(sum(1, N, b, (int) c, 1)).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static long treeInit(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = treeInit(start, mid, node * 2) + treeInit(mid + 1, end, node * 2 + 1);
    }


    private static void update(int start, int end, int node, int idx, long diff) {
        //범위 밖이면
        if (start > idx || end < idx) {
            return;
        }

        //범위 안에 있으면 내려가며 다른 원소도 갱신
        tree[node] += diff;

        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, idx, diff);
        update(mid + 1, end, node * 2 + 1, idx, diff);
    }

    private static long sum(int start, int end, int ts, int te, int node) {
        if (start > te || end < ts) {
            return 0;
        }

        if (start >= ts && end <= te) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return sum(start, mid, ts, te, node * 2) + sum(mid + 1, end, ts, te, node * 2 + 1);
    }
}
