package algo.그래프.최소비용신장트리_MST_Kruskal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Bj_16398_행성연결 {
    static int n;
    static long ans;
    static int[] parent;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        parent = new int[n];
        for(int i=0; i<n; i++){
            parent[i] = i;
        }

        PriorityQueue<NodeT2> queue = new PriorityQueue<>();
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                int v = Integer.parseInt(st.nextToken());
                if(v==0) continue;
                queue.add(new NodeT2(i, j, v));
            }
        }

        while (!queue.isEmpty()) {
            NodeT2 node = queue.poll();
            int s1 = find(node.s);
            int e1 = find(node.e);
//            System.out.println("node.v="+node.v+"\tnode.s="+node.s + "\tnode.e="+node.e + "\ts1="+s1 +"\te1="+e1);
            if(s1 == e1) continue;
            union(s1, e1);
            ans += node.v;
        }

        System.out.println(ans);
    }

    private static int find(int a) {
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b){
        if(a < b) {
            parent[b] = a;
        }else{
            parent[a] = b;
        }
    }
}

class NodeT2 implements Comparable<NodeT2>{
    int s;
    int e;
    int v;
    public NodeT2(int s, int e, int v){
        this.s = s;
        this.e = e;
        this.v = v;
    }
    @Override
    public int compareTo(NodeT2 o) {
        return this.v - o.v;
    }
}
