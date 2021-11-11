package algo.그래프.BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Bj_2644_촌수계산 {

    static int N, M, start, end;
    static int[] dist;
    static List<Integer>[] list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        dist = new int[N + 1];
        Arrays.fill(dist, -1);

        list = new List[N+1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        int res = bfs();
        System.out.println(res);
    }


    private static int bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        dist[start] = 0;
        while (!q.isEmpty()) {
            Integer now = q.poll();

            if(now == end){
                break;
            }

            for(Integer child : list[now]){
                if(dist[child] != -1 ) continue;
                dist[child] = dist[now] + 1;
                q.add(child);
            }
        }

        return dist[end];
    }
}
