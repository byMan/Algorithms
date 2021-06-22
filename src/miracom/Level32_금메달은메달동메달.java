package miracom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Level32_금메달은메달동메달 {
    static int n;
    static int[] num;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        num = new int[n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            num[i] = Integer.parseInt(st.nextToken());
        }

        int temp;
        for(int i=0; i<n-1; i++){
            for(int j=i+1; j<n; j++){
                if(num[i] < num[j]){
                    temp = num[i];
                    num[i] = num[j];
                    num[j] = temp;
                }
            }
        }

        System.out.println("금" + num[0]);
        System.out.println("은" + num[1]);
        System.out.println("동" + num[2]);
    }
}
