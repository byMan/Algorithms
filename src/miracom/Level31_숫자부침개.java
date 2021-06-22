package miracom;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Level31_숫자부침개 {
    static int p, n, ans;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        p = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());

        ans = p;
        for(int i=0; i<n; i++){
            ans *= 2;
            ans = changeNum(ans + "");
        }

        System.out.println(ans);
    }

    private static int changeNum(String numStr){
        int cnt = numStr.length();
        StringBuilder sb = new StringBuilder();
        for(int i=cnt-1; i>=0; i--){
            sb.append(numStr.charAt(i));
        }
        return Integer.parseInt(sb.toString());
    }
}
