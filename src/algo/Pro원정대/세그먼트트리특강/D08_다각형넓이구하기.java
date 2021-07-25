package algo.Pro원정대.세그먼트트리특강;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D08_다각형넓이구하기 {
    static class Line implements Comparable<Line>{
        int x; // 해당 선분의 x좌표
        int min_y; // 해당 선분의 시작하는 y좌표
        int max_y; // 해당 선분의 끝나는 y좌표
        int state; // 해당 선분이 추가(1)되는 것인지 빼지는 것(-1)인지
        Line(int x, int min_y, int max_y, int state)
        {
            this.x = x;
            this.min_y = min_y;
            this.max_y = max_y;
            this.state = state;
        }
        @Override
        public int compareTo(Line right) {
            if(x < right.x) return -1;
            if(x > right.x) return 1;
            if(state < right.state) return -1;
            if(state > right.state) return 1;
            return 0;
        }
    }


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static Line line[];
    static int tree[]; // node가 갖는 구간 안의 선분 길이
    static int cnt[]; // node가 완벽한 선분을 이루는지
    static void update(int node, int start, int end, int min_y, int max_y, int state)
    // node번째의 data는 start~end 선분에 대한 정보를 갖는다.
    // min_y ~ max_y선분이 추가(state = 1)되거나 삭제(state = -1)된다.
    {
        if(max_y < start || end < min_y)
            return; // node의 구간이 원하는 구간(min_y ~ max_y)에 아에 포함되지 않는 경우
        if(min_y <= start && end <= max_y)
        // node의 구간이 원하는 구간에 완벽하게 포함되는 경우
        {
            cnt[node] += state; // start~end의 완벽한 선분이 1개 있는 것이므로 추가
        }
        else
        // node의 구간이 원하는 구간에 걸쳐있다.
        // start ~ end : 1 ~ 5, min_y~max_y : 3 ~ 8
        {
            // 왼쪽으로 구간 쪼개주기
            update(node * 2, start, (start + end) / 2, min_y, max_y, state);
            // 오른쪽으로 구간 쪼개주기
            update(node * 2 + 1, (start + end) / 2 + 1, end, min_y, max_y, state);
        }
        // start ~ end 선분이 있는지 없는지

        // start ~ end까지 선분들의 길이
        if(cnt[node] != 0)
        // node의 구간에 완벽하게 일치하는 선분이 존재한다!
        {
            tree[node] = end - start + 1;
        }
        else
        // node의 구간에 완벽하게 일치하는 선분은 존재하지 않는다!
        {
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }


    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        line = new Line[2 * n];
        tree = new int[5 * 30001];
        cnt = new int[5 * 30001];
        int line_cnt = 0;
        for(int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            line[line_cnt++] = new Line(x1, y1, y2, 1);
            // x1좌표에 y1~y2에 해당하는 선분이 추가
            line[line_cnt++] = new Line(x2, y1, y2, -1);
            // x2좌표에 y1~y2에 해당하는 선분이 제거
        }
        Arrays.sort(line);
        int ans = 0;
        for(int i = 0; i < 2 * n - 1; i++)
        {
            int dx = line[i + 1].x - line[i].x;
            // 이번 직사각형의 밑변 길이
            // line[i]선분에 대한 정보를 tree와 cnt에 update
            update(1, 0, 30000, line[i].min_y, line[i].max_y - 1, line[i].state);
            int dy = tree[1]; // 모든 구간에 대한 정보를 갖는 1번 node의 정보(총 선분 길이)
            // 이번 직사각형들의 넓이를 계산해서 누적
            ans += dx * dy;
        }
        System.out.println(ans);
    }
}
