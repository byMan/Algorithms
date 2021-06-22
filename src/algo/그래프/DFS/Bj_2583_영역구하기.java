package algo.그래프.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_2583_영역구하기 {
    static int M,N,K;
    static boolean[][] visit;
    static int[][] ad;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int component = 0;
    static int width;
    public static void main(String []args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int num = 0;
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); //직사각형 개수
        int size[] = new int[100];
        visit = new boolean[M][N];
        ad = new int[M][N];

        //입력
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int y_first = Integer.parseInt(st.nextToken());
            int x_first = Integer.parseInt(st.nextToken());
            int y_last = Integer.parseInt(st.nextToken()) - 1;
            int x_last = Integer.parseInt(st.nextToken()) - 1;
            for(int m = x_first; m <= x_last; m++){
                for(int n = y_first; n <= y_last; n++){
                    ad[m][n] = 1;
                }
            }
        }

        //dfs시작
        for(int i = 0; i < ad.length; i++){
            for(int j = 0; j < ad[i].length; j++){
                if(!visit[i][j] && ad[i][j] == 0){
                    width = 1;
                    size[num] = dfs(i,j);
                    component++;
                    num++;
                }
            }
        }

        System.out.println(component);
        Arrays.sort(size,0,num);
        for(int i = 0; i < num; i++)
            System.out.print(size[i] + " ");

    }
    static int dfs(int x, int y){
        visit[x][y] = true;
        for(int i = 0; i < 4; i++){
            int row = x + dx[i];
            int col = y + dy[i];
            if(isRangeTrue(row,col) && !visit[row][col] && ad[row][col] == 0) {
                dfs(row, col);
                width++;
            }
        }
        return width;
    }

    static boolean isRangeTrue(int x, int y){
        return x >= 0 && x < M && y >= 0 && y < N;
    }
}


/*
문제
눈금의 간격이 1인 M×N(M,N≤100)크기의 모눈종이가 있다. 이 모눈종이 위에 눈금에 맞추어 K개의 직사각형을 그릴 때, 이들 K개의 직사각형의 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어진다.

예를 들어 M=5, N=7 인 모눈종이 위에 <그림 1>과 같이 직사각형 3개를 그렸다면, 그 나머지 영역은 <그림 2>와 같이 3개의 분리된 영역으로 나누어지게 된다.

<그림 2>와 같이 분리된 세 영역의 넓이는 각각 1, 7, 13이 된다.

M, N과 K 그리고 K개의 직사각형의 좌표가 주어질 때, K개의 직사각형 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어지는지, 그리고 분리된 각 영역의 넓이가 얼마인지를 구하여 이를 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 M과 N, 그리고 K가 빈칸을 사이에 두고 차례로 주어진다. M, N, K는 모두 100 이하의 자연수이다. 둘째 줄부터 K개의 줄에는 한 줄에 하나씩 직사각형의 왼쪽 아래 꼭짓점의 x, y좌표값과 오른쪽 위 꼭짓점의 x, y좌표값이 빈칸을 사이에 두고 차례로 주어진다. 모눈종이의 왼쪽 아래 꼭짓점의 좌표는 (0,0)이고, 오른쪽 위 꼭짓점의 좌표는(N,M)이다. 입력되는 K개의 직사각형들이 모눈종이 전체를 채우는 경우는 없다.

출력
첫째 줄에 분리되어 나누어지는 영역의 개수를 출력한다. 둘째 줄에는 각 영역의 넓이를 오름차순으로 정렬하여 빈칸을 사이에 두고 출력한다.

예제 입력 1
5 7 3
0 2 4 4
1 1 2 5
4 0 6 2

예제 출력 1
3
1 7 13



[ 문제 풀이 ]
참고 : https://hyonee.tistory.com/59

이 문제는 다른 문제와 달리 비어있는 곳의 배열을 방문하고 넓이를 구하는 것이다. 그래서 dfs를 돌릴 때 배열의 값이 1이 아닌 0을 가진 값으로 돌렸다.
또한, 이 문제는 다른 문제와 다른 점이 행렬로 나타내지 않고 좌표로 나타내 주었다. 그래서 배열과는 입력이 조금 다르고,
그림 모양도 달랐지만 결국 넓이 구하는 것은 똑같기 때문에 dfs 메소드를 구현하는 건 어렵지 않았다. 그러나 입력 부분에서 조금 어려움이 있었다.
0 2 4 4에서 0 2 는 직사각형 왼쪽 아래의 꼭짓점 좌표, 4 4 는 직사각형 오른쪽 위의 꼭짓점 좌표이다.
여기서 좌표가 그대로 배열의 index값으로 들어간다고 생각하면 바로 틀리게 된다. 예시에서 보면 0,0 이 왼쪽 아래에서부터 시작하지만 배열은 왼쪽 위부터 시작하게 된다.
그래서 이 좌표 그림을 뒤집는다고 생각하고 오른쪽 위가 0,0이 되게 생각해보자.
그러면 0 2 는 배열 index로 [2][0] 이 되고 [4][4]는 [3][3]이 된다.
즉, 왼쪽 아래 꼭짓점 좌표는 서로 x y 값이 바뀌고, 오른쪽 위 꼭짓점 좌표는 -1 하면서 x y 값이 바뀐다.
그래서 입력 부분을 보면 x_last와 y_last에 -1이 된 것을 볼 수 있다.
그래서 좌표값은 배열 index로 갈 떄 반댓값이 되기 때문에,
처음에 입력받을 때도 y_first x _fisrt 즉, y x 순으로 입력을 받았다. 나머지 component 개수와, size 구하는 건 알 것이다.
여기서 Arrays.sort(size,0,num) 의 뜻은 size배열에서 0 ~ num-1까지의 배열을 정렬한다는 메소드이다.

 */