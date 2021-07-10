package algo.Pro원정대.DP특강;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP08_개구리점프_BottomUp {
    static int h, w, ANS;
    static int[][] map, arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        map = new int[h][w];
        arr = new int[h][w];
        ANS = Integer.MIN_VALUE;

        for(int i=0; i<h; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<w; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                arr[i][j] = Integer.MIN_VALUE;
            }
        }

        getSumMax(0, 0, map[0][0]);

        System.out.println(ANS);
    }


    private static int getSumMax(int y, int x, int val){
        if(y >= h) return Integer.MIN_VALUE;
        if(x >= w) return Integer.MIN_VALUE;
        if(map[y][x] == 0) return Integer.MIN_VALUE;
        if(x < 0 || y < 0) return Integer.MIN_VALUE;

        if(arr[y][x] != Integer.MIN_VALUE) return arr[y][x];

        arr[y][x] = val;

        //아래왼쪽
        int a=0, b=0, c=0;
        if(y+1 <= h-1 && x-1 >= 0) {
            a = getSumMax(y + 1, x - 1, val + map[y + 1][x - 1]);
        }

        //가운데
        if(y+1 <= h-1) {
            b = getSumMax(y + 1, x, val + map[y + 1][x]);
        }

        //오른쪽
        if(y+1 <= h-1 && x+1 <= w-1) {
            c = getSumMax(y + 1, x + 1, val + map[y + 1][x + 1]);
        }

        ANS = Math.max(ANS, a);
        ANS = Math.max(ANS, b);
        ANS = Math.max(ANS, c);

        return arr[y][x];
    }
}
