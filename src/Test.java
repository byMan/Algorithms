import java.io.*;
import java.util.StringTokenizer;

/*
17
12
 */
public class Test {

    private static int N;
    private static int M;
    private static int K;
    private static int[] input;
    private static long[] tree;
    private static int NN;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream(new File("구간합구하기")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        NN = 1;
        while(N > NN) {
            NN *= 2;
        }
        tree = new long[NN*2];

        input = new int[N+1];

        for(int i=1; i<=N; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        initTree(1, 1, N);

        for(int i=0; i<M+K; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            if(a == 1) {
                //update
                int b = Integer.parseInt(st.nextToken());
                long c = Long.parseLong(st.nextToken());

                updateTree(1, 1, N, b, b, c);
            }else {
                //query
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                long ans = query(1, 1, N, b, c);
                bw.append(ans + "\n");
            }
        }

        bw.close();
    }

    private static void updateTree(int node, int l, int r, int ql, int qr, long value) {
        if(qr < l || r < ql) {
            return;
        }

        if(ql <= l && r <= qr) {
            tree[node] = value;
            return;
        }

        int mid = (l+r)/2;

        updateTree(2*node, l, mid, ql, qr, value);
        updateTree(2*node+1, mid+1, r, ql, qr, value);

        tree[node] = tree[2*node] + tree[2*node+1];
    }

    private static long query(int node, int l, int r, int ql, int qr) {
        if(qr < l || r < ql) {
            return 0;
        }

        if(ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = (l+r)/2;
        return query(2*node, l, mid, ql, qr) + query(2*node+1, mid+1, r, ql, qr);
    }

    private static void initTree(int node, int l , int r) {
        if(l == r) {
            tree[node] = input[l];
            return;
        }

        int mid = (l+r)/2;

        initTree(2*node, l, mid);
        initTree(2*node+1, mid+1, r);

        tree[node] = tree[2*node] + tree[2*node+1];
    }
}
