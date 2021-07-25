package algo.Pro원정대.세그먼트트리특강;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D01_SumQuery {
    static int N, M;
    static int[] arr;
    static long[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        tree = new long[N * 5];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            //입력 값 하나 받을 때마다 tree 값을 초기화 및 값 갱신을 한번에 처리한다.
            update(1, 0, N - 1, i, arr[i]);
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (type == 1) {
                arr[a - 1] = b;
                update(1, 0, N - 1, a - 1, b);
            } else {
                System.out.println(query(1, 0, N - 1, a - 1, b - 1));
            }
        }
    }


    private static long query(int node, int start, int end, int ts, int te) {
        if (start > te || end < ts) {
            return 0;
        }

        if (start >= ts && end <= te) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        long left = query(node * 2, start, mid, ts, te);
        long right = query(node * 2 + 1, mid + 1, end, ts, te);
        return left + right;
    }


    private static void update(int node, int start, int end, int index, int val) {
        if (start > index || end < index) {
            return;
        }

        if (start == end) {
            tree[node] = val;
            return;
        }

        int mid = (start + end) / 2;
        update(node * 2, start, mid, index, val);
        update(node * 2 + 1, mid + 1, end, index, val);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
}
