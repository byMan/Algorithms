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
    static int N, M, ANS;
    static int[] parent;
    static PriorityQueue<Node> q = new PriorityQueue<>();

    static class Node implements Comparable<Node>{
        int com1, com2, cost;
        Node(int a, int b, int c){
            com1 = a; com2 = b; cost = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N+1];
        for(int i=1; i<=N; i++){
            parent[i] = i;
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            q.add(new Node(a, b, c));
        }

        bfs();

        System.out.println(ANS);
    }

    private static void bfs(){

        while(!q.isEmpty()){
            Node now = q.poll();
            int a = find(now.com1);
            int b = find(now.com2);
            if(a==b) continue;
            union(a, b);
            ANS += now.cost;
        }
    }

    private static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b){
        if(a<b){
            parent[b] = a;
        }else{
            parent[a] = b;
        }
    }
}