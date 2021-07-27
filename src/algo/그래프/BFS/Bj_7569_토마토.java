package algo.그래프.BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_7569_토마토 {
    static int INF = (int)21E8;
    static int M, N, H;
    static int[][][] map, visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][N][M];
        visit = new int[H][N][M];

        for(int k=0; k<H; k++){
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++){
                    map[k][i][j] = Integer.parseInt(st.nextToken());
                    visit[k][i][j] = INF;
                }
            }
        }

        bfs();
    }

    private static void bfs(){
        Queue<Integer[]> q = new LinkedList<>();
        for(int k=0; k<H; k++){
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(map[k][i][j] == 1) {
                        q.add(new Integer[]{k, i, j, 0});
                        visit[k][i][j] = 0;
                    }
                }
            }
        }

        int ans = -1;
        int[] dx = {-1, 1, 0, 0, 0, 0};
        int[] dy = {0, 0, -1, 1, 0, 0};
        int[] dk = {0, 0, 0, 0, -1, 1};
        while(!q.isEmpty()){
            Integer[] now = q.poll();
            int k = now[0];
            int y = now[1];
            int x = now[2];
            int cnt = now[3];
            ans = Math.max(ans, cnt);
            for(int i=0; i<6; i++){
                int nk = k + dk[i];
                int ny = y + dy[i];
                int nx = x + dx[i];
                if(nk < 0 || nk >= H || ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if(map[nk][ny][nx] == -1) continue;
                if(visit[nk][ny][nx] > cnt + 1) {
                    visit[nk][ny][nx] = cnt + 1;
                    q.add(new Integer[]{nk, ny, nx, cnt + 1});
                }
            }
        }


//        for(int k=0; k<H; k++){
//            for(int i=0; i<N; i++){
//                for(int j=0; j<M; j++){
//                    System.out.print(visit[k][i][j] + "\t");
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }

        out : for(int k=0; k<H; k++){
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(map[k][i][j] == 0 && visit[k][i][j] == INF) {
                        ans = -1;
                        break out;
                    }
                }
            }
        }

        System.out.println(ans);
    }
}
