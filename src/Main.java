import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int de;
    static int n, t;
    static int[] dist;
    static List<Node>[] list;

    static class Node implements Comparable<Node>{
        int next, cost;
        Node(int a, int b){
            next = a; cost = b;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] arg) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        dist = new int[n];
        list = new ArrayList[n];

        for(int i=0; i<n; i++){
            list[i] = new ArrayList<>();
        }

        Arrays.fill(dist, Integer.MAX_VALUE);

        for(int i=0; i<t; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, cost));
        }

        bfs(0);

        if(dist[n-1] == Integer.MAX_VALUE){
            System.out.println("impossible");
        }else{
            System.out.println(dist[n-1]);
        }
    }


    private static void bfs(int start){
        PriorityQueue<Node> q = new PriorityQueue<>();
        for(Node node : list[start]){
            q.add(node);
        }
de = -1;
        while(!q.isEmpty()){
            Node node = q.poll();
            int now = node.next;
            int cost = node.cost;
            if(dist[now] < Integer.MAX_VALUE) continue;
            dist[now] = cost;
            for(Node next : list[now]){
                q.add(new Node(next.next, cost + next.cost));
            }
        }
    }
}

