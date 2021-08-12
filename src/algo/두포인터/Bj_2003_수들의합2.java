package algo.두포인터;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_2003_수들의합2 {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //투 포인터로 누적합이 목표값보다 작으면 right 포인터의 값을 sum에 누적한 후 right를 증가시킨다.
        //누적합이 목표값보다 크거나 같으면 left 포인터의 값을 sum에 누작한 후 left를 증가시킨다.
        //int cnt = towPointer1();
        int cnt = towPointer2();

        System.out.println(cnt);
    }

    private static int towPointer2() {
        int left = 0, right = 0, sum = 0, cnt = 0;
        while (left < N) {
            while (sum < M && right < N) {
                sum += arr[right++];
            }

            if (sum == M) cnt++;

            sum -= arr[left++];
        }
        return cnt;
    }

    private static int towPointer1() {
        int left = 0, right = 0, sum = 0, cnt = 0;
        while (true) {
            if (sum >= M) sum -= arr[left++];
            else if (right == N) break;
            else sum += arr[right++];
            if (sum == M) cnt++;
        }
        return cnt;
    }
}

/*
입력
4 2
1 1 1 1
10 5
1 2 3 4 2 5 3 1 1 2

출력
3
3
 */