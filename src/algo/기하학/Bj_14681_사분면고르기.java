package algo.기하학;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bj_14681_사분면고르기 {
    static int x, y, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        x = Integer.parseInt(br.readLine());
        y = Integer.parseInt(br.readLine());

        if(x > 0 && y > 0){
            System.out.println(1);
        }else if(x > 0 && y < 0){
            System.out.println(4);
        }else if(x < 0 && y < 0){
            System.out.println(3);
        }else{
            System.out.println(2);
        }
    }
}
