package algo.세그먼트트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Bj_10868_최소값 {
    static int N, M;
    static int[] map, tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1];
        tree = new int[(int)Math.pow(2, Math.ceil(Math.log(N) / Math.log(2)) + 1)];

        for(int i=1; i<=N; i++){
            map[i] = Integer.parseInt(br.readLine());
        }

        treeInit(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int ts = Integer.parseInt(st.nextToken());
            int te = Integer.parseInt(st.nextToken());
            sb.append(getMin(1, N, ts, te, 1)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }


    /**
     * Tree 노드에서 leaf 노드를 제외 한 노드는 자식 노드 중 최소값을 갖도록 설정한다.
     * @param start : 탐색 시작
     * @param end : 탐색 끝
     * @param node : 노드 index
     * @return : 자식 노드 중 최소값
     */
    private static int treeInit(int start, int end, int node){
        if(start == end){
            tree[node] = map[start];
        }else{
            int mid = (start + end) / 2;
            tree[node] = Math.min(treeInit(start, mid, node * 2), treeInit(mid + 1, end, node * 2 + 1));
        }
        return tree[node];
    }


    /**
     * 자식 노드 중에서 구간의 최소 값을 찾아 리턴한다.
     * @param start : 탐색 시작
     * @param end : 탐색 끝
     * @param ts : 구간 시작
     * @param te : 구간 끝
     * @param node : 노드 index
     * @return : 자식 노드 중 최소값
     */
    private static int getMin(int start, int end, int ts, int te, int node) {
        if(start >= ts && end <= te){
            return tree[node];
        }
        if(start > te || end < ts){
            return Integer.MAX_VALUE;
        }
        int mid = (start + end) / 2;
        return Math.min(getMin(start, mid, ts, te, node * 2),
                        getMin(mid + 1, end, ts, te, node * 2 + 1));
    }
}
