package algo.Math.PrimeNumber;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PrimeNumber_1978 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        int max = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(arr[i], max);
        }

        boolean[] check = new boolean[max + 1];

        check[0] = check[1] = true;


        /*
         * 에라토스테네스의 접근
         * 주어진 자연수 N이 소수이기 위한 필요충분 조건은 N이 N의 제곱근보다 크지 않은 어떤 소수로도 나눠지지 않는다.
         * 수가 수를 나누면 몫이 발생하게 되는데 몫과 나누는 수, 둘 중 하나는 반드시 N의 제곱근 이하이기 때문이다.
         */
        for (int i = 2; i * i <= max; i++) {
            /*
             * 에라토스테네스의 체는 매우 간단한 아이디어입니다.
             * 위에서 모든 자연수는 소수들의 곱으로 표현이 된다고 했습니다.
             * 제일 작은 소수 2부터 시작합니다.
             * 2부터 N-1까지의 수 중에서 2의 배수를 모두 체로 거르고 남은 숫자들 중에서 3의 배수로 거르고를 반복해서
             * 제곱근N 까지 나눠서 걸러지지 않고 남은 수들이 모두 소수가 됩니다.
             */
            for (int j = i * i; j <= max; j += i) {
                check[j] = true;
            }
        }


        int cnt = 0;
        for (int a : arr) {
            if (!check[a])
                cnt++;
        }
        System.out.println(cnt);
    }
}
