package miracom;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Level31_세로줄변경 {
    static String[] input;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = new String[5];

        for(int i=0; i<5; i++){
            input[i] = (String) br.readLine();
        }

        String temp;
        boolean isYes = false;
        for(int i=0; i<5; i++){
            temp = "" + input[i].charAt(0) + input[i].charAt(3) + input[i].charAt(2) + input[i].charAt(1) + input[i].charAt(4);
            System.out.println(temp);
            if("MAPOM".equalsIgnoreCase(temp)){
                isYes = true;
                break;
            }
        }

        if(isYes) {
            System.out.println("yes");
        }else {
            System.out.println("no");
        }
    }
}
