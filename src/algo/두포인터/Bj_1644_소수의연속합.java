package algo.두포인터;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Bj_1644_소수의연속합 {
    static int N;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        //주어진 수까지의 소수를 구한다.
        makePrimeNumber();

        //연속된 소수의 합이 일치하는 경우 수 찾기
        int ret = twoPointer();

        System.out.println(ret);
    }

    /**     * 소수구하기
     */
    private static void makePrimeNumber() {
        boolean[] prime = new boolean[N + 1];
        prime[0] = prime[1] = true;
        //주어진 수까지 소수만 남긴다.
        for (int i = 2; i * i <= N; i++) {
            if (!prime[i]) {
                //현재 i의 모든 배수를 소수 아님으로 설정한다.
                for (int j = i * i; j <= N; j += i) {
                    prime[j] = true;
                }
            }
        }

        //소수만 list에 담는다.
        for(int i=0; i<=N; i++){
            if(!prime[i])
                list.add(i);
        }
    }

    private static int twoPointer(){
        int start=0, end=0, sum=0, cnt=0;
        while (start < list.size()) {
            while (sum < N && end < list.size()) {
                sum += list.get(end++);
            }
            if(sum == N) cnt++;
            sum -= list.get(start++);
        }
        return cnt;
    }
}
