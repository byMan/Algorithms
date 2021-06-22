package miracom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Level31_버스주차요금저렴한곳찾기 {
    static int[] doro = {1,2,3,3,5,1,0,1,3};
    static int n, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ans = Integer.MAX_VALUE;
        n = Integer.parseInt(st.nextToken());

        int left = 0, right = n-1, tot = 0;

        for(int i=0; i<n; i++){
            tot += doro[i];
        }

        while(true){
            left++;
            right++;
            if(right >= doro.length) break;
            tot -= doro[left-1];
            tot += doro[right];
            ans = ans > tot ? tot : ans;
        }

        System.out.println(ans);
    }
}
