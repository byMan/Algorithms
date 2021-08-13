package algo.두포인터;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_1940_주몽 {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int start=0, end=N-1, cnt=0;
        while (start < end) {
            int tot = arr[start] + arr[end];
            if(tot == M){
                cnt++;
            }
            if(tot > M){
                end--;
            }else{
                start++;
            }
        }

        System.out.println(cnt);
    }
}
