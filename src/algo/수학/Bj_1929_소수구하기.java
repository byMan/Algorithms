package algo.수학;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_1929_소수구하기 {
    static int s, e;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        getSosu(s, e);
    }

    private static void getSosu(int s, int e){
        boolean[] check = new boolean[e+1];
        check[0] = check[1] = true;

        for(int i=2; i<Math.sqrt(e); i++){
            for(int j=i*i; j<=e; j+=i){
                check[j] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=s; i<=e; i++){
            if(!check[i]){
                sb.append(i).append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}
