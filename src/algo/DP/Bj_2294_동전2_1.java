package algo.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_2294_동전2_1 {
    static int n, k;
    static int[] num, d;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        num = new int[n+1];
        d = new int[k+1];

        //값 입력
        for(int i=1; i<=n; i++)
            num[i] = Integer.parseInt(br.readLine());

        Arrays.fill(d, 100001);
        d[0] = 0;

        for(int i=1; i<=n; i++){
            for(int j=num[i]; j<=k; j++){
                d[j] = Math.min(d[j], d[j - num[i]] + 1);
            }
        }

        d[k] = d[k] == 100001 ? -1 : d[k];
        System.out.println(d[k]);
    }
}
