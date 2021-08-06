import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test2 {
    static int[] arr, tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());

        arr = new int[n];
        tree = new int[n*5];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        initTree(1, 0, n-1);

        int m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a == 1){
                arr[b-1] = c;
                update(1, 0, n-1, b-1, c);
            }else{
                int ret = query(1, 0, n-1, b-1, c-1);
                System.out.println(ret);
            }
        }
    }

    private static void initTree(int node, int start, int end){
        if(start == end){
            tree[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        initTree(node * 2, start, mid);
        initTree(node * 2 + 1, mid+1, end);
        tree[node] = Math.min(tree[node * 2], tree[node*2+1]);
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
        tree[node] = Math.min(tree[node * 2], tree[node*2+1]);
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