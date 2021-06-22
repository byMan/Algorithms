package miracom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Level31_HITSMUSIC {
    static int n, ans;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        List<String> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            list.add(st.nextToken());
        }

        String str = "HITSMUSIC";
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
//                if(i==j) continue;
                sb.delete(0, sb.capacity());
                sb.append(list.get(i)).append(list.get(j));
                if(str.equalsIgnoreCase(sb.toString())){
                    ans++;
                }
            }
        }


        System.out.println(ans);
    }
}
