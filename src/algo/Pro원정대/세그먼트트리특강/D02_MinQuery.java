package algo.Pro원정대.세그먼트트리특강;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D02_MinQuery {
    static int N, M;
    static int[] arr, tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        tree = new int[N*5];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] =  Integer.parseInt(st.nextToken());
            update(1, 0, N-1, i, arr[i]);
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int ret = query(1, 0, N-1, a-1, b-1);
            System.out.println(ret);
        }
    }


    private static void update(int node, int start, int end, int index, int val){
        if(start > index || end < index){
            return;
        }

        if(start == end){
            tree[node] = val;
            return;
        }

        int mid = (start + end) / 2;
        update(node * 2, start, mid, index, val);
        update(node * 2 + 1, mid + 1, end, index, val);
        tree[node] = Math.min(tree[node*2], tree[node*2+1]);
    }

    private static int query(int node, int start, int end, int ts, int te){
        if(start > te || end < ts){
            return Integer.MAX_VALUE;
        }

        if(start >= ts && end <= te){
            return tree[node];
        }

        int mid = (start + end) / 2;
        int left = query(node * 2, start, mid, ts, te);
        int right = query(node * 2 + 1, mid + 1, end, ts, te);
        return Math.min(left, right);
    }
}
