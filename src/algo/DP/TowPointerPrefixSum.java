package algo.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class TowPointerPrefixSum {
    static int N, M;
    static int[] arr, sum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        sum = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            //미리 누적 값을 구해놓고
            sum[i] = arr[i] + sum[i-1];
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(sum[end] - sum[start-1]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

/*

입력
10 5
1 2 3 4 5 6 7 8 9 10
1 3
2 8
3 9
1 9
1 10

출력
6
35
42
45
55

 */