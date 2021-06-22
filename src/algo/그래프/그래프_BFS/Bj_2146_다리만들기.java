package algo.그래프.그래프_BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bj_2146_다리만들기 {
    //visit에다가count 숫자를 넣고
    //arr[][]에는 영역을 넓혀나간다

    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};
    static int visit[][];
    static int arr[][];
    static Queue<Integer[]> q;
    static int result;
    static int N;
    static int min;

    static void BFS() {
        while (!q.isEmpty()) {
            Integer[] temp = q.poll();
            int x = temp[0];
            int y = temp[1];
            int count = temp[2];
            for (int i = 0; i < 4; i++) {
                int rx = x + dx[i];
                int ry = y + dy[i];
                if (rx < 0 || ry < 0 || rx >= N || ry >= N) {
                    continue;
                }
                if (arr[rx][ry] == 0) {
                    q.add(new Integer[]{rx, ry, count + 1});
                    arr[rx][ry] = arr[x][y];
                    visit[rx][ry] = count + 1;
                } else if (arr[x][y] != arr[rx][ry]) {
                    result = visit[rx][ry] + visit[x][y];
                    if (result < min) {
                        min = result;
                    }
                }
            }

//          for(int i=0;i<arr.length;i++) {
//              for(int j=0;j<arr.length;j++) {
//                  System.out.print(visit[i][j]);
//              }
//              System.out.println();
//          }
//          System.out.println();

        }
    }

    //섬 나누기
    static void change(int i, int j, int color) {
        arr[i][j] = color;
        for (int x = 0; x < 4; x++) {
            int rx = i + dx[x];
            int ry = j + dy[x];
            if (rx >= 0 && ry >= 0 && rx < N && ry < N) {
                if (arr[rx][ry] == -1) {
                    arr[rx][ry] = color;
                    change(rx, ry, color);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N][N];
        visit = new int[N][N];
        q = new LinkedList<>();
        min = Integer.MAX_VALUE;
        //배열 입력받기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int v = sc.nextInt();
                if (v == 1) {
                    q.add(new Integer[]{i, j, 0});
                }
                arr[i][j] = -v;
            }
        }

        int color = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == -1) {
                    change(i, j, color);
                    color++;
                }
            }
        }

        BFS();
        System.out.println(min);
    }
}

/*

[입력]
10
1 1 1 0 0 0 0 1 1 1
1 1 1 1 0 0 0 0 1 1
1 0 1 1 0 0 0 0 1 1
0 0 1 1 1 0 0 0 0 1
0 0 0 1 0 0 0 0 0 1
0 0 0 0 0 0 0 0 0 1
0 0 0 0 0 0 0 0 0 0
0 0 0 0 1 1 0 0 0 0
0 0 0 0 1 1 1 0 0 0
0 0 0 0 0 0 0 0 0 0

[출력]
3
 */