package algo.그래프.BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bj_16236_아기상어 {
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int N;
    private static int[][] map;
    private static int[][] dist;
    private static int sharkSize = 2;
    private static int eatingCount = 0;
    private static int count = 0;
    private static int sharkX = -1;
    private static int sharkY = -1;
    private static int minX;
    private static int minY;
    private static int minDist;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = scanner.nextInt();

                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            dist = new int[N + 1][N + 1];
            minX = Integer.MAX_VALUE;
            minY = Integer.MAX_VALUE;
            minDist = Integer.MAX_VALUE;

            //아기 상어 위치에서 bfs를 수행하고 나면 잡아 먹을 수 있는 물고기가 있는 곳으로 이동한다.
            bfs(sharkX, sharkY);

            //잡아 먹을 수 있는 물고기가 있다면
            if (minX != Integer.MAX_VALUE && minY != Integer.MAX_VALUE) {
                //잡아먹은 물고기 수를 증가시킨다.
                eatingCount++;
                //물고기를 잡아 먹었으므로 물고기가 더 이상 존재하지 않음으로 표시한다.
                map[minX][minY] = 0;
                //물고기를 잡아먹은 위치에서 다시 잡아먹을 수 있는 물고기가 있는 위치를 탐색할 수 있도록 초기화 해준다.
                sharkX = minX;
                sharkY = minY;
                //dist배열에 이동한 거리 값이 있으므로 이를 누적한다.
                count += dist[minX][minY];

                //아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다.
                //예를 들어, 크기가 2인 아기 상어는 물고기를 2마리 먹으면 크기가 3이 된다.
                if (eatingCount == sharkSize) {
                    sharkSize++;
                    eatingCount = 0;
                }
            } else {
                // 더 이상 잡아먹을 물고기가 없다.
                break;
            }

//            print();
        }

        System.out.println(count);
    }

    private static void bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newX = p.x + dx[i];
                int newY = p.y + dy[i];

                //새로운 좌표가 배열 범위 내에 있고, 이동 가능한 곳이며 dist 값이 0인 경우는 아직 방문하지 않은 경우이다.
                if (isInArea(newX, newY) && isAbleToMove(newX, newY) && dist[newX][newY] == 0) {
                    //이동 거리는 이전 거리에 1을 더한 거리
                    dist[newX][newY] = dist[p.x][p.y] + 1;

                    //먹을 수 있는 물고기라면
                    if (isEatable(newX, newY)) {
                        //먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
                        if (minDist > dist[newX][newY]) {
                            minDist = dist[newX][newY];
                            minX = newX;
                            minY = newY;
                        } else if (minDist == dist[newX][newY]) {
                            //거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
                            if (minX == newX) {
                                //가장 위에 있는 물고기
                                if (minY > newY) {
                                    minX = newX;
                                    minY = newY;
                                }
                            } else if (minX > newX) {   //가장 왼쪽에 있는 물고기
                                minX = newX;
                                minY = newY;
                            }
                        }
                    }

                    queue.add(new Point(newX, newY));
                }
                print();
            }
        }
    }

    private static boolean isAbleToMove(int x, int y) {
        //아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 나머지 칸은 모두 지나갈 수 있다.
        return map[x][y] <= sharkSize;
    }

    private static boolean isEatable(int x, int y) {
        //아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다. 따라서, 크기가 같은 물고기는 먹을 수 없지만, 그 물고기가 있는 칸은 지나갈 수 있다.
        return map[x][y] != 0 && map[x][y] < sharkSize;
    }

    private static boolean isInArea(int x, int y) {
        //범위 체크
        return x <= N && x > 0 && y <= N && y > 0;
    }


    private static class Point {
        private int x;
        private int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    private static void print() {
        System.out.println();
        System.out.println("minY : " + minY + "\tminX : " + minX);
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(dist[i][j] + "\t");
            }
            System.out.println();
        }
    }
}


/*

접근 방식
만족해야하는 조건이 많아 조금 까다로웠던 문제. 기본 알고리즘 풀이법에서 응용을 해야했지만 같은 사이즈 물고기 중 "최단 거리"에 있는 것을 찾는 문제이기 때문에 bfs로 접근했다.

돌아가는 로직 순서는 다음과 같다.
1. 입력받은 값 중 9인 경우(상어가 있는 경우)의 좌표를 따로 저장한다. (sharkX, sharkY)
- 좌표를 표시했으면 해당 칸을 0으로 바꿔논다. 탐색 시에 똑같은 빈칸으로 취급해야하기 때문.

2. while 문 안에서 bfs를 시작한다.
- 이후에 자세히 설명되겠지만 while문이 시작될 때마다 minX, minY, dist[][]를 초기화한다.
- bfs에 들어가는 sharkX, sharkY 값이 minX, minY에 의해 좌우되는데 minX, minY가 초기화 값이랑 일치한다는 것은 더이상 탐색할 곳이 없다는 것을 의미한다.
- dist[][]는 방문 여부 체크와 함께 시작점에서 해당 좌표까지의 거리를 표시한다. (dist[][] == 0이면 방문 안한 곳)

3. bfs 내부에서는 큐를 이용해 움직일 수 있는 곳이 없을 때까지 계속해서 탐색한다.
- 탐색하려는 좌표가 범위 안에 있고(isInArea()), 움직일 수 있는 조건(isAbleToMove())이고, 방문하지 않은 곳(dist[][] == 0)인 경우 큐에 들어 갈 수 있다.
- 큐에 들어가는 조건을 만족하는 경우 dist[newX][newY] = dist[x][y] + 1를 통해 이전까지 거리에 1을 추가한 값을 넣는다. (방문 체크도 함께 됨)
- 제일 중요한 부분! 해당 좌표에 먹을 수 있는 사이즈의 물고기가 있는 경우(isEatable())에 minX, minY, minDist와 비교해 최소값을 저장한다. 물고기를 찾았다고해서
  바로 넣는 것이 아니라 최소값에 대한 정보를 가지고 있다가 큐가 끝난 경우에 최종적으로 넣어야한다. 이는 문제의 조건처럼 가장 가까운 위치에 있는 물고기가 여러 마리인 경우
  조건에 해당하는 물고기를 먼저 먹기 위함이다.

4. bfs가 끝나면 다음 bfs로 넘어가기 위한 준비를 한다.
- 상어 사이즈(sharkSize)와 물고기 먹은 횟수(eatingCount) 같은 경우 상어 사이즈++
- 먹힌 물고기 좌표를 0으로 바꿔준다. (map[][] = 0)
- minX, minY는 새로운 상어 위치가 된다.
- 해당 좌표에 담긴 거리값을 총 거리에 추가한다. (count += dist[minX][minY]

5. minX, minY가 초기화한 값과 동일한 경우 탐색할 곳이 없다는 의미이므로 while문을 종료한다.

처음에 구현할 때, bfs탐색을 하는 경우 가장 먼저 발견되는 곳이 가장 가까운 곳이라고 생각하고 구현했다가 fail의 고배를 마셨다. 문제를 잘 읽는걸 잘해야겠다..

 */