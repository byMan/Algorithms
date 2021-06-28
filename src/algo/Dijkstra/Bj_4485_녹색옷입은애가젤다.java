package algo.Dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_4485_녹색옷입은애가젤다 {

    static int N;
    static int[][] map, dist;
    static boolean[][] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int num = 1;
        while(true){
            N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            map = new int[N][N];
            dist = new int[N][N];
            visit = new boolean[N][N];

            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    int val = Integer.parseInt(st.nextToken());
                    map[i][j] = val;
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            dijkstra(0, 0, map[0][0]);

            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    System.out.print(dist[i][j] + "\t");
                }
                System.out.println();
            }

            System.out.println("Problem " + num++ + ": " + dist[N-1][N-1]);
        }
    }

    private static void dijkstra(int i, int j, int val){
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{i, j, val});
        dist[i][j] = val;

        while(!q.isEmpty()){
            Integer[] arr = q.poll();
            int y = arr[0];
            int x = arr[1];
            int v = arr[2];
            int[] dx = {-1,1,0,0};
            int[] dy = {0,0,-1,1};
            for(int k=0; k<4; k++){
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(v + map[ny][nx] < dist[ny][nx]){
                    dist[ny][nx] = v + map[ny][nx];
                    q.add(new Integer[]{ny, nx, v + map[ny][nx]});
                }
            }
        }
    }
}
