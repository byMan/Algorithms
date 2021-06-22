package algo.기하학;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_1002_터렛 {
    static int t, x1, y1, r1, x2, y2, r2;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for(int k=0; k<t; k++){
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            r1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            r2 = Integer.parseInt(st.nextToken());

            sb.append(target_point(x1, y1, r1, x2, y2, r2)).append("\n");
        }
        System.out.println(sb);
    }

    public static int target_point(int x1, int y1, int r1, int x2, int y2, int r2){
        //중점간 거리 distance
        int distancePow = (int)(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

        //case1 : 중점이 같으면서 반지듦도 같을 경우 접점은 무한대
        if(x1==x2 && y1==y2 && r1==r2){
            return -1;
        }

        //case2 : 두 원의 반지름 합보다 중점간 거리가 더 긴 경우 접점은 0개임
        else if(distancePow > Math.pow(r1 + r2, 2)){
            return 0;
        }

        //case3 : 원 안에 원이 있으나 내접하지 않는 경우
        else if(distancePow < Math.pow(r1 - r2, 2)){
            return 0;
        }

        //case4 : 원 안에 원이 있으면서 내접하는 경우 접점 1개
        else if(distancePow == Math.pow(r1 - r2, 2)){
            return 1;
        }

        //case5 : 두 원이 외접하는 경우 접점이 1개
        else if(distancePow == Math.pow(r1 + r2, 2)){
            return 1;
        }

        //그 외의 모든 경우는 접점이 오직 2개만 있을 수 있다.
        else{
            return 2;
        }
    }
}
