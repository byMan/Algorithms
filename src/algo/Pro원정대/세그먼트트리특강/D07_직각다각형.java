package algo.Pro원정대.세그먼트트리특강;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D07_직각다각형 {
    static class Line implements Comparable<Line>{
        int pos; // 좌표
        int state; // 선분이 시작되면 1, 선분이 끝나면 -1
        Line(int pos, int state)
        {
            this.pos = pos;
            this.state = state;
        }
        @Override
        public int compareTo(Line right) {
            // sweeping을 진행할 때 좌표 순서대로 꺼내서 사용할 것이므로 정렬기준을 만들어 줌
            if(pos < right.pos) return -1;
            if(pos > right.pos) return 1;

            // 선분이 시작되는 것보다 선분이 끝나는 것을 우선하여 계산할 필요가 있음
            if(state < right.state) return -1;
            if(state > right.state) return 1;
            return 0;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Line x_line[]; // y 선분 정보
    static Line y_line[]; // x 선분 정보
    static int n, x_cnt, y_cnt; // n : 점의 개수, x_cnt : x선분 정보 개수, y_cnt : y 선분 정보 개수
    static int arr[][]; // 꼭지점 정보
    static void divide()
    // 꼭지점을 보고 세로 선분과 가로 선분을 나눠주는 역할
    {
        x_cnt = 0;
        y_cnt = 0;
        for(int i = 0; i < n; i++)
        {
            if(arr[i][0] == arr[i + 1][0])
            // 이번 x좌표와 다음 x좌표가 같으면 세로 선분
            {
                y_line[y_cnt] = new Line(Math.min(arr[i][1], arr[i + 1][1]), 1);
                // 이번 y좌표와 다음 y좌표 중 작은 쪽을 선분이 시작, 선분이 시작하는 것이므로 state : 1
                y_cnt++;
                y_line[y_cnt] = new Line(Math.max(arr[i][1], arr[i + 1][1]), -1);
                // 이번 y좌표와 다음 y좌표 중 작은 쪽을 선분이 끝, 선분이 끝나는 것이므로 state : -1
                y_cnt++;
            }
            else
            // 이번 x좌표와 다음 x좌표가 같지 않으면 가로 선분
            {
                x_line[x_cnt] = new Line(Math.min(arr[i][0], arr[i + 1][0]), 1);
                // 이번 x좌표와 다음 x좌표 중 작은 쪽을 선분이 시작, 선분이 시작하는 것이므로 state : 1
                x_cnt++;
                x_line[x_cnt] = new Line(Math.max(arr[i][0], arr[i + 1][0]), -1);
                // 이번 x좌표와 다음 x좌표 중 작은 쪽을 선분이 끝, 선분이 끝나는 것이므로 state : -1
                x_cnt++;
            }
        }
    }
    static int ans = 0;
    static void sweeping()
    {
        Arrays.sort(x_line);
        Arrays.sort(y_line);
        // sweeping은 좌표 순서대로 선분 정보를 꺼내서 누적하는 방식을 사용
        // 좌표 순서대로 정렬하여 사용하면 효율적이기에 좌표 성분들을 정렬
        int sum = 0;
        for(int i = 0; i < x_cnt; i++)
        // x 선분 정보들을 x좌표 순서대로 꺼냄
        {
            sum += x_line[i].state;
            // 이번에 꺼낸 state을 sum에 누적함으로 이번 선분까지 고려된 x선분이 겹치는 개수를 구할 수 있음.
            ans = Math.max(sum, ans);
            // sum은 현재 선분정보까지 고려했을때의 겹친 선분의 개수이므로 그 중 가장 큰 값을 ans에 저장
        }

        // x선분에 대해 계산이 끝났으므로 y선분에 대해서도 같은 방식으로 겹치는 개수를 구함
        sum = 0;
        for(int i = 0; i < y_cnt; i++)
        {
            sum += y_line[i].state;
            ans = Math.max(sum, ans);
        }
    }

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1][2];
        y_line = new Line[n];
        x_line = new Line[n];
        for(int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            arr[i][0] = p1; arr[i][1] = p2;
        }
        // 마지막 꼭지점 다음엔 처음 꼭지점으로 돌아가야 하므로 n번에 첫 꼭지점의 정보를 그대로 저장
        arr[n][0] = arr[0][0];
        arr[n][1] = arr[0][1];
        divide();
        sweeping();
        System.out.println(ans);
    }
}