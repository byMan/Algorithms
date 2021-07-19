package algo.그래프.BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_2206_벽부수고이동하기 {
    static int N, M;
    static int[][] arr, visit;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class Node {
        int y, x, dis, fense;
        Node(int a, int b, int c, int d){
            y = a; x = b; dis = c; fense = d;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visit = new int[N][M];

        String[] str = null;
        for(int i=0; i<N; i++){
            str = br.readLine().split("");
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(str[j]);
                visit[i][j] = Integer.MAX_VALUE;
            }
        }

        int ans = bfs();

        if(ans == 0){
            System.out.println(-1);
        }else{
            System.out.println(ans);
        }
    }


    public static int bfs(){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0,0, 1, 0));
        visit[0][0] = 0;

        int res=0;
        while(!q.isEmpty()){
            Node now = q.poll();
            int x = now.x;
            int y = now.y;
            int dis = now.dis;
            int fense = now.fense;
            if(y == N-1 && x == M-1){
                res = dis;
                break;
            }
            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || ny >= N || nx >= M) continue;
                if(visit[ny][nx] <= fense) continue;
                if(arr[ny][nx] == 0){
                    visit[ny][nx] = fense;
                    q.add(new Node(ny, nx, dis + 1, fense));
                }else{
                    if(fense == 0) {
                        visit[ny][nx] = fense + 1;
                        q.add(new Node(ny, nx, dis + 1, fense + 1));
                    }
                }
            }
        }
        return res;
    }
}
