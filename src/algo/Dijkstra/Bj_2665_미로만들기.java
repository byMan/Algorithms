package algo.Dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Bj_2665_미로만들기 {
    static class Node implements Comparable<Node>{
        int y, x, dist;
        Node(int a, int b, int c){
            y = a; x = b; dist = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    static int N;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map, dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        dist = new int[N][N];

        String str;
        for(int i=0; i<N; i++){
            str = br.readLine();
            for(int j=0; j<N; j++){
                int val = str.charAt(j) - '0';
                map[i][j] = val == 1 ? 0 : 1;
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

//        for(int i=0; i<N; i++){
//            for(int j=0; j<N; j++){
//                dijkstra(i, j);
//            }
//        }

        dijkstra(0,0);

        System.out.println(dist[N-1][N-1]);
    }


    private static void dijkstra(int a, int b){
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(a, b, map[a][b]));

        while(!q.isEmpty()){
            Node now = q.poll();
            int nowX = now.x;
            int nowY = now.y;
            int nowDist = now.dist;
            dist[nowY][nowX] = nowDist;
            for(int k=0; k<4; k++){
                int nx = nowX + dx[k];
                int ny = nowY + dy[k];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                int nextDist = map[nx][ny] + nowDist;
                if(dist[ny][nx] > nextDist){
                    dist[ny][nx] = nextDist;
                    q.add(new Node(ny, nx, nextDist));
                }
            }
        }

//        System.out.println();
//        for(int i=0; i<N; i++){
//            for(int j=0; j<N; j++){
//                System.out.print(dist[i][j] + " ");
//            }
//            System.out.println();
//        }
    }
}
