package algo.그래프.최소비용신장트리_MST_Prim;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/**
 * 최소 비용 신장 트리 알고리즘은 크루스칼 알고리즘과 프림 알고리즘이 있다.
 * 이 둘의 차이점은 아래와 같다.
 * 크루스칼 -> 간선을 가지고 오름 차순 정렬하여 차례대로 선택한다. 그러므로 간선이 적은 경우에 사용하기 적합하다
 * 프림 -> 노드를 하나 하나 방문하여 해당 노드에 연결된 간선 중 가장 비용이 적은 간선을 선택하는 구조로 간선이 많은 경우 사용하기 적합하다.
 */
public class Bj_1922_네트워크_프림 {
    static boolean[] visit;
    static LinkedList<Node>[] list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        visit = new boolean[N+1];
        list = new LinkedList[N+1];
        for(int i=1; i<=N; i++){
            list[i] = new LinkedList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[s].add(new Node(e, v));
            list[e].add(new Node(s, v));
        }

        visit[1] = true;

        PriorityQueue<Node> queue = new PriorityQueue<>();
        for(Node node : list[1]){
            queue.add(node);
        }

        long ans = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int nx = node.nx;
            int val = node.val;
            if(visit[nx]) continue;
            visit[nx] = true;
            ans += val;

            for(Node childNode : list[nx]){
                if(!visit[childNode.nx])
                    queue.add(childNode);
            }
        }

        System.out.println(ans);
    }

    static class Node implements Comparable<Node>{
        int nx;
        int val;
        public Node(int nx, int val){
            this.nx = nx;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }
}