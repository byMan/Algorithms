package algo.두포인터;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_2003_수들의합2 {
    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int s=0, e=0, tot = 0, result=0;
        while (true) {
            if(tot >= m){
                tot -= arr[s++];
            }else if(e == n){
                break;
            }else{
                tot += arr[e++];
            }
            if(tot == m){
                result++;
            }
        }
        System.out.println(result);
    }

}
