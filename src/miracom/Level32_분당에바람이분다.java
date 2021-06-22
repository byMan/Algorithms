package miracom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Level32_분당에바람이분다 {
    static int n, w;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        String str;
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i=0; i<n; i++){
            list.add(new ArrayList<>());
        }

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            st.nextToken(); st.nextToken();
            str = st.nextToken();
            for(int j=0; j<str.length(); j++){
                list.get(i).add(str.charAt(j) - '0');
            }
        }

        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            int segi = Integer.parseInt(st.nextToken());
            for(int i=0; i<n; i++){
                int cnt = list.get(i).size();
                if(cnt == 0) continue;
                int val = list.get(i).get(cnt-1);
                if(segi >= val){
                    list.get(i).remove(cnt-1);
                }else if(segi < val){
                    list.get(i).remove(cnt - 1);
                    list.get(i).add(val - segi);
                }
            }
        }


        int tot = 0;
        for(int i=0; i<list.size(); i++){
            tot += list.get(i).size();
        }

        System.out.println(tot);
    }
}
