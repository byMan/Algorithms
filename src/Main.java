import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int node;
        long weight;
        Node(int a, long b){
            node = a; weight = b;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.weight, o.weight);
        }
    }

    static int N, M, START, END;
    static int[] parent;
    static long[] dist;
    static List<Node>[] list = null;
    static List<Integer> pathList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new long[N+1];
        parent = new int[N+1];
        list = new List[N+1];

        for(int i=0; i<=N; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, c));
        }

        st = new StringTokenizer(br.readLine());
        START = Integer.parseInt(st.nextToken());
        END = Integer.parseInt(st.nextToken());

        dijkstra(START);

        bw.write(dist[END] + "\n");
        bw.write(pathList.size() + "\n");
        for(int i=pathList.size()-1; i>=0; i--){
            bw.write(pathList.get(i) + " ");
        }
        bw.flush();
    }


    private static void dijkstra(int start){
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(start, 0));
        parent[start] = 0;
        dist[start] = 0;

        while(!q.isEmpty()){
            Node now = q.poll();
            int node = now.node;
            long weight = now.weight;

            for(Node next : list[node]){
                int nextNode = next.node;
                long nextWeight = next.weight + weight;

                if(dist[nextNode] > nextWeight){
                    dist[nextNode] = nextWeight;
                    q.add(new Node(nextNode, nextWeight));
                    parent[nextNode] = node;
                }
            }
        }

        int idx = END;
        while(idx != 0){
            pathList.add(idx);
            idx = parent[idx];
        }
    }
}

