package algo.기하학;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_1085_직사각형에서탈출 {
    static int x,y,w,h;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        //현재위치 좌표점 기준으로 상하좌우 길이 체크해서 가장 작은 길이 값이 정답이 된다.
        //0,0 기준으로 현재의 x, y 위치값 계산하기
        int res1 = Math.min(Math.abs(x-0), Math.abs(y-0));

        //w,h 기준으로 현재의 x, y 위치값 계산하기
        int res2 = Math.min(Math.abs(x-w), Math.abs(y-h));

        System.out.println(Math.min(res1, res2));
    }
}
