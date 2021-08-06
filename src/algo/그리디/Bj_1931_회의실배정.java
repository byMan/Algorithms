package algo.그리디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Bj_1931_회의실배정 {
    static int N, ANS;
    static PriorityQueue<Node> q = new PriorityQueue<>();
    static class Node implements Comparable<Node>{
        int start, end;
        Node(int a, int b){
            start = a; end = b;
        }

        @Override
        public int compareTo(Node o) {
            if(this.end < o.end) return -1;
            if(this.end > o.end) return 1;
            if(this.end == o.end){
                if(this.start < o.start) return -1;
                if(this.start > o.start) return 1;
            }
            return 0;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());


        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            q.add(new Node(a, b));
        }

        bfs();

        System.out.println(ANS);
    }

    private static void bfs(){
        int cnt = 0;
        Node preNode = null;
        while(!q.isEmpty()){
            Node now = q.poll();
            if(preNode == null){
                cnt++;
                preNode = new Node(now.start, now.end);
            }else{
                if(preNode.end <= now.start) {
                    cnt++;
                    preNode = now;
                }
            }
        }

        ANS = cnt;
    }
}
