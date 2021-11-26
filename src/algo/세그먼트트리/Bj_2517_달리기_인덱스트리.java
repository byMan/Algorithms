package algo.세그먼트트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Bj_2517_달리기_인덱스트리 {
    private static int N, NN;
    private static int[] tree, result;
    private static Runner[] input;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        input = new Runner[N];
        result = new int[N + 1];

        NN = 1;
        while (N > NN) {
            NN *= 2;
        }

        tree = new int[NN * 2];

        for (int i = 0; i < N; i++) {
            int speed = Integer.parseInt(br.readLine());
            input[i] = new Runner(i + 1, speed);
        }

        Arrays.sort(input);

        for (Runner r : input) {
            //현재 내 위치 앞까지의 합을 구한 후 + 1을 하여 내 등수를 구한다.
            int rank = query(1, 1, NN, 1, r.num - 1) + 1;

            //현재 나의 등수가 카운트 되었으므로 1로 업데이트 해준다.
            update(NN + r.num - 1, 1);

            //입력 순서에 맞춰 등수가 표시 되도록 하기 위해
            result[r.num] = rank;
        }

        //결과 출력...
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append("\n");
        }

        System.out.println(sb.toString());
    }


    private static void update(int node, int val) {
        //leaf 노드 값을 대체한다.
        tree[node] = val;

        //leaf 노드와 연관 있는 부모 노드들의 값을 갱신한다.
        while (node > 1) {
            node /= 2;
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }


    private static int query(int node, int start, int end, int ts, int te) {
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


    static class Runner implements Comparable<Runner> {
        int num;
        int speed;

        Runner(int num, int speed) {
            this.num = num;
            this.speed = speed;
        }

        @Override
        public int compareTo(Runner o) {
            return o.speed - this.speed;
        }
    }

}
