package algo.그래프.최소비용신장트리_MST_Kruskal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 최소 비용 신장 트리 알고리즘은 크루스칼 알고리즘과 프림 알고리즘이 있다.
 * 이 둘의 차이점은 아래와 같다.
 * 크루스칼 -> 간선을 가지고 오름 차순 정렬하여 차례대로 선택한다. 그러므로 간선이 적은 경우에 사용하기 적합하다
 * 프림 -> 노드를 하나 하나 방문하여 해당 노드에 연결된 간선 중 가장 비용이 적은 간선을 선택하는 구조로 간선이 많은 경우 사용하기 적합하다.
 */
public class Bj_1922_네트워크_크루스칼 {
    static int n, m, ans;
    static int[] parent;
    static PriorityQueue<Node1922k> q = new PriorityQueue<Node1922k>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        parent = new int[n+1];
        for(int i=1; i<=n; i++){
            parent[i] = i;
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            q.add(new Node1922k(s, e, v));
        }

        while (!q.isEmpty()) {
            Node1922k node = q.poll();
            int a = find(node.s);
            int b = find(node.e);
            if(a==b) continue;
            union(a, b);
            ans += node.v;
        }

        System.out.println(ans);
    }

    private static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    private static void union(int s, int e){
        if(s < e){
            parent[e] = s;
        }else{
            parent[s] = e;
        }
    }
}
class Node1922k implements Comparable<Node1922k>{
    int s;
    int e;
    int v;
    public Node1922k(int s, int e, int v){
        this.s = s;
        this.e = e;
        this.v = v;
    }
    @Override
    public int compareTo(Node1922k o) {
        return this.v - o.v;
    }
}