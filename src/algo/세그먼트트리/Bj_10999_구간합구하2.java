package algo.세그먼트트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_10999_구간합구하2 {
    static long[] arr, tree;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        arr = new long[n+1];
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        tree = new long[n*4];

        treeInit(1, n, 1);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m+k; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a == 1){
                long d = Integer.parseInt(st.nextToken());
                update(1, n, b, c, d);
            }else{
//                sb.append(sum(1, n, b, c, 1)).append("\n");
            }
        }
    }


    public static long treeInit(int start, int end, int node){
        if(start == end){
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return treeInit(start, mid, node*2) + treeInit(mid+1, end, node*2+1);
    }


    private static void update(int start, int end, int ts, int te, long val){
        //범위 밖이면
        if(start > te || end < ts){
            return;
        }

//        tree[node]
    }
}
