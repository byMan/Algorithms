package algo.투포인터;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_1806_부분합 {
    static int N, S;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ret = twoPointer();

        System.out.println(ret);
    }

    private static int twoPointer() {
        int start=0, end=0, sum=0, cnt=0, len=Integer.MAX_VALUE;
        while (true) {
            if(sum >= S){
                len = Math.min(len, end - start);
                sum -= arr[start++];
            } else if (end == N) {
                break;
            }else{
                sum += arr[end++];
            }
        }

        return len == Integer.MAX_VALUE ? 0 : len;
    }
}
