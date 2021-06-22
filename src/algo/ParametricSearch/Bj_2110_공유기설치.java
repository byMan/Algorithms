package algo.ParametricSearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_2110_공유기설치 {
    static int n, k, ans;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int start = 1;
        int end = arr[n-1] - arr[0];    //가능한 최대 거리
        while (start <= end) {
            int cnt = 1;
            int mid = (start + end) / 2;
            int last = arr[0] + mid;      //첫번째값 + 거리
            for(int i=0; i<n; i++){
                if(arr[i] >= last){
                    cnt++;
                    last = arr[i] + mid;
                }
            }

            if(cnt >= k){
                ans = Math.max(ans, mid);
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }

        System.out.println(ans);
    }
}
