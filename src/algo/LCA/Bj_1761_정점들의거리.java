package algo.LCA;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_1761_정점들의거리 {


    public static int N, M, dp[][], depth[], dist[];
    public static ArrayList<ArrayList<Point>> list;
    public static boolean vtd[];
    public static int maxlevel = 16;

    public static void dfs(int cur, int parent) {
        vtd[cur] = true;

        depth[cur] = depth[parent] + 1;

        for(Point next: list.get(cur)) {
            if(!vtd[next.x]) {
                vtd[next.x] = true;
                dp[next.x][0] = cur;
                dist[next.x] = dist[cur] + next.y;
                dfs(next.x, cur);
            }
        }
    }

    public static void solve() {
        for(int y = 1; y <= maxlevel; y++) {
            for(int x = 1; x <= N; x++) {
                dp[x][y] = dp[dp[x][y-1]][y-1];
            }
        }
    }

    public static int lca(int x, int y) {
        //x를 더 깊은 depth로 가정
        if(depth[x] < depth[y]) {
            int tmp = y;
            y = x;
            x = tmp;
        }

        for(int i = maxlevel; i >= 0; i--) {
            if(Math.pow(2, i) <= depth[x] - depth[y]) {
                x = dp[x][i];
            }
        }

        if(x == y) return x;
        else {
            for(int i = maxlevel; i >= 0; i--) {
                if(dp[x][i] != dp[y][i]) {
                    x = dp[x][i];
                    y = dp[y][i];
                }
            }
            x = dp[x][0];
        }
        return x;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        vtd = new boolean[N+1];
        dp = new int[N+1][maxlevel+1];
        depth = new int[N+1];
        dist = new int[N+1];

        for(int i = 0; i <= N; i++) list.add(new ArrayList<>());

        for(int i = 0; i < N-1; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.get(x).add(new Point(y,v));
            list.get(y).add(new Point(x,v));
        }

        dfs(1, 0);

        //dfs 결과값 출력
        System.out.println("vdt \t" + Arrays.toString(vtd));
        System.out.println("depth \t" + Arrays.toString(depth));
        System.out.println("dist \t" + Arrays.toString(dist));
        for(int i=0; i<N; i++){
            System.out.println("dp["+i+"]\t" + Arrays.toString(dp[i]));
        }

        solve();

        //solve() 결과값 출력
        System.out.println("\n\nsolve 결과");
        for(int i=0; i<N; i++){
            System.out.println("dp["+i+"]\t" + Arrays.toString(dp[i]));
        }

        st = new StringTokenizer(bf.readLine().trim());
        M = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int res = lca(x, y);
            sb.append(dist[x] + dist[y] - 2*dist[res]).append("\n");
        }

        System.out.println(sb);
    }
}
