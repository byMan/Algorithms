package algo.그래프.DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_1987_알파벳 {

    static int ga, se, ans;
    static int[][] map;
    static boolean[] visited = new boolean[26];
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        se = Integer.parseInt(st.nextToken());
        ga = Integer.parseInt(st.nextToken());

        map = new int[se][ga];

        String str;
        for(int i=0; i<se; i++){
            str = br.readLine();
            for(int j=0; j<ga; j++){
                map[i][j] = str.charAt(j) - 'A';
            }
        }

        visited[map[0][0]] = true;

        dfs(0,0, 1);

        System.out.println(ans);
    }


    private static void dfs(int x, int y, int count){
        if(ans < count){
            ans = count;
        }

        int idx, nx, ny;
        for(int i=0; i<4; i++){
            nx = x + dx[i];
            ny = y + dy[i];

            if(nx >= 0 && ny >= 0 && nx < se && ny < ga){
                idx = map[nx][ny];
                if(!visited[idx]) {
                    visited[idx] = true;
                    dfs(nx, ny, count + 1);
                    visited[idx] = false;
                }
            }
        }
    }
}
