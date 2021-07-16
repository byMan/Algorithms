package algo.세그먼트트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Bj_2357_최소값_최대값 {
    static int N, M;
    static int[] map, minTree, maxTree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1];
        minTree = new int[N*4];
        maxTree = new int[N*4];

        for(int i=1; i<=N; i++){
            map[i] = Integer.parseInt(br.readLine());
        }

        minTree(1, N, 1);
        maxTree(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int ts = Integer.parseInt(st.nextToken());
            int te = Integer.parseInt(st.nextToken());
            int min = getMin(1, N, ts, te, 1);
            int max = getMax(1, N, ts, te, 1);
            sb.append(min).append(" ").append(max).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }


    private static int minTree(int start, int end, int node){
        if(start == end){
            return minTree[node] = map[start];
        }
        int mid = (start + end) / 2;
        int left = minTree(start, mid, node*2);
        int right = minTree(mid+1, end, node*2+1);
        minTree[node] = left < right ? left : right;
        return minTree[node];
    }

    private static int maxTree(int start, int end, int node){
        if(start == end){
            return maxTree[node] = map[start];
        }
        int mid = (start + end ) / 2;
        int left = maxTree(start, mid, node*2);
        int right = maxTree(mid+1, end, node*2+1);
        maxTree[node] = left > right ? left : right;
        return maxTree[node];
    }

    private static int getMin(int start, int end, int ts, int te, int node){
        if(start > te || end < ts){
            return Integer.MAX_VALUE;
        }
        if(start >= ts && end <= te){
            return minTree[node];
        }
        int mid = (start + end) / 2;
        int left = getMin(start, mid, ts, te, node*2);
        int right = getMin(mid+1, end, ts, te, node*2+1);
        int min = left < right ? left : right;
        return min;
    }

    private static int getMax(int start, int end, int ts, int te, int node){
        if(start > te || end < ts){
            return Integer.MIN_VALUE;
        }
        if(start >= ts && end <= te){
            return maxTree[node];
        }
        int mid = (start + end) / 2;
        int left = getMax(start, mid, ts, te, node*2);
        int right = getMax(mid+1, end, ts, te, node*2+1);
        int max = left > right ? left : right;
        return max;
    }
}
