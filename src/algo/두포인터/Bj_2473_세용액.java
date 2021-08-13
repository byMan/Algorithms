package algo.두포인터;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_2473_세용액 {
    static int N;
    static long[] arr;
    static long[] val = new long[3];
    static long min = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        for(int i=0; i<N-2; i++){
            solve(i);
        }

        Arrays.sort(val);

        System.out.println(val[0] + " " + val[1] + " " + val[2]);
    }

    private static void solve(int idx) {
        int start = idx + 1;
        int end = N-1;
        while(start < end){
            long sum = arr[idx] + arr[start] + arr[end];
            long absSum = Math.abs(sum);
            if(min > absSum){
                min = absSum;
                val[0] = arr[idx];
                val[1] = arr[start];
                val[2] = arr[end];
            }
            if (sum > 0) {
                end--;
            }else{
                start++;
            }
        }
    }
}
