package algo.KMP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * KMP 알고리즘 관련 영상 자료 참고
 * https://youtu.be/UcjK_k5PLHI
 */
public class Bj_1786_찾기 {
    static int[] p;
    static List<Integer> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String pattern = br.readLine();

        list = new ArrayList<>();

        makeTable(pattern);
        kmp(input, pattern);

        StringBuilder sb = new StringBuilder();
        for(int a : list){
            sb.append(a).append(" ");
        }

        System.out.println(list.size());
        System.out.println(sb.toString());
    }

    /**
     * 패턴 문자열 내에서 문자의 반복여부를 체크하여 배열에 저장한다.
     * 이후 kmp 알고리즘에서 이 패턴을 활용해서 불일치 시 일치한 만큼만 건너뛰어 체크할 수 있도록
     * 유도하기 위해서 구한다.
     * @param pattern
     */
    private static void makeTable(String pattern){
        int j =0;
        int len = pattern.length();
        p = new int[len];

        for(int i=1; i<len; i++){
            while(j>0 && pattern.charAt(j) != pattern.charAt(i)){
                j = p[j-1];
            }
            //같으면...
            if(pattern.charAt(i) == pattern.charAt(j)){
                p[i] = ++j;
            }
        }
    }

    /**
     * 패턴과 일치하지 않을 경우 패턴과 부분 일치 했던 부분을 건너 뛰어 체크한다.
     * @param input
     * @param pattern
     */
    private static void kmp(String input, String pattern){
        int j=0;
        int len = input.length();

        for(int i=0; i<len; i++){
            while(j>0 && pattern.charAt(j) != input.charAt(i)){
                j = p[j-1];
            }
            if(pattern.charAt(j) == input.charAt(i)){
                if(j+1 == pattern.length()){
                    list.add(i-pattern.length() + 2);
                    j = p[j];
                }else{
                    j++;
                }
            }
        }
    }
}