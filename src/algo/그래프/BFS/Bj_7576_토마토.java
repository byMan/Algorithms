package algo.그래프.BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_7576_토마토 {
    static int M, N;
    static int INF = (int)21E8;
    static int[][] map, visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                visit[i][j] = INF;
            }
        }

        bfs();
    }


    private static void bfs(){
        Queue<Integer[]> q = new LinkedList<>();

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == 1){
                    q.add(new Integer[]{i, j, 0});
                }
            }
        }

        int ans = -1;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while(!q.isEmpty()){
            Integer[] now = q.poll();
            int y = now[0];
            int x = now[1];
            int cnt = now[2];
            ans = Math.max(ans, cnt);
            for(int k=0; k<4; k++){
                int ny = y + dy[k];
                int nx = x + dx[k];
                if(ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if(map[ny][nx] == -1) continue;
                if(visit[ny][nx] > cnt){
                    visit[ny][nx] = cnt;
                    q.add(new Integer[]{ny, nx, cnt + 1});
                }
            }
        }

        out: for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == 0 && visit[i][j] == INF){
                    ans = -1;
                }
            }
        }

        System.out.println(ans);
    }
}
