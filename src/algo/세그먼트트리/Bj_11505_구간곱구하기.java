package algo.세그먼트트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_11505_구간곱구하기 {
    static long[] arr, tree;
    static int MOD = 1000000007;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        arr = new long[n+1];
        for(int i=1; i<=n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        tree = new long[n * 4];

        init(1, n, 1);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m+k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if(a==1){
                arr[b] = c;
                update(1, n, 1, b, c);
            }else if(a==2){
                sb.append(mul(1, n, b, (int)c, 1) + "\n");
            }
        }

        System.out.println(sb.toString());
    }

    public static long init(int start, int end, int node){
        if(start == end){
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        return tree[node] = (init(start, mid, node*2) * init(mid+1, end, node*2+1)) % MOD;
    }

    public static long update(int start, int end, int node, int idx, long val){
        //범위 밖
        if(idx < start || idx > end){
            return tree[node];
        }

        //리프 노드 업데이트
        if(start == end){
            return tree[node] = val;
        }

        int mid = (start + end) / 2;
        return tree[node] = (update(start, mid, node*2, idx, val) * update(mid+1, end, node*2+1, idx, val)) % MOD;
    }

    public static long mul(int start, int end, int ts, int te, int node){
        //범위 밖
        if(te < start || ts > end){
            return 1;
        }

        if(start >= ts && end <= te){
            return tree[node];
        }

        int mid = (start + end) / 2;
        return (mul(start, mid, ts, te, node*2) * mul(mid+1, end, ts, te, node*2+1)) % MOD;
    }
}
