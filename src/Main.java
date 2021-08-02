import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map, dist;
    static int sharkSize = 2;   //가장 처음에 아기 상어의 크기는 2이고
    static int eatingCount = 0;
    static int sumDist = 0;
    static int sharkX, sharkY;
    static int minX, minY, minDist;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int val = sc.nextInt();
                map[i][j] = val;
                if (val == 9) {
                    sharkY = i;
                    sharkX = j;
                    //아기 상어가 먹을 수 있는 물고기가 있는 칸으로 이동했다면, 이동과 동시에 물고기를 먹는다. 물고기를 먹으면, 그 칸은 빈 칸이 된다.
                    //즉, 아기 상어가 있는 곳이라면 빈곳이다.
                    map[i][j] = 0;
                }
            }
        }

        //잡아먹을 물고기가 없을때까지 반복한다.
        while (true) {
            dist = new int[N + 1][N + 1];
            minX = Integer.MAX_VALUE;
            minY = Integer.MAX_VALUE;
            minDist = Integer.MAX_VALUE;

            //잡아먹을 물고기가 있는 위치를 검색한다.
            bfs(sharkY, sharkX);

            //더 이상 잡아먹을 물고기가 없을 경우 종료한다.
            if (minX != Integer.MAX_VALUE && minY != Integer.MAX_VALUE) {
                //물고기 한 마리를 잡아 먹었으므로 카운트 증가
                eatingCount++;

                //물고기가 잡혀 먹은 자리는 빈 곳으로 표시
                map[minY][minX] = 0;

                //아기 상어의 출발 위치는 물고기를 잡아먹은 자리로 설정
                sharkX = minX;
                sharkY = minY;

                //아기 상어가 있던 곳에서 물고기를 잡아먹으로 오기까지 이동한 거리 값 누적
                sumDist += dist[minY][minX];

                //아기 상어의 크기가 증가되어야 하는지 체크
                //아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다. 예를 들어, 크기가 2인 아기 상어는 물고기를 2마리 먹으면 크기가 3이 된다.
                if (eatingCount == sharkSize) {
                    sharkSize++;
                    eatingCount = 0;
                }
            } else {
                break;
            }
//            print();
        }
        System.out.println(sumDist);
    }


    private static void bfs(int i, int j) {
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{i, j});

        while (!q.isEmpty()) {
            Integer[] now = q.poll();
            int y = now[0];
            int x = now[1];

            for (int k = 0; k < 4; k++) {
                int ny = y + dy[k];
                int nx = x + dx[k];

                //새로운 좌표가 배열 범위 내에 있고, 이동 가능한 곳이며 dist 값이 0인 경우는 아직 방문하지 않은 경우이다.
                if (isInArea(ny, nx) && isAbleToMove(ny, nx) && dist[ny][nx] == 0) {
                    dist[ny][nx] = dist[y][x] + 1;
                    //잡아먹을 수 있는 물고기인 경우
                    if (isEatable(ny, nx)) {
                        //거리가 가장 가까운 물고기를 먹으러 간다.
                        if (minDist > dist[ny][nx]) {
                            minDist = dist[ny][nx];
                            minY = ny;
                            minX = nx;
                        } else if (minDist == dist[ny][nx]) {  //잡아 먹을 물고기가 같은 거리에 있다면
                            //거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
                            if (minX == nx) {
                                if (minY > ny) {
                                    minX = nx;
                                    minY = ny;
                                }
                            } else if (minX > nx) {    //가장 왼쪽에 있는 물고기로 이동한다.
                                minX = nx;
                                minY = ny;
                            }
                        }
                    }

                    //잡아 먹거나 지나갈 수 있는 물고기 이므로 추가한다.
                    q.add(new Integer[]{ny, nx});
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

    private static void print() {
        System.out.println();
        System.out.println("minY : " + minX + "\tminX : " + minY);
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(dist[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
