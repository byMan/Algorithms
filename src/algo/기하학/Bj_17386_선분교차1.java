package algo.기하학;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_17386_선분교차1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        Point a = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        Point b = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        Point c = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        Point d = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));

        if(Solve(a, b, c, d)){
            System.out.println(1);
        }else{
            System.out.println(0);
        }
    }

    private static boolean Solve(Point a, Point b, Point c, Point d){
        int abc = ccw(a, b, c);
        int abd = ccw(a, b, d);
        int cda = ccw(c, d, a);
        int cdb = ccw(c, d, b);

        if(abc * abd == 0 && cda * cdb == 0){
            if(a.cmp(b)) swap(a, b);
            if(c.cmp(d)) swap(c, d);
            return a.x <= d.x && c.x <= b.x;
        }
        return abc * abd <= 0 && cda * cdb <= 0;
    }

    private static int ccw(Point a, Point b, Point c){
        long x = a.x * b.y + b.x * c.y + c.x * a.y;
        long y = a.y * b.x + b.y * c.x + c.y * a.x;

        if(x - y > 0)            return 1;
        else if(x - y == 0)      return 0;
        else                    return -1;
    }

    private static void swap(Point a, Point b){
        Point tmp = a;
        a = b;
        b = tmp;
    }

    static class Point {
        long x;
        long y;
        public Point(long x, long y){
            this.x = x;
            this.y = y;
        }
        public boolean cmp(Point in){
            if(x > in.x)
                return true;
            if(x == in.x){
                if(y > in.y)
                    return true;
            }
            return false;
        }
    }
}
