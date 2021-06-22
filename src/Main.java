import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] isPossible;
    static int[] weight;
    static int n, ans;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        weight = new int[n];
        isPossible = new boolean[n+1][200001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            weight[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0,0);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++){
            int v = Integer.parseInt(st.nextToken());

            if(isPossible[n][v]){
                ans++;
            }
        }

        System.out.println(ans);
    }

    private static void dfs(int cnt, int w){
        if(isPossible[cnt][w]){
            return;
        }

        isPossible[cnt][w] = true;

        if(cnt == n){
            return;
        }

        //한쪽에 추가로 놓은 경우
        dfs(cnt+1, w+weight[cnt]);
        //아무쪽에도 놓지 않은 경우
        dfs(cnt+1, w);
        //반대쪽에 놓은 경우
        dfs(cnt+1, Math.abs(w-weight[cnt]));
    }
}

