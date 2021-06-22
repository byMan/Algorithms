package algo.그래프.최소공통조상_LCA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_11437_LCA {
    static int n;
    static ArrayList<ArrayList<Integer>> list;
    static int[] depth, parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        //트리 생성
        list = new ArrayList<>();
        for(int i=0; i<=n; i++){
            list.add(new ArrayList<>());
        }

        //나와 연결된 노드를 저장
        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }

        //깊이, 부모 찾기
        depth = new int[n+1];
        parent = new int[n+1];
        dfs(1,1);

        System.out.println();
        System.out.println(Arrays.toString(depth));
        System.out.println(Arrays.toString(parent));

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(solve(a, b));
        }
    }

    /**
     *
     * @param node : 방문 노드
     * @param cnt : 현재 깊이
     */
    private static void dfs(int node, int cnt){
        depth[node] = cnt;
        //node와 연결된 것들 중에
        for(int child : list.get(node)){
            //깊이 계산이 안 된 곳은 자식 노드이므로
            if(depth[child] == 0){
                dfs(child, cnt+1);
                parent[child] = node;
            }
        }
    }


    private static int solve(int a, int b){
        //a가 b보다 더 밑에 있다면 같은 층으로 만들기
        while(depth[a] > depth[b]){
            a = parent[a];
        }

        //b가 a보다 더 밑에 있다면 같은 층으로 만들기
        while(depth[a] < depth[b]){
            b = parent[b];
        }

        //같은 층인데 부모가 같지 않다면 같은 부모를 찾을 때 까지 반복한다.
        while(a != b){
            a = parent[a];
            b = parent[b];
        }

        return a;
    }
}
