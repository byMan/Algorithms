package algo.세그먼트트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_1395_스위치 {
    static int Tree[];
    static int laze[];
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Tree = new int[N * 4];
        laze = new int[N * 4];
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            if (o == 0) {
                // 반전
                update(1, N, s, t, 1);
            } else {
                // sum
                System.out.println(sum(1, N, s, t, 1));
            }
        }
    }

    public static int sum(int start, int end, int s, int t, int n) {
        lazeUpdate(start, end, n);
        if (t < start || end < s) {
            return 0;
        }

        if (s <= start && end <= t) {
            return Tree[n];
        }

        int mid = (start + end) / 2;

        return sum(start, mid, s, t, n * 2) + sum(mid + 1, end, s, t, n * 2 + 1);

    }

    public static void update(int start, int end, int s, int t, int n) {
        lazeUpdate(start, end, n);
        if (t < start || end < s)
            return;

        if (s <= start && end <= t) {
            // update
            laze[n] += 1;
            lazeUpdate(start, end, n);
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, s, t, n * 2);
        update(mid + 1, end, s, t, n * 2 + 1);
        Tree[n] = Tree[n * 2] + Tree[n * 2 + 1];

    }

    public static void lazeUpdate(int start, int end, int n) {
        if (laze[n] == 0)
            return;
        if (laze[n] % 2 == 1) {
            Tree[n] = (end-start + 1) - Tree[n];
        }

        if (start != end) {
            laze[n * 2] += laze[n];
            laze[n * 2 + 1] += laze[n];
        }
        laze[n] = 0;

    }
}