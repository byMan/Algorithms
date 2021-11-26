
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int INF = 1_000_000_000;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, NN;
    private static int[] arr;
    private static int[] tree;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if (N == 0) break;

            NN = 1;
            while (N > NN) {
                NN *= 2;
            }
            tree = new int[NN * 2];

            arr = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

//            initTree();
            sb.append(getMaxWidth(1, N)).append("\n");
        }

        System.out.println(sb);
        br.close();
    }


    // 세그먼트 트리의 각 노드엔 담당하는 구간의 최솟값을 가지는 인덱스를 저장한다.
    private static void init() {
        for (int i = 1; i <=N; i++) {
//            tree[i] =
        }
    }


    // start~end 구간에서 최솟값을 가지는 인덱스를 반환한다.
    private static int query(int node, int start, int end, int ts, int te) {
        if (ts > end || te < start) return INF;

        if (ts <= start && end <= te) return tree[node];

        int mid = (start + end) / 2;
        int leftMinIndex = query(node * 2, start, mid, ts, te);
        int rightMinIndex = query(node * 2 + 1, mid + 1, end, ts, te);

        //최소값을 갖는 인덱스 정보를 저장시킨다.
        if (leftMinIndex == INF) return rightMinIndex;
        else if (rightMinIndex == INF) return leftMinIndex;
        else return arr[leftMinIndex] < arr[rightMinIndex] ? leftMinIndex : rightMinIndex;
    }


    // start~end 구간에서의 최대 넓이를 찾는 메서드.
    private static long getMaxWidth(int start, int end) {
        long maxWidth, tmpWidth;

        //start와 end 구간내에서 최소값이 존재하는 인덱스값을 구한다.
        int minIndex = query(1, 1, N, start, end);

        //start와 end 구간(길이) * 최소 높이를 바탕으로 넓이 계산.
        maxWidth = (long) (end - start + 1) * arr[minIndex];

        // start와 end 구간의 최소값이 존재하는 인덱스 왼쪽에 막대가 존재 ?
        if (start < minIndex) {
            //현재 구간 이외의 구간인 왼쪽 구간에 대한 최대 넓이 값도 구한다.
            tmpWidth = getMaxWidth(start, minIndex - 1);
            //두 값을 비교해서 최대값을 구한다.
            maxWidth = Math.max(maxWidth, tmpWidth);
        }

        // start와 end 구간의 최소값이 존재하는 인덱스 오른쪽에 막대가 존재 ?
        if (minIndex < end) {
            //현재 구간 이외의 구간인 오른쪽 구간에 대한 최대 넓이 값도 구한다.
            tmpWidth = getMaxWidth(minIndex + 1, end);
            //두 값을 비교해서 최대값을 구한다.
            maxWidth = Math.max(maxWidth, tmpWidth);
        }

        return maxWidth;
    }
}