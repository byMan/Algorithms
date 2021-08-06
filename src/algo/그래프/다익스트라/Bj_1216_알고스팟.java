package algo.그래프.다익스트라;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Bj_1216_알고스팟 {
    static int N, M, ANS;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map, visit;
    static class Node implements Comparable<Node> {
        int y, x, cnt;
        Node(int a, int b, int c){
            y = a; x = b; cnt = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.cnt - o.cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new int[N][M];

        String str = "";
        for(int i=0; i<N; i++){
            str = br.readLine();
            for(int j=0; j<M; j++){
                int a = str.charAt(j) - '0';
                map[i][j] = a;
            }
        }

        bfs();
        System.out.println(ANS);
    }

    private static void bfs(){
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(0, 0, 0));
        visit[0][0] = 1;

        while (!q.isEmpty()) {
            Node now = q.poll();
            int y = now.y;
            int x = now.x;
            int cnt = now.cnt;
            if(y == N-1 && x == M-1){
                ANS = cnt;
                return;
            }
            for(int i=0; i<4; i++){
                int ny = y + dy[i];
                int nx = x + dx[i];
                if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if(visit[ny][nx] == 1) continue;
                visit[ny][nx] = 1;
                if(map[ny][nx] == 0) {
                    q.add(new Node(ny, nx, cnt));
                }else{
                    q.add(new Node(ny, nx, cnt + 1));
                }
            }
        }
    }
}
