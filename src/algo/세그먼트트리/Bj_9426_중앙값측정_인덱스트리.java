package algo.세그먼트트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_9426_중앙값측정_인덱스트리 {

    private static int N, K, NN, LIMIT = 65537;
    private static int[] input, tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        input = new int[N + 1];
        NN = 1;
        while (LIMIT > NN) {
            NN *= 2;
        }
        tree = new int[NN * 2];

        for (int i = 1; i <= N; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i < K; i++) {
            // K-1개까지 트리에 업데이트
            update(input[i] + NN, 1);
        }

        long ans = 0;
        for (int i = K; i <= N; i++) {
            // K번째 값 트리에 업데이트
            update(input[i] + NN, 1);

            // 트리에 K개의 값이 업데이트 되었으므로 중앙값 탐색
            ans += query(1, 1, NN, (K + 1) / 2);

            // 현재 input값 위치에서 K-1번째 이전의 값 트리에서 제거
//			update(input[i - K + 1], -1);
            update(input[i - K + 1] + NN, -1);
        }

        System.out.println(ans);
    }

    private static void update(int node, int val) {
        tree[node] += val;

        while (node > 1) {
            node /= 2;
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    private static int query(int node, int start, int end, int target) {
        if (start == end) {
            return start - 1;
        }

        int mid = (start + end) / 2;

        if (tree[node * 2] >= target) {
            // 중앙값이 왼쪽 자식에 있는 경우, 왼쪽으로 탐색
            return query(node * 2, start, mid, target);
        } else {
            // 중앙값이 오른쪽 자식에 있는 경우, 왼쪽 자식의 갯수를 빼고 오른쪽 자식으로 탐색
            return query(node * 2 + 1, mid + 1, end, target - tree[node * 2]);
        }
    }
}

