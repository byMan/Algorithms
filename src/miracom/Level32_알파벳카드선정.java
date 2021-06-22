package miracom;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Level32_알파벳카드선정 {
    static int n;
    static String str;
    static int[] arr = new int[26];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        n = Integer.parseInt(br.readLine());

        char[] ch = str.toCharArray();
        for(int i=ch.length-1; i>=ch.length-n; i--){
            arr[ch[i]-'A']++;
        }

        int maxId = -1, maxVal = 0;
        for(int i=0; i<26; i++){
            if(maxVal < arr[i]){
                maxVal = arr[i];
                maxId=i;
            }
        }

        System.out.println((char)(maxId + 'A'));
    }
}
