package miracom;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Level32_이름정렬하기 {
    static int n;
    static String[] list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        list = new String[n];

        for(int i=0; i<n; i++){
            list[i] = br.readLine();
        }

        String temp;
        for(int i=0; i<n-1; i++){
            for(int j=i+1; j<n; j++){
                if(list[i].length() > list[j].length()){
                    temp = list[i];
                    list[i] = list[j];
                    list[j] = temp;
                }
            }
        }

        for(int i=0; i<n-1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (list[i].length() == list[j].length()) {
                    for (int k = 0; k < list[i].length(); k++) {
                        if (list[i].charAt(k) > list[j].charAt(k)) {
                            temp = list[i];
                            list[i] = list[j];
                            list[j] = temp;
                        }else if(list[i].charAt(k) < list[j].charAt(k)){
                            break;
                        }
                    }
                }
            }
        }

        for(String str : list){
            System.out.println(str);
        }
    }
}
