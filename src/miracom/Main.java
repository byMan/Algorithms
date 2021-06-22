package miracom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int input = Integer.parseInt(st.nextToken());

        if(input == 1)
            System.out.println(40);
        if(input == 2)
            System.out.println(3*8+1*10+4*10);
        if(input == 3)
            System.out.println(100*(4+1));
        if(input == 4)
            System.out.println(12);
    }
}
