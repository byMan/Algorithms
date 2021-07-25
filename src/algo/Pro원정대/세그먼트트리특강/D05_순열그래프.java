package algo.Pro원정대.세그먼트트리특강;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D05_순열그래프 {
    static int N;
    static int[] arr1, arr2, tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N];
        arr2 = new int[N];
        tree = new int[N * 5];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        int[] index = new int[1000001];
        //DAT, index : arr2의 숫자, value : 해당 arr2의 숫자가 있는 위치
        for(int i=0; i<N; i++){
            index[arr2[i]] = i;
        }

        long ans = 0;
        for(int i=0; i<N; i++){
            ans += query(1, 0, N - 1, index[arr1[i]], N - 1);
            update(1, 0, N - 1, index[arr1[i]], 1);
        }

        System.out.println(ans);
    }


    /**
     * arr1과 arr2의 동일 값 한 쌍을 연결하는 역할을 한다.
     * @param node
     * @param start
     * @param end
     * @param index
     * @param val
     */
    private static void update(int node, int start, int end, int index, int val){
        if(start > index || end < index){
            return;
        }

        if(start == end){
            tree[node] = val;
            return;
        }

        int mid = (start + end) / 2;
        //현재 node의 자식 노드 둘의 값을 갱신한다.
        update(node * 2, start, mid, index, val);
        update(node * 2 + 1, mid + 1, end, index, val);

        //현재 node의 자식 노드 둘의 갱신된 결과 값을 가지고 현재 node의 값을 갱신한다.
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }


    private static int query(int node, int start, int end, int ts, int te){
        if(start > te || end < ts){
            return 0;
        }

        if(start >= ts && end <= te){
            return tree[node];
        }

        int mid = (start + end) / 2;
        int left = query(node * 2, start, mid, ts, te);
        int right = query(node * 2 + 1, mid + 1, end, ts, te);
        return left + right;
    }
}
