import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test2 {
    static int N;
    static int[] arr, tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        int[] a = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        int[] b = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            b[i] = Integer.parseInt(st.nextToken());
        }

        int index[] = new int[1000001];
        for(int i=0; i<N; i++){
            index[b[i]] = i;
        }

        long ans = 0;
        tree = new int[N*5];

        for(int i=0; i<N; i++){
            ans += query(1, 0, N-1, index[a[i]], N-1);
            update(1, 0, N-1, index[a[i]], 1);
        }

        System.out.println(ans);
    }


    private static long query(int node, int start, int end, int ts, int te){
        if(start > te || end < ts){
            return 0;
        }

        if(start >= ts && end <= te){
            return tree[node];
        }

        int mid = (start + end) / 2;
        long left = query(node*2, start, mid, ts, te);
        long right = query(node*2+1, mid+1, end, ts, te);
        return left + right;
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
        update(node * 2 + 1, mid+1, end, index, val);
        tree[node] = tree[node*2] + tree[node*2+1];
    }
}