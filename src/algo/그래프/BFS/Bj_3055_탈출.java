package algo.그래프.BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_3055_탈출 {
    static int R, C, si, sj, ANS;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] arr;
    static int[][] map, visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        map = new int[R][C];

        String str = "";
        for(int i=0; i<R; i++){
            str = br.readLine();
            for(int j=0; j<C; j++){
                char ch = str.charAt(j);
                arr[i][j] = ch;
                if(ch == 'S'){
                    si = i;
                    sj = j;
                }
            }
        }

        ANS = -1;

        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(arr[i][j] == '*'){
                    //먼저 물이 차오르는 곳이 언제 차오르는지 map에 표시한다.
                    bfs(i, j, 0);
                }
            }
        }

//        print(arr);
//        print(visit);
//        print(map);

        //시작 위치 기준으로 비버 굴을 찾아간다.
        bfs(si, sj, 1);
//        print(visit);

        if(ANS == -1){
            System.out.println("KAKTUS");
        }else{
            System.out.println(ANS);
        }

    }

    private static void bfs(int i, int j, int gubun){
        visit = new int[R][C];
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{i, j, 0});
        visit[i][j] = 1;

        while(!q.isEmpty()){
            Integer[] now = q.poll();
            int y = now[0];
            int x = now[1];
            int d = now[2];
            map[y][x] = d;
            if(arr[y][x] == 'D'){
                ANS = d;
                break;
            }
            for(int k=0; k<4; k++){
                int ny = y + dy[k];
                int nx = x + dx[k];
                if(nx < 0 || ny < 0 || nx >= C || ny >= R) continue;
                if(visit[ny][nx] == 1) continue;
                //물이 차오를 경우에 대한 BFS라면
                if(gubun == 0) {
                    //비버의 소굴이거나 바위인 경우라면
                    if(arr[ny][nx] == 'D' || arr[ny][nx] == 'X') continue;
                }else{
                    if (arr[ny][nx] == 'X') continue;
                }
                //아직 물이 차오르지 않은 곳이라면, 또는 물이 차기 전에 지나갈 수 있는 곳이라면
                //map[ny][nx] == 0 인 곳은 비버의 동굴이거나 아니면 물의 시작 위치인데, 물의 시작 위치로는 절대 방문할 일이 없으므로 오직 비버의 동굴인 경우만 방문한다고 보면 됨.
                if(map[ny][nx] == 0 || map[ny][nx] > d + 1) {
                    visit[ny][nx] = 1;
                    q.add(new Integer[]{ny, nx, d+1});
                }

            }
        }
    }


    private static void print(char[][] pArr){
        System.out.println();
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                System.out.print(pArr[i][j] + "\t");
            }
            System.out.println();
        }
    }


    private static void print(int[][] pArr){
        System.out.println();
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                System.out.print(pArr[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
