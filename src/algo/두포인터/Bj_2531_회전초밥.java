package algo.두포인터;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_2531_회전초밥 {
    static int N, D, K, C;
    static int[] arr;
    static int[] num;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[N + N];
        num = new int[D + 1];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int idx = 0;
        for (int i = N; i < N + N; i++) {
            arr[i] = arr[idx++];
        }

        int cnt = 0, maxCnt = 0;
        for (int i = 0; i < K; i++) {
            if (num[arr[i]] == 0) {
                cnt++;
            }
            num[arr[i]]++;
        }

        //쿠폰 적립
        if (num[C] == 0) {
            cnt++;
            num[C]++;
        }

//        System.out.println("cnt : " + cnt);
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(num));

        maxCnt = cnt;
        int start = 0, end = start + K - 1;
        while (end < N + K - 1) {
            int leftVal = arr[start++];
            if (leftVal != C) {
                if(num[leftVal] == 1) {
                    cnt--;
                }
                num[leftVal]--;
            }
            int rightVal = arr[++end];
            if (num[rightVal] == 0) {
                cnt++;
            }
            num[rightVal]++;
            maxCnt = Math.max(maxCnt, cnt);

//            System.out.println();
//            System.out.println("cnt : " + cnt + "\tleft : " + leftVal + "\tright : " + rightVal);
//            System.out.println(Arrays.toString(arr));
//            System.out.println(Arrays.toString(num));
        }

        System.out.println(maxCnt);
    }

}
