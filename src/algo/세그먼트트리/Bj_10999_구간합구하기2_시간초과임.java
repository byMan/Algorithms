package algo.세그먼트트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Bj_10999_구간합구하기2_시간초과임 {
    static int N, M, K;
    static long[] arr, tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N+1];
        tree = new long[N+1];

        for(int i=1; i<=N; i++) {
            long val = Long.parseLong(br.readLine());
            arr[i] = val;
            update(i, val);
        }

        for(int i=1; i<=(M+K); i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if(a==1) {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                long d = Integer.parseInt(st.nextToken());
                update_range(b, c, d);
            } else {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                bw.write(sum_range(b, c) + "\n");
            }
        }

        bw.flush();
    }

    static long sum_range(int start, int end) {
        return sum(end) - sum(start-1);
    }

    static long sum(int idx) {
        long ans=0;

        while(idx>0) {
            ans += tree[idx];
            idx -= (idx & -idx);
        }

        return ans;
    }

    static void update_range(int start, int end, long val) {
        for(int i=start; i<=end; i++){
            update(i, val);
        }
    }

    static void update(int idx, long val) {
        while(idx<=N) {
            tree[idx] += val;
            idx += (idx & -idx);
        }
    }
}