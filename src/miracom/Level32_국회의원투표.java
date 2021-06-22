package miracom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Level32_국회의원투표 {
    static int n, m, maxIdx, maxVal;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        maxVal = Integer.MIN_VALUE;

        ArrayList<ArrayList<String>> list = new ArrayList<>();
        for(int i=0; i<n; i++){
            list.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            list.get(a).add(st.nextToken());
        }

        //1등 구하기
        for(int i=0; i<n; i++){
            if(maxVal < list.get(i).size()){
                maxVal = list.get(i).size();
                maxIdx = i;
            }
        }

        for(String str : list.get(maxIdx)){
            System.out.print(str + " ");
        }
    }
}
