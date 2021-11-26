package algo.세그먼트트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Bj_2042_구간합구기_인덱스트리 {
    private static int N, M, K, NN;
    private static long ANS;
    private static int[] arr;
    private static long[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N+1];

        NN = 1;
        while(N > NN) {
            NN *= 2;
        }
        tree = new long[NN*2];

        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        treeInit();

        for(int i=0; i<M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a == 1) {
                long c = Long.parseLong(st.nextToken());
                update(NN+b-1, c);
            }else {
                int c = Integer.parseInt(st.nextToken());
                ANS = query(1, 1, NN, b, c);
                System.out.println(ANS);
            }
        }
    }


    private static void treeInit() {
        //leaf 노드부터 값을 채울 것이므로 leaf 노드의 제일 왼쪽 아래 지점부터 인덱스를 1씩 증가시키며 값을 채운다.
        for(int i=1; i<=N; i++) {
            tree[NN+i-1] = arr[i];
        }

        //leaf 노드들의 부모 노드들의 값도 채운다.
        for(int i=NN-1; i>=1; i--) {
            tree[i] = tree[i*2] + tree[i*2+1];
        }
    }


    private static void update(int node, long val) {
        //leaf 노드의 값을 갱신한다.
        tree[node] = val;

        //leaf 노드에 해당하는 부모노드 값 갱신을 진행한다.
        while(node > 1) {
            node /= 2;
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }


    private static long query(int node, int start, int end, int ts, int te) {
        if(start > te || end < ts) {
            return 0;
        }

        if(start >= ts && end <= te) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        long left = query(node * 2, start, mid, ts, te);
        long right = query(node * 2 + 1, mid + 1, end, ts, te);

        return left + right;
    }
}

