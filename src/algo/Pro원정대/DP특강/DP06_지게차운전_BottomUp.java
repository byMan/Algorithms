package algo.Pro원정대.DP특강;

import java.util.Scanner;

public class DP06_지게차운전_BottomUp {
    static int[][] map;
    static int[][] red;
    static int h, w;

    enum Bang {
        RIGHT,
        DOWN,
    }

    static Bang[][] bang;

    public static void main(String[] args) {
        //입력처리
        Scanner sc = new Scanner(System.in);
        h = sc.nextInt();
        w = sc.nextInt();

        map = new int[h + 1][w + 1];
        red = new int[h + 1][w + 1];
        bang = new Bang[h + 1][w + 1];

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                map[y][x] = sc.nextInt();
            }
        }

        //오른쪽 맨 끝, 아래 모두 21e8로 처리
        for (int i = 0; i < h; i++) red[i][w] = (int) 21e8;
        for (int i = 0; i < w; i++) red[h][i] = (int) 21e8;

        //2중 for돌면서 오른쪽 vs 아래
        for (int y = h - 1; y >= 0; y--) {
            for (int x = w - 1; x >= 0; x--) {
                if (y == h - 1 && x == w - 1) continue;

                int right = red[y][x + 1];
                int down = red[y + 1][x];

                //right vs left 비교해서 작은 값 선택
                int mini = down;
                bang[y][x] = Bang.DOWN;
                if (mini > right) {
                    mini = right;
                    bang[y][x] = Bang.RIGHT;
                }

                //작은 값으로 넣기
                red[y][x] = map[y][x] + mini;
            }
        }

        System.out.println(red[0][0]);

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

/*
[입력]
4 4
0 3 5 1
1 1 1 5
1 50 20 10
1 50 5 0

[출력]
18
0,0
1,0
1,1
1,2
1,3
2,3
3,3


 */