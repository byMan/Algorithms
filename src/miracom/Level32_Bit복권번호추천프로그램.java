package miracom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    int num;
    int cnt;
    @Override
    public int compareTo(Node o) {
        return o.cnt - this.cnt;
    }
}

public class Level32_Bit복권번호추천프로그램 {
    static int n;
    static int[] m = new int[10];
    static int[] num = new int[10];
    static int[][] map, bit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        ArrayList<Node> list = new ArrayList<>();
        for(int i=0; i<10; i++){
            Node node = new Node();
            node.num = i;
            list.add(node);
        }


    }




    private static void method1(BufferedReader br) throws IOException {
        StringTokenizer st;
        map = new int[n][n];
        bit = new int[n][n];

        for(int i=0; i<10; i++){
            num[i] = i;
        }

        for(int i=0; i<n*2; i++){
            if(i < n) {
                st = new StringTokenizer(br.readLine());
                int cnt = st.countTokens();
                for (int j = 0; j < cnt; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }else{
                st = new StringTokenizer(br.readLine());
                int cnt = st.countTokens();
                for (int j=0; j<cnt; j++) {
                    int val = Integer.parseInt(st.nextToken());
                    if(val > 0){
                        m[map[i-4][j]]++;
                    }
                    bit[i-4][j] = val;
                }
            }
        }

        int maxVal = -1, tmp;
        for(int i=0; i<9; i++){
            for(int j=i+1; j<10; j++){
                if(m[i] < m[j]){
                    changeValue(i, j);
                }else if(m[i] == m[j] && num[i] > num[j]){
                    changeValue(i, j);
                }
            }
        }

        System.out.println(Arrays.toString(m));
        System.out.println(Arrays.toString(num));

        for(int i=0; i<10; i++){
            if(m[i] > 0){
                for(int j=0; j<m[i]; j++){
                    System.out.print(num[i] + " ");
                }
            }else{
                break;
            }
        }
    }

    private static void changeValue(int i, int j) {
        int tmp;
        tmp = m[i];
        m[i] = m[j];
        m[j] = tmp;

        tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }
}

