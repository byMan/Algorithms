package algo.세그먼트트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_10999_구간합구하기2 {
    static int N;
    static int Q;
    static int cal;
    static int list[];
    static long tree[];
    static long lazy[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());

        list = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = Integer.parseInt(br.readLine());
        }

        int size = 1;
        while (size < N * 2) {
            size <<= 1;
        }
        cal = size / 2 - 1;

        tree = new long[size];
        lazy = new long[size];

        makeInit(cal + 1, size - 1, 1);


        for (int i = 0; i < Q; i++) {

            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            if (g == 1) {
                int left = Integer.parseInt(st.nextToken()) + cal;
                int right = Integer.parseInt(st.nextToken()) + cal;
                long value = Integer.parseInt(st.nextToken());
                update(cal + 1, size - 1, left, right, value, 1);
            } else {
                int left = Integer.parseInt(st.nextToken()) + cal;
                int right = Integer.parseInt(st.nextToken()) + cal;
                long answer = sum(cal + 1, size - 1, left, right, 1);
                System.out.println(answer);
            }


        }


    }

    private static long sum(int start, int end, int left, int right, int node) {
        lazy_update(node, start, end);

        if (left > end || right < start) return 0;
        if (left <= start && right >= end) return tree[node];

        int mid = (start + end) / 2;
        return sum(start, mid, left, right, node * 2) + sum(mid + 1, end, left, right, node * 2 + 1);

    }

    private static void print() {
        for (int i = 1; i <= 15; i++) {
            System.out.print(tree[i] + " ");
        }
        System.out.println();
        for (int i = 1; i <= 15; i++) {
            System.out.print(lazy[i] + " ");
        }
        System.out.println();
        System.out.println("######################");
    }

    private static void update(int start, int end, int left, int right, long value, int node) {
        lazy_update(node, start, end);

        if (left > end || right < start) return;

        if (left <= start && right >= end) {
            tree[node] += (end - start + 1) * value;
            if (start != end) {
                lazy[node * 2] += value;
                lazy[node * 2 + 1] += value;
            }
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, left, right, value, node * 2);
        update(mid + 1, end, left, right, value, node * 2 + 1);

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static void lazy_update(int node, int start, int end) {
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];
            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    private static void makeInit(int start, int end, int node) {
        if (start == end) {
            if (start - cal > N) {
                tree[node] = 0;
            } else {
                tree[node] = list[start - cal];
            }
            return;
        }
        int mid = (start + end) / 2;
        makeInit(start, mid, node * 2);
        makeInit(mid + 1, end, node * 2 + 1);

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

}
