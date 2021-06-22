package algo.ParametricSearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_1654_랜선자르기 {
    static int n, k;
    static long max;
    static long[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new long[n];

        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }

        long left=1, right=max;
        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0;
            for(int i=0; i<n; i++){
                sum += (arr[i] / mid);
            }

            //더 많은 랜선이 나온 경우 더 크게 잘라줘야 함
            if(sum >= k){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        System.out.println(right);
    }
}
