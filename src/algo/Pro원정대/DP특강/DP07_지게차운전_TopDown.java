package algo.Pro원정대.DP특강;

import java.util.Scanner;

public class DP07_지게차운전_TopDown {
    static int[][] map;
    static int[][] red;
    static int h, w;

    enum Bang {
        RIGHT,
        DOWN,
    }

    static Bang[][] bang;
    static int[][] memo;

    static int getMinMamo(int dy, int dx) {
        if (dy == w - 1 && dx == h - 1) return 0;
        if (dx >= w) return (int) 21e8;
        if (dy >= h) return (int) 21e8;
        if (memo[dy][dx] != 0) return memo[dy][dx];

        int a = getMinMamo(dy + 1, dx);
        int b = getMinMamo(dy, dx + 1);

        int minab = a;
        bang[dy][dx] = Bang.DOWN;
        if (minab > b) {
            minab = b;
            bang[dy][dx] = Bang.RIGHT;
        }

        memo[dy][dx] = minab + map[dy][dx];
        return memo[dy][dx];
    }

    public static void main(String[] args) {
        //입력처리
        Scanner sc = new Scanner(System.in);
        h = sc.nextInt();
        w = sc.nextInt();

        map = new int[h + 1][w + 1];
        red = new int[h + 1][w + 1];
        bang = new Bang[h + 1][w + 1];
        memo = new int[h + 1][w + 1];

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                map[y][x] = sc.nextInt();
            }
        }

        int ret = getMinMamo(0, 0);
        System.out.println(ret);

        int y = 0;
        int x = 0;
        while (bang[y][x] != null) {
            System.out.println(y + "," + x);
            if (bang[y][x] == Bang.RIGHT) x++;
            else if (bang[y][x] == Bang.DOWN) y++;
        }
        System.out.println(y + "," + x);
    }
}
