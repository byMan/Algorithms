package miracom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Level31_가장작은영역구하기 {
    static int n, ans, tot, left, right;
    static int[] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE;
        map = new int[n];

        left = 0;
        right = 3;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            map[i] = Integer.parseInt(st.nextToken());
        }

        while(true){
            if(right >= n) break;
            sumNum();
        }

        System.out.println(ans);
    }


    private static void sumNum(){
        if(left==0){
            for(int k=left; k<4; k++){
                tot += map[k];
            }
        }else{
            tot = tot - map[left-1];
            tot = tot + map[right];
        }

        ans = ans > tot ? tot : ans;

//        System.out.println("tot = " + tot + "\tmap[left] = " + map[left] + "\tmap[right] = " + map[right] + "\tans = " + ans);

        left++;
        right++;
    }
}
