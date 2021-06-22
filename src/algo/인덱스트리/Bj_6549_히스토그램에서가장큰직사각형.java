package algo.인덱스트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_6549_히스토그램에서가장큰직사각형 {

    static int n;
    static int[] arr, tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        while (true) {
            n = Integer.parseInt(st.nextToken());

            if (n == 0) break;

            arr = new int[n];
            tree = new int[n * 4];

            for (int i = 0; i < n; i++) {
                int val = Integer.parseInt(st.nextToken());
                arr[i] = val;
                update(i, val);
            }

            System.out.println(Arrays.toString(tree));
        }
    }


    private static void update(int i, int dif) {
        while (i <= n) {
            tree[i] = Math.min(tree[i], dif);
            i += (i & -i);
        }
    }
}
