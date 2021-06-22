package algo.그래프.BFS;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_13549_숨바꼭질3 {
    static int n, k, min;
    static boolean[] visit = new boolean[200001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;

        bfs();
        System.out.println(min);
    }

    private static void bfs(){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(n, 0));

        int cnt = 0;
        while (!queue.isEmpty()) {
            Point po = queue.poll();
            visit[po.x] = true;

//            System.out.println(po.x + ":" + po.y);

            if(po.x == k){
                min = Math.min(min, po.y);
//                break;  //만약 여기서 종료하면 이보다 더 짧은 경우의 수를 파악하지 못하므로 부분 정답만 맞춘다.
            }

            //0초 후에 x*2
            if(po.x * 2 <= 100000 && !visit[po.x*2]){
                queue.add(new Point(po.x*2, po.y));
            }

            //1초 후에 x+1
            if(po.x + 1 <= 100000 && !visit[po.x+1]){
                queue.add(new Point(po.x+1, po.y+1));
            }

            //1초 후에 x-1
            if(po.x - 1 >= 0 && !visit[po.x-1]){
                queue.add(new Point(po.x-1, po.y+1));
            }
        }
    }
}
