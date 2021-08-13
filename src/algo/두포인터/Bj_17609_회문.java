package algo.두포인터;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bj_17609_회문 {
    static int N;
    static char[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String str = null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            str = br.readLine();
            int cnt = str.length();
            arr = new char[cnt];
            for (int j = 0; j < cnt; j++) {
                arr[j] = str.charAt(j);
            }
            int ret = palindrome(0, cnt-1);
            sb.append(ret).append("\n");
        }
        System.out.println(sb);
    }

    private static int palindrome(int start, int end){
        while (start < end) {
            if (arr[start] == arr[end]) {
                start++;
                end--;
            }else{
                boolean leftCheck = check(start + 1, end);
                boolean rightCheck = check(start, end - 1);
                if (leftCheck || rightCheck) {
                    return 1;
                }else{
                    return 2;
                }
            }
        }
        return 0;
    }


    private static boolean check(int start, int end) {
        while (start < end) {
            if (arr[start] == arr[end]) {
                start++;
                end--;
            }else{
                return false;
            }
        }
        return true;
    }
}
