package algo.그래프.그래프_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class mira_걸어서맛집까지 {
    static int se, ga, result, min;
    static int[][] map;
    static int[][] visit;   //최소 거리 저장용
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Queue<Integer[]> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        se = Integer.parseInt(st.nextToken());
        ga = Integer.parseInt(st.nextToken());

        min = Integer.MAX_VALUE;
        map = new int[se][ga];
        visit = new int[se][ga];

        q = new LinkedList<>();
        for(int i=0; i<se; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<ga; j++){
                int val = Integer.parseInt(st.nextToken());
                map[i][j] = val;
                if(val != 0){
                    //맛집의 각 좌표를 시작 위치로 설정해서 모든 칸에서부터 최소 거리를 셀 수 있도록 하기 위해 큐에 담는다.
                    q.add(new Integer[]{i, j, 0});
                }
            }
        }

        BFS();
        System.out.println(min);
    }


    private static void BFS(){
        while(!q.isEmpty()){
            Integer[] val = q.poll();

            int cx = val[0];    //현재 세로 좌표
            int cy = val[1];    //현재 가로 좌표
            int count = val[2]; //최소 거리

            for(int k=0; k<4; k++) {
                int nx = cx + dx[k];    //다음 세로 좌표
                int ny = cy + dy[k];    //다음 가로 좌표

                //검색 범위 벗어나면 종료
                if (nx < 0 || ny < 0 || nx >= se || ny >= ga) continue;

                if (map[nx][ny] == 0) {
                    //다음 좌표가 0이면 거리를 측정해야 하므로 큐에 담는다.
                    q.add(new Integer[]{nx, ny, count + 1});
                    //현재 좌표가 어느 맛집에서 가까운지 현재의 맛집 값을 다음 맛집의 값에 넣어준다.
                    map[nx][ny] = map[cx][cy];
                    //최소 거리의 다음 맛집 좌료에 1 증가 시켜둔다.
                    visit[nx][ny] = count + 1;
                } else if (map[cx][cy] != map[nx][ny]) {
                    System.out.println("cx:cy=" + cx + ":" + cy + "\tmap[cx][cy]=" + map[cx][cy] + "\tnx:ny=" + nx + ":" + ny + "\tmap[nx][ny]=" + map[nx][ny]);
                    //맛집과 맛집의 경계를 만나면 방문 정보를 더하고 min 값보다 작으면 더 작은 값이 min에 저장되도록 하여 최소 거리값이 저장되도록 한다.
                    //현재 위치의 거리와 다음 방문지의 거리 값을 더하는 이유는 맛집 자리는 모두 0으로 바뀌어 있고 원래 0이 있던 자리는 거리 값으로 변경되어 있다.
                    //따라서, 경계라는 것은 맛집과 거리 정보가 만나는 위치라고 보면 된다.
                    //자세한건 아래 주석을 풀고 로그를 찍어보면 알 수 있다.
                    result = visit[cx][cy] + visit[nx][ny];
                    min = min > result ? result : min;
                }
            }

//                System.out.println();
//                System.out.println("nx:ny = " + nx + ":" + ny + "\tcx:cy = " + cx + ":" + cy + "\tresult = " + result + "\tmin = " + min);
//                System.out.println("Map 정보");
//                for (int i = 0; i < map.length; i++) {
//                    for (int j = 0; j < map.length; j++) {
//                        System.out.print(map[i][j] + "\t");
//                    }
//                    System.out.println();
//                }
//                System.out.println();
//
//                System.out.println();
//                System.out.println("visit 정보");
//                for (int i = 0; i < map.length; i++) {
//                    for (int j = 0; j < map.length; j++) {
//                        System.out.print(visit[i][j]);
//                    }
//                    System.out.println();
//                }
//                System.out.println();
//            }


        }
    }
}
