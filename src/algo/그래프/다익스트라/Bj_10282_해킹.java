package algo.그래프.다익스트라;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Bj_10282_해킹 {
    static int INF = (int)21E8;
    static int T, N, D, C;
    static int[] map, visit;
    static List<Node>[] list;
    static class Node implements Comparable<Node>{
        int com, time;
        Node(int a, int b){
            com = a; time = b;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        T = Integer.parseInt(br.readLine());

        for(int k=1; k<=T; k++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N+1];
            visit = new int[N+1];
            list = new List[N+1];

            for(int i=1; i<=N; i++)
                list[i] = new ArrayList<>();

            for(int i=0; i<D; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
//                list[a].add(new Node(b, 0, c));
                list[b].add(new Node(a, c));
            }

            dijkstra();

            int cnt = 0;
            int maxTime = Integer.MIN_VALUE;
            for(int i=1; i<=N; i++){
                if(map[i] != INF){
                    cnt++;
                    maxTime = Math.max(maxTime, map[i]);
                }
            }

            System.out.println(cnt + " " + maxTime);
        }
    }


    private static void dijkstra(){
        Arrays.fill(map, INF);
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(C, 0));
        map[C] = 0;

        while(!q.isEmpty()){
            Node now = q.poll();
            int cur = now.com;
            for(Node child : list[cur]){
                if(map[child.com] > map[cur] + child.time) {
                    map[child.com] = map[cur] + child.time;
                    q.add(new Node(child.com, map[cur] + child.time));
                }
            }
        }
    }
}
