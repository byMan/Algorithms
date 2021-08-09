package algo.투포인터;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_11728_배열합치기 {
    static int n1, n2;
    static int[] n1Arr, n2Arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n1 = Integer.parseInt(st.nextToken());
        n2 = Integer.parseInt(st.nextToken());

        n1Arr = new int[n1];
        n2Arr = new int[n2];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n1; i++){
            n1Arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n2; i++){
            n2Arr[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        int left = 0, right = 0, index = 0, leftVal = 0, rightVal = 0;
        while (true) {
            if(left >= n1 && right >= n2) break;
            leftVal = left >= n1 ? Integer.MAX_VALUE : n1Arr[left];
            rightVal = right >= n2 ? Integer.MAX_VALUE : n2Arr[right];
            if(leftVal < rightVal){
                sb.append(n1Arr[left++]);
            }else{
                sb.append(n2Arr[right++]);
            }
            sb.append(" ");
        }

        System.out.println(sb);
    }
}
