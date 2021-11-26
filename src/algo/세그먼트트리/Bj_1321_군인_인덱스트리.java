package algo.세그먼트트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_1321_군인_인덱스트리 {

    private static int N, K, NN;
    private static int LIMIT = 65537;
    private static long ANS;
    private static int[] arr;
    private static long[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        NN = 1;
        while (LIMIT > NN) {
            NN *= 2;
        }
        tree = new long[NN * 2];
        arr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 2개만 먼저 넣는다.
        for (int i = 1; i < K; i++) {
            // arr 값이 0일때 NN 위치에 값이 들어가야한다. 그리고 그때 카운트를 1 올려준다.
            update(NN + arr[i], 1);
        }

        // 이제 K번째 숫자를 넣으면서 중앙값을 구하고 슬라이딩 윈도우를 활용하여 다음칸으로 한칸씩 전진하면서 중앙값을 구한다.
        for (int i = K; i <= N; i++) {
            // K번째 숫자를 추가하기
            update(NN + arr[i], 1);

            // 중앙값 구하기
            ANS += query(1, 1, NN, (K + 1) / 2);

            // K개를 유지하기 위해 맨 처음 입력받았던 수를 제외시킨다.
            update(NN + arr[i - K + 1], -1);
        }

        System.out.println(ANS);
    }

    private static long query(int node, int start, int end, long target) {
        if (start == end) {
            //현재 위치는 인덱스트리 leaf 노드의 시작위치가 0부터 시작한 경우이므로 -1을 해줘야 현재 위치의 leaf 노드 위치이다.
            return start - 1;
        }

        int mid = (start + end) / 2;

        if (tree[node * 2] >= target) {
            //중앙값이 왼쪽 자식에 있는 경우, 왼쪽으로 탐색
            return query(node * 2, start, mid, target);
        } else {
            //중앙값이 오른쪽 자식에 있는 경우, 왼쪽 자식의 갯수를 빼고 오른쪽 자식으로 탐색
            return query(node * 2 + 1, mid + 1, end, target - tree[node * 2]);
        }
    }

    private static void update(int node, int val) {
        // 카운트를 누적시킨다.
        tree[node] += val;

        while (node > 1) {
            node /= 2;
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

}

