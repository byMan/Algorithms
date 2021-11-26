package algo.세그먼트트리.lazy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_10999_구간합구하기2 {
    private static int N, M, K;
    private static long[] arr, tree, lazy;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        tree = new long[N * 4];
        lazy = new long[N * 4];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Long.parseLong(st.nextToken());
        }

        init(1, 1, N);

        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            /*
                기존 세그먼트 트리에서 하나의 값을 변경할 때에는 O(logN)의 시간이 걸렸다.
                이 알고리즘을 활용하여 구간의 값들을 변경하면 (c-b+1)만큼 변경해야하므로 O(logN)만큼 걸리게 되고 TLE가 된다.
                이것을 해결하기 위해 Lazy Propergation을 활용해야한다.
                O(logN)^2 이 걸린다.
             */
            if (a == 1) {
                long d = Long.parseLong(st.nextToken());
                updateRange(1, 1, N, b, c, d);  //b번째 수부터 c번째 수에 d를 더한다.
            }else{
                System.out.println(sum(1, 1, N, b, c)); //b번째 수부터 c번째 수까지 합을 구한다.
            }
        }
    }

    private static long init(int node, int start, int end) {
        if(start == end)
            return tree[node] = arr[start];

        int mid = (start + end) / 2;
        long left = init(node * 2, start, mid);
        long right = init(node * 2 + 1, mid + 1, end);

        return tree[node] = left + right;
    }


    private static void updateRange(int node, int start, int end, int left, int right, long diff) {
        updateLazy(node, start, end);   //해당 노드에 lazy가 있다면 업데이트

        if(left > end || right < start) return;

        //범위에 해당될 때
        if (left <= start && right >= end) {
            //해당하는 노드가 담당하는 노드의 총 갯수 * 더하거나 빼야야 할 값
           tree[node] += (end - start + 1) * diff;
           //leaf노드가 아닌 경우
            if (start != end) {
                lazy[node * 2] += diff;   //자식들에게 lazy 값을 물려준다.
                lazy[node * 2 + 1] += diff;
            }
            return;
        }

        int mid = (start + end) / 2;
        updateRange(node * 2, start, mid, left, right, diff);
        updateRange(node * 2 + 1, mid + 1, end, left, right, diff);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }


    public static void updateLazy(int node, int start, int end) {
        //lazy 값이 0이 아닌 경우만 수행
        if (lazy[node] != 0) {
            //해당하는 노드가 담당하는 노드의 총 갯수 * 해당 노드의 lazy 값
            tree[node] += (end - start + 1) * lazy[node];

            //leaf노드가 아닌 경우
            if (start != end) {
                //자식에게 lazy 값을 물려준다.
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            //자식에게 lazy 값을 물려준 후 해당 노드의 lazy 값은 초기화 한다.
            lazy[node] = 0;
        }
    }


    private static long sum(int node, int start, int end, int left, int right) {
        //남은 lazy들을 udpate 시켜준다.
        updateLazy(node, start, end);

        if(left > end || right < start) return 0;

        //범위 안에 있는 경우만
        if(start >= left && end <= right)
            return tree[node];

        int mid = (start + end) / 2;
        long leftValue = sum(node * 2, start, mid, left, right);
        long rightValue = sum(node * 2 + 1, mid + 1, end, left, right);
        return leftValue + rightValue;
    }
}
