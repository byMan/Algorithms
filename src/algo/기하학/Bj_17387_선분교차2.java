package algo.기하학;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//선분교차 원리 영상 참고 : https://www.youtube.com/watch?v=aVX5rOZuwjQ
public class Bj_17387_선분교차2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Pair a = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Pair b = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        Pair c = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Pair d = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        if(Solve(a, b, c, d))
            System.out.println(1);
        else
            System.out.println(0);

    }

    public static boolean Solve(Pair a, Pair b, Pair c, Pair d){
        int abc = ccw(a, b, c);
        int abd = ccw(a, b, d);
        int cda = ccw(c, d, a);
        int cdb = ccw(c, d, b);

        //선분 두 개가 하나의 일직선 상에 있는 경우
        if(abc * abd == 0 && cda * cdb == 0){

            //한 직선의 두 접점 중 큰 값을 찾는다.
            if(a.cmp(b)){
                Pair tmp = a;
                a = b;
                b = tmp;
            }

            //다른 한 직선의 접점 중 큰 값을 찾는다.
            if(c.cmp(d)){
                Pair tmp = c;
                c = d;
                d = tmp;
            }

            //두 선의 한쪽 접점이 이어진 경우라면,
            //즉, 한 직선의 가장 큰 점과 나머지 한 직선의 작은 점이 서로 일치한다면 일직선이며 연결되어 있다.
            if(b.eq(c) || a.eq(d)){
                return true;
            }

            //일직선 상에서 두 직선이 중첩되어 한 직선으로 연결된 경우를 말한다.
            // A----C-----B-------D
            // A----C-----B-------D
            // C----A-----B-------D
            // C----A-----D-------B
            return b.cmp(c) != a.cmp(d);
        }

        //두 선분이 교차하는 경우
        return abc * abd <= 0 && cda * cdb <= 0;
    }


    public static int ccw(Pair a, Pair b, Pair c){
        //x와 y의 빼는 순서는 뒤집어져도 상관 없다.
        long x = a.y * b.x + b.y * c.x + c.y * a.x;
        long y = a.x * b.y + b.x * c.y + c.x * a.y;

        if(x - y > 0)       return 1;   //반시계 방향
        else if(x - y == 0) return 0;   //일직선인 경우
        else                return -1;  //시계방향
    }

    static class Pair{
        //멤버변수 자료형을 int로 하면 틀린다.
        long x;
        long y;

        public Pair(long x, long y){
            super();
            this.x = x;
            this.y = y;
        }

        public boolean cmp(Pair in){
            if(x > in.x){
                return true;
            }
            if(x == in.x){
                if(y >= in.y){
                    return true;
                }
            }
            return false;
        }

        public boolean eq(Pair in){
            if(x == in.x && y == in.y){
                return true;
            }
            return false;
        }
    }
}
