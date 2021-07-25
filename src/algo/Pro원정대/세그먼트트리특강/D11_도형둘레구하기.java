package algo.Pro원정대.세그먼트트리특강;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D11_도형둘레구하기 {
    static class Line implements Comparable<Line>
    {
        int x;
        int min_y;
        int max_y;
        int state;
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
            return 0;
        }
        @Override
        public String toString() {
            return "Line [x=" + x + ", min_y=" + min_y + ", max_y=" + max_y + ", state=" + state + "]";
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static Line line[];
    static int n, ans, square[][];
    static int tree[], cnt[];

    static void update(int node, int start, int end, int left, int right, int state) {
        if (end < left || right < start) return;

        if (left <= start && end <= right)
            cnt[node] += state;
        else
        {
            update(node * 2, start, (start + end) / 2, left, right, state);
            update(node * 2 + 1, (start + end) / 2 + 1, end, left, right, state);
        }

        if (cnt[node] != 0)
            tree[node] = end - start + 1;
        else
        {
            if (start == end)
                tree[node] = 0;
            else
                tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    static void sweeping() {

        line = new Line[2 * n];
        for (int i = 0; i < n; i++) {
            line[i] = new Line (square[i][0],square[i][1],square[i][3],1);
            line[i + n] = new Line (square[i][2],square[i][1],square[i][3],-1 );
        }

        Arrays.sort(line);

        cnt = new int[20001 * 5];
        tree = new int[20001 * 5];
        for (int i = 0, tmp = 0; i < 2 * n; i++) {
            update(1, 1, 20001, line[i].min_y, line[i].max_y - 1, line[i].state);
            ans += Math.abs(tree[1] - tmp);
            tmp = tree[1];
        }

    }

    public static void swap(int arr[], int a, int b)
    {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        n = Integer.parseInt(br.readLine());
        square = new int[n][4];
        for(int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++)
            {
                square[i][j] = Integer.parseInt(st.nextToken());
                square[i][j] += 10001;
            }
            if (square[i][0] > square[i][2])
                swap(square[i], 0, 2);
            if (square[i][1] > square[i][3])
                swap(square[i], 1, 3);
        }
        sweeping();

        for (int i = 0; i < n; i++)
        {
            swap(square[i], 0, 1);
            swap(square[i], 2, 3);
        }

        sweeping();

        System.out.println(ans);
    }
}
