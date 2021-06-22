package algo.기하학;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_4153_직각삼각형 {
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        arr = new int[3];
        while(true) {
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 3; i++) {
                arr[i] = (int) Math.pow(Integer.parseInt(st.nextToken()), 2);
            }

            Arrays.sort(arr);

            if (arr[0] == 0 && arr[1] == 0 && arr[2] == 0) {
                break;
            } else {
                if (arr[0] + arr[1] == arr[2]) {
                    System.out.println("right");
                } else {
                    System.out.println("wrong");
                }
            }
        }
    }
}
