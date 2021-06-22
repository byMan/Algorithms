package miracom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Level32_새로운회원관리시스템 {
    static int n;
    static String[] list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new String[n];

        String str;
        for(int i=0; i<n; i++){
            str = br.readLine();
            char l = str.charAt(0);
            //첫글자 대문자 체크
            if(l < 97){
                //첫글자 빼고 모두 소문자이면
                if(allLower(str)){
                    list[i] = str;
                }else{
                    list[i] = str.toUpperCase();
                }
            }else if(l >= 97){
                if(allLower(str)){
                    l = (char)(l-32);
                    list[i] = l + str.substring(1);
                }else{
                    list[i] = str.toUpperCase();
                }
            }
        }

        Arrays.sort(list);

        for(String tmp : list){
            System.out.println(tmp);
        }
    }

    private static boolean allLower(String str){
        for(int i=1; i<str.length(); i++){
            if(str.charAt(i) < 97)
                return false;
        }
        return true;
    }
}
