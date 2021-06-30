package algo.세그먼트트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_6549_히스토그램에서가장큰직사각형 {

    private static final int INF = 1_000_000_000;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] arr;
    private static int[] tree;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if (N == 0) break;

            arr = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            tree = new int[N * 4];
            init(1, N, 1);

            sb.append(getMaxWidth(1, N)).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    // 세그먼트 트리의 각 노드엔 담당하는 구간의 최솟값을 가지는 인덱스를 저장한다.
    private static int init(int start, int end, int node) {
        if (start == end) return tree[node] = start;

        int mid = (start + end) / 2;
        int leftMinIndex = init(start, mid, node * 2);
        int rightMinIndex = init(mid + 1, end, node * 2 + 1);

        return tree[node] = arr[leftMinIndex] < arr[rightMinIndex] ? leftMinIndex : rightMinIndex;
    }

    // i~j 구간에서 최솟값을 가지는 인덱스를 반환한다.
    private static int query(int start, int end, int node, int i, int j) {
        if (i > end || j < start) return INF;

        if (i <= start && end <= j) return tree[node];

        int mid = (start + end) / 2;
        int leftMinIndex = query(start, mid, node * 2, i, j);
        int rightMinIndex = query(mid + 1, end, node * 2 + 1, i, j);

        if (leftMinIndex == INF) return rightMinIndex;
        else if (rightMinIndex == INF) return leftMinIndex;
        else return arr[leftMinIndex] < arr[rightMinIndex] ? leftMinIndex : rightMinIndex;
    }

    // i~j 구간에서의 최대 넓이를 찾는 메서드.
    private static long getMaxWidth(int i, int j) {
        long maxWidth, tmpWidth;
        int minIndex = query(1, N, 1, i, j);

        // 최소 높이를 바탕으로 넓이 계산.
        maxWidth = (long) (j - i + 1) * (long) arr[minIndex];

        // 왼쪽 존재 ?
        if (i < minIndex) {
            tmpWidth = getMaxWidth(i, minIndex - 1);
            maxWidth = Math.max(maxWidth, tmpWidth);
        }

        // 오른쪽 존재 ?
        if (minIndex < j) {
            tmpWidth = getMaxWidth(minIndex + 1, j);
            maxWidth = Math.max(maxWidth, tmpWidth);
        }

        return maxWidth;
    }
}