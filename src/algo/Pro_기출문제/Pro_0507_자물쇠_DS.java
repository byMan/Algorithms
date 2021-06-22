package algo.Pro_기출문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pro_0507_자물쇠_DS {
    static int T, N, K;
    static long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        int[][] map = null;
        long[][] dist = null;

        for(int t=1; t<=T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[K][N];
            dist = new long[K][N];
            long min = INF;

            for(int i = 1; i<=K; i++){
                String line = br.readLine();
                for(int j=1; j<=N; j++){
                    map[i-1][j-1] = line.charAt(j-1) -'0';
                    dist[i-1][j-1] = INF;
                }
            }

            for(int n=0; n<N; n++){
                int start = 0;
                for(int k=0; k<K; k++){
                    if(map[k][n] == 1){
                        start = k;
                        break;
                    }
                }

                //아래 방향으로 클릭 수 계산
                int d = 0;
                for(int k=start; k<K; k++){
                    if(map[k][n] == 1){
                        d = 0;
                    }
                    if(dist[k][n] > d){
                        dist[k][n] = d;
                    }
                    d++;
                }

                for(int k=0; k<start; k++){
                    if(dist[k][n] > d){
                        dist[k][n] = d++;
                    }
                }

                start = K-1;
                for(int k=start; k>=0; k--){
                    if(map[k][n] == 1){
                        start = k;
                        break;
                    }
                }

                //위 방향으로 클릭 수 계산
                d = 0;
                for(int k=start; k>=0; k--){
                    if(map[k][n] == 1){
                        d = 0;
                    }
                    if(dist[k][n] > d){
                        dist[k][n] = d;
                    }
                    d++;
                }

                for(int k=K-1; k>start; k--){
                    if(dist[k][n] > d){
                        dist[k][n] = d;
                    }
                    d++;
                }
            }

            for(int k=0; k<K; k++){
                long sum = 0;
                for(int n=0; n<N; n++){
                    sum += dist[k][n];
                    if(sum > min) break;
                }

                if(sum < min) {
                    min = sum;
                }
            }

            System.out.println("#" + t + " " + min);
        }
    }
}
