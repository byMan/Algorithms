package algo.그래프.다익스트라;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Bj_1504_특정한최단경로 {
    static int N, E, INF = (int)21E7;
    static int[] map, visit;
    static List<Node>[] list;
    static class Node implements Comparable<Node>{
        int end, dist;
        Node(int a, int b){
            end = a; dist = b;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        list = new List[N + 1];

        for(int i=1; i<=N; i++)
            list[i] = new ArrayList<>();

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int dist1 = dijkstra(1, v1);
        dist1 += dijkstra(v1, v2);
        dist1 += dijkstra(v2, N);

        int dist2 = dijkstra(1, v2);
        dist2 += dijkstra(v2, v1);
        dist2 += dijkstra(v1, N);

        int ret = (dist1 >= INF && dist2 >= INF) ? -1 : Math.min(dist1, dist2);
        System.out.println(ret);
    }

    private static int dijkstra(int start, int end){
        map = new int[N + 1];
        visit = new int[N + 1];
        Arrays.fill(map, INF);

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(start, 0));
        map[start] = 0;

        while(!q.isEmpty()){
            Node now = q.poll();
            int cur = now.end;
            if(cur == end){
                return map[end];
            }
            for(Node child : list[cur]){
                if(map[child.end] > map[cur] + child.dist) {
                    map[child.end] = map[cur] + child.dist;
                    q.add(new Node(child.end, map[cur] + child.dist));
                }
            }
        }

        return map[end];
    }
}
