package 기출;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 삐뽀Q바이러스 {
    static int n, m;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] arr;
    static boolean[][] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 2 && !visit[i][j]) {
                    bfs(i, j);
                    cnt++;
                }
            }
        }

//        for(int i=0; i<n; i++){
//            for(int j=0; j<m; j++){
//                System.out.print(arr[i][j] + "\t");
//            }
//            System.out.println();
//        }

        System.out.println(cnt);
    }

    public static void bfs(int x, int y) {
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{x, y});
        visit[x][y] = true;

        while (!q.isEmpty()) {
            Integer[] aa = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = aa[0] + dx[i];
                int ny = aa[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (!visit[nx][ny] && arr[nx][ny] > 0) {
                    q.add(new Integer[]{nx, ny});
                    visit[nx][ny] = true;
                    arr[nx][ny] = 2;
                }
            }
        }

    }
}