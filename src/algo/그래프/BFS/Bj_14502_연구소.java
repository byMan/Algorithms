package algo.그래프.BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_14502_연구소 {
    static class virus{
        int x, y;
        virus(int y, int x){
            this.y = y; this.x = x;
        }
    }

    static int N, M;
    static int[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //벽세우면서 바이러스 뿌리기 시작
        dfs(0);

        System.out.println(result);
    }


    private static void dfs(int depth){
        //벽 3개를 다 세웠으면 바이러스 뿌리기
        if(depth == 3){
            //임의의 벽을 세고 바이러스를 퍼트린 후에 안전 영역까지 세어본다. 그중에 가장 큰 값만 출력한다.
            bfs();
            return;
        }

        //벽 3개 못 세웠으면 다시 세운다.
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                //빈칸이라면
                if(map[i][j] == 0){
                    //벽 세우고
                    map[i][j] = 1;
                    dfs(depth + 1);
                    //다시 돌려놓고
                    map[i][j] = 0;
                }
            }
        }
    }

    private static void bfs(){
        int[][] virusMap = new int[N][M];
        Queue<virus> que = new LinkedList<>();

        //virus map 복제
        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                virusMap[i][j] = map[i][j];

        //바이러스를 찾아서 큐에 넣어준다.
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                //바이러스라면 큐에 넣어
                if(virusMap[i][j] == 2){
                    que.add(new virus(i, j));
                }
            }
        }

        //바이러스를 모두 퍼트린다.
        while(!que.isEmpty()){
            virus v = que.poll();
            for(int i=0; i<4; i++){
                int nx = v.x + dx[i];
                int ny = v.y + dy[i];
                if(nx < 0 || ny < 0 || ny >= N || nx >= M) continue;
                //빈칸이라면 바이러스 퍼뜨리고 다시 큐에 넣는다
                if(virusMap[ny][nx] == 0){
                    virusMap[ny][nx] = 2;
                    que.add(new virus(ny, nx));
                }
            }
        }

        //bfs가 불려질때마다 바이러스가 퍼지지 않은 영역을 센다.
        countSafeArea(virusMap);
    }


    private static void countSafeArea(int[][] virusMap){
        int count = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(virusMap[i][j] == 0)
                    count++;
            }
        }

        result = Math.max(result, count);
    }
}
