package miracom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Level31_복합사전정렬 {
     static int n;
     static String[] strArr;
     public static void main(String[] args) throws Exception {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

         n = Integer.parseInt(br.readLine());
         strArr = new String[n];

         String str;
         for(int i=0; i<n; i++){
             str = br.readLine();
             strArr[i] = str;
         }

         Arrays.sort(strArr);

         for(int i=0; i<n; i++){
             for(int j=i+1; j<n; j++){
                 if(strArr[i].length() > strArr[j].length()){
                     str = strArr[i];
                     strArr[i] = strArr[j];
                     strArr[j] = str;
                 }
             }
         }

         for(String tmp : strArr){
             System.out.println(tmp);
         }

         for(int i=0; i<n; i++){
             for(int j=i+1; j<n; j++){
                 if(strArr[i].length() == strArr[j].length()){
                     if(changeYn(strArr[i], strArr[j], strArr[i].length())) {
                         str = strArr[i];
                         strArr[i] = strArr[j];
                         strArr[j] = str;
                     }
                 }
             }
         }

         for(String tmp : strArr){
             System.out.println(tmp);
         }
     }

     private static boolean changeYn(String str1, String str2, int len){
         for(int i=0; i<len; i++){
             if(str1.charAt(i) < str2.charAt(i)){
                 return false;
             }else if(str1.charAt(i) > str2.charAt(i)){
                 return true;
             }
         }
         return false;
     }
}
