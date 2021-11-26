package algo.세그먼트트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_7578_공장_인덱스트리 {

    private static int N, NN;
    private static int[] machine;
    private static int[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        NN = 1;
        while (N > NN) {
            NN *= 2;
        }

        tree = new int[NN * 2];
        machine = new int[1000001];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int a = Integer.parseInt(st.nextToken());
            machine[a] = i;
        }

        long ans = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int a = Integer.parseInt(st.nextToken());
            // 연결(카운팅)
            update(NN + machine[a] - 1, 1);

            // 내 뒤부터 끝까지 연결된 갯수가 몇개인지 확인(교차점의 갯수)
            ans += query(1, 1, NN, machine[a] + 1, NN);
        }

//		System.out.println(Arrays.toString(tree));
        System.out.println(ans);
    }

    public static void update(int node, int val) {
        tree[node] += val;

        while (node > 1) {
            node /= 2;
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    public static int query(int node, int start, int end, int ts, int te) {
        if (start > te || end < ts) {
            return 0;
        }

        if (start >= ts && end <= te) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        int left = query(node * 2, start, mid, ts, te);
        int right = query(node * 2 + 1, mid + 1, end, ts, te);

        return left + right;
    }
}

