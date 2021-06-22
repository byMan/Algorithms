package algo.그래프.DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Bj_2644_촌수계산 {

    static int N, p1, p2, M, ans = -1;
    static boolean[] visit;
    static ArrayList<ArrayList<Integer>> arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        p1 = Integer.parseInt(st.nextToken());
        p2 = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());

        visit = new boolean[N+1];

        arr = new ArrayList<>();
        for(int i=0; i<=N; i++){
            arr.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr.get(x).add(y);
            arr.get(y).add(x);
        }

        dfs(p2, p1, 0);

        System.out.println(ans);
    }


    private static void dfs(int start, int find, int cnt) {
        visit[start] = true;

        for(int i : arr.get(start))
            if(!visit[i]){
                if(find == i) {
                    ans = cnt + 1;
                    return;
                }
                dfs(i, find, cnt + 1);
            }
    }
}
