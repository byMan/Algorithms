import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Test {
    static int n, m;
    static int[] dist, route;
    static List<Node>[] list;
    static ArrayList<Integer> arr = new ArrayList<>();

    static class Node implements Comparable<Node>{
        int node, cost;
        Node(int a, int b){
            node = a; cost = b;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        list = new List[n+1];
        dist = new int[n+1];
        route = new int[n+1];

        for(int i=0; i<=n; i++){
            list[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        int a, b, c;
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        bfs(start, end);

        System.out.println(dist[end]);

        int node = end;
        while(node !=0){
            arr.add(node);
            node = route[node];
        }

        System.out.println(arr.size());
        for(int i=arr.size()-1; i>=0; i--){
            System.out.print(arr.get(i) + " ");
        }
    }


    private static void bfs(int start, int end){
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(start, 0));
        dist[start] = 0;
        route[start] = 0;

        while(!q.isEmpty()){
            Node now = q.poll();
            int node = now.node;
            int cost = now.cost;
            if(node == end) break;
            for(Node childNode : list[node]){
                int nextNode = childNode.node;
                int nextCost = childNode.cost + cost;
                if(dist[nextNode] > nextCost){
                    dist[nextNode] = nextCost;
                    q.add(new Node(nextNode, nextCost));
                    route[nextNode] = node;
                }
            }
        }

        System.out.println();
        System.out.println(Arrays.toString(route));
    }
}