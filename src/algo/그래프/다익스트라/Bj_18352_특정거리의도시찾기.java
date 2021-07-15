package algo.그래프.다익스트라;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Bj_18352_특정거리의도시찾기 {
    static int N, M, K, X, ANS;
    static int[] dist;
    static List<Node>[] list = null;

    static class Node implements Comparable<Node>{
        int node, dist;
        Node(int a, int b){
            node = a; dist = b;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   //도시 수
        M = Integer.parseInt(st.nextToken());   //도로 수
        K = Integer.parseInt(st.nextToken());   //최단거리정보
        X = Integer.parseInt(st.nextToken());   //출발 도시 번호

        dist = new int[N+1];
        list = new List[N+1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, 1));
        }

        dijkstra(X);

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++){
            if(dist[i] == K) {
                sb.append(i).append("\n");
                cnt++;
            }
        }
        if(cnt == 0){
            bw.write("-1");
        }else {
            bw.write(sb.toString() + "\n");
        }
        bw.flush();
        bw.close();
    }



    private static void dijkstra(int start){
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(start, 0));
        dist[start] = 0;

        while(!q.isEmpty()){
            Node now = q.poll();
            int node = now.node;
            int nowDist = now.dist;
            if(nowDist > K) continue;
            for(Node next : list[node]){
                int nextNode = next.node;
                int nextDist = next.dist + nowDist;
                if(dist[nextNode] > nextDist){
                    dist[nextNode] = nextDist;
                    q.add(new Node(nextNode, nextDist));
                }
            }
        }
    }
}
