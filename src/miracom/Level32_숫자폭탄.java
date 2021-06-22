package miracom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Level32_숫자폭탄 {
    static int n;
    static String numStr;
    static int[] num;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        numStr = br.readLine().replaceAll(" ", "");
        num = new int[numStr.length()];

        for(int i=0; i<num.length; i++){
            num[i] = numStr.charAt(i) - '0';
        }

        int l=0, r=2;
        StringBuilder sb = new StringBuilder();
        while(true){
            if(num[l] == num[l+1] && num[l] == num[r]){
                num[l] = Integer.MAX_VALUE;
                num[l+1] = Integer.MAX_VALUE;
                num[r] = Integer.MAX_VALUE;
                l = r+1;
                r = l+2;
                continue;
            }else{
                if(r == n-1){
                    sb.append(num[l]).append(num[l+1]).append(num[r]);
                }else{
                    sb.append(num[l]);
                }
            }
            if(r == n-1) break;
            l++;
            r++;
        }

        char[] res = sb.toString().toCharArray();
        Arrays.sort(res);

        for(int i=0; i<res.length; i++){
            System.out.print(res[i] + " ");
        }
    }

}
