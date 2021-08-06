package algo.그래프.다익스트라;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Bj_1238_파티 {
    static int N, M, X;
    static int[] map, visit;
    static List<Node>[] list;
    static class Node implements Comparable<Node>{
        int end, dist;
        Node(int a, int b){
            end = a; dist = b;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        list = new List[N + 1];

        for(int i=1; i<=N; i++)
            list[i] = new ArrayList<>();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, c));
        }

        int maxVal = Integer.MIN_VALUE;
        for(int i=1; i<=N; i++){
            int ret = bfs(i, X);
            ret += bfs(X, i);
            maxVal = Math.max(maxVal, ret);
        }
        System.out.println(maxVal);
    }


    private static int bfs(int start, int end){
        map = new int[N + 1];
        Arrays.fill(map, Integer.MAX_VALUE);
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(start, 0));
        map[start] = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();
            int node = now.end;
            int dist = now.dist;
//            print(map, start, end);
            if(node == end){
                return dist;
            }
            for(Node next : list[node]){
                if(map[next.end] > map[node] + next.dist){
                    map[next.end] = map[node] + next.dist;
                    q.add(new Node(next.end, map[node] + next.dist));
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    private static void print(int[] arr, int start, int end){
        System.out.println();
        System.out.println("start : " + start + "\tend : " + end);
        System.out.println(Arrays.toString(arr));
    }
}
