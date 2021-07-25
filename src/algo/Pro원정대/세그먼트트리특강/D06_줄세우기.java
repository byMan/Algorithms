package algo.Pro원정대.세그먼트트리특강;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D06_줄세우기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int tree[];
    static int height[];
    static int arr[];

    static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = 1; // 최 하단을 모두 1로 세팅(아직 사람이 있다.)
            return;
        }
        init(node * 2, start, (start + end) / 2);
        init(node * 2 + 1, (start + end) / 2 + 1, end);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static int index = -1;

    static void query(int node, int start, int end, int value) {
        if (start == end) // 최하단(1명을 특정지을 수 있으면)
        {
            index = start; // 그 사람이 몇번인지 기록
            tree[node] = 0; // 그 사람은 사용했다라고 0으로 갱신
            return;
        }
        if (value <= tree[node * 2]) // value 번째로 작은 사람이 '왼쪽'(작은 사람쪽)에 포함되는가?
            // 왼쪽으로 가서 탐색
            query(node * 2, start, (start + end) / 2, value);
        else
            // 오른쪽으로 가서 탐색
            query(node * 2 + 1, (start + end) / 2 + 1, end, value - tree[node * 2]);
        tree[node] = tree[node * 2] + tree[node * 2 + 1]; // 하단 값을 이용해서 부모 node를 갱신
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        height = new int[n];
        arr = new int[n];
        tree = new int[n * 5];
        for (int i = 0; i < n; i++) {
            height[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(height);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        init(1, 0, n - 1);
        int ans[] = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            query(1, 0, n - 1, arr[i] + 1);
            ans[i] = height[index];
        }
        for (int i = 0; i < n; i++) {
            System.out.println(ans[i]);
        }
    }
}