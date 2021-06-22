package miracom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Level32_음료수창고정리 {
    static String in;
    static int[][] num;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        List<String> inList = new ArrayList<>();
        for(int i=0; i<5; i++) {
            st = new StringTokenizer(br.readLine());
            inList.add(st.nextToken());
        }

        int n1, n2;
        st = new StringTokenizer(br.readLine());
        n1 = Integer.parseInt(st.nextToken());
        n2 = Integer.parseInt(st.nextToken());

        num = new int[5][];

        String str;
        for(int i=0; i<5; i++) {
            str = inList.get(i);
            char[] ch = str.toCharArray();
            num[i] = new int[ch.length];
            for(int j=0; j<ch.length; j++){
                num[i][j] = ch[j] - '0';
            }
        }

        for(int i=0; i<5; i++){
            if(i == n1 || i == n2) {
                arr = num[i];
                Arrays.sort(arr);
                num[i] = arr;
            }
        }

        for(int i=0; i<5; i++) {
            System.out.print(num[i][0] + " ");
        }
    }
}
