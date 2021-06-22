package miracom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Level31_빌딩은나의것 {
    static int n, start, end, tot, ans;
    static int[] num;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        num = new int[n];
        ans = Integer.MIN_VALUE;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0, slideCnt = 1;

        while(slideCnt < n){

            if(right >= n){
                slideCnt++;
                left = 0;
                tot = 0;
                right = slideCnt - 1;
                continue;
            }

            if(left == 0){
                for(int i=0; i<slideCnt; i++){
                    tot += num[i];
                }
                left++;
                right++;
//                System.out.println("tot = " + tot);
                continue;
            }

            tot -= num[left-1];
            tot += num[right];

//            System.out.println("ans="+ans+"\ttot="+tot+"\tleft="+left+"\tright="+right+"\tslideCnt="+slideCnt);

            if(tot > ans) {
                start = left;
                end = right;
                ans = tot;
            }

            left++;
            right++;
        }

        System.out.println(start + " " + end);
    }
}
