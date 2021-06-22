package algo.그래프.최소비용신장트리_MST_Kruskal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Bj_1197_최소스패닝트리 {
    static int n, m, ans;
    static int[] parent;
    static PriorityQueue<Node> q = new PriorityQueue<Node>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];

        for(int i=0; i<=n; i++) {
            parent[i] = i;
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            q.add(new Node(a, b, v));
        }


        while(!q.isEmpty()) {
            Node node = q.poll();
            int a = find(node.s);
            int b = find(node.e);
            if(a==b)	continue;
            union(a, b);
            ans += node.v;
        }

        System.out.println(ans);
    }

    private static int find(int a) {
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        if(a<b) {
            parent[b] = a;
        }else {
            parent[a] = b;
        }
    }
}

class Node implements Comparable<Node>{
    int s;
    int e;
    int v;
    public Node(int s, int e, int v) {
        this.s = s;
        this.e = e;
        this.v = v;
    }
    @Override
    public int compareTo(Node o) {
        return this.v - o.v;
    }

}

