package algo.기하학;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

//아래 설명 참고
//https://coder-in-war.tistory.com/entry/%EA%B0%9C%EB%85%90-45-%EB%B3%BC%EB%A1%9D-%EA%BB%8D%EC%A7%88Convex-Hull

public class Bj_1708_블록껍질 {

    //x좌표와 y좌표의 절대값은 40,000을 넘지 않는다
    static Point first = new Point(40001, 40001);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        ArrayList<Point> points = new ArrayList<Point>();
        //입력으로부터 각 점들의 x, y좌표를 받아옴
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        //y좌표가 가장 작은 점이나, 점들 중에서 x좌표값이 가장 작음 점을 기준점으로 잡음
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).y < first.y) {
                first = points.get(i);
            } else if (points.get(i).y == first.y) {
                if (points.get(i).x < first.x) {
                    first = points.get(i);
                }
            }
        }
        //기준점과 나머지점들이 ccw로 반시계방향(좌회전)이 되도록 정렬을 시키고, 만약 일직선상에 있으면 거리가 증가하게끔 정렬을 시킴
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point second, Point third) {
                int ccwR = ccw(first, second, third);
                if (ccwR > 0)
                    return -1;
                else if (ccwR < 0)
                    return 1;
                else if (ccwR == 0) {
                    long distR1 = dist(first, second);
                    long distR2 = dist(first, third);
                    if (distR1 > distR2)
                        return 1;
                }
                return -1;
            }
        });

        //그라함 스캔 알고리즘
        Stack<Point> stack = new Stack<Point>();
        stack.add(first);
        for (int i = 1; i < points.size(); i++) {
            while (stack.size() > 1 && ccw(stack.get(stack.size() - 2), stack.get(stack.size() - 1), points.get(i)) <= 0) {
                stack.pop();
            }
            stack.add(points.get(i));
        }
        bw.write(stack.size() + "\n");

        bw.flush();
        br.close();
        bw.close();
    }

    static int ccw(Point a, Point b, Point c) {
        long ccwR = (a.x * b.y + b.x * c.y + c.x * a.y) - (b.x * a.y + c.x * b.y + a.x * c.y);
        if (ccwR > 0)
            return 1;
        if (ccwR < 0)
            return -1;
        return 0;
    }

    static long dist(Point a, Point b) {
        return (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y);
    }

    static class Point {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}