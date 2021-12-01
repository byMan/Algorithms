import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static int N;
    private static final int INF = Integer.MAX_VALUE;
    private static int[] input, tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        input = new int[N + 1];
        int NN = 1;
        while (N > NN) {
            NN *= 2;
        }
        tree = new int[NN * 2];

        for (int i = 1; i <= N; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        init(1, 1, N);

        System.out.println(getMaxWidth(1, N));
    }

    private static int init(int node, int start, int end) {
        if (start == end) {
            return tree[node] = start;
        }

        int mid = (start + end) / 2;
        int l = init(node * 2, start, mid);
        int r = init(node * 2 + 1, mid + 1, end);
        return tree[node] = input[l] < input[r] ? l : r;
    }


    private static int query(int node, int start, int end, int ts, int te) {
        if(start > te || end < ts){
            return INF;
        }

        if (start >= ts && end <= te) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        int l = query(node * 2, start, mid, ts, te);
        int r = query(node * 2 + 1, mid + 1, end, ts, te);

        if(l == INF) return r;
        if(r == INF) return l;
        return input[l] < input[r] ? l : r;
    }


    private static int getMaxWidth(int start, int end) {

        int minIndex = query(1, 1, N, start, end);
        int maxWidth = (end - start + 1) * input[minIndex];

        int tmpMaxWidth;

        if (start < minIndex) {
            tmpMaxWidth = getMaxWidth(start, minIndex - 1);
            maxWidth = Math.max(maxWidth, tmpMaxWidth);
        }

        if (minIndex < end) {
            tmpMaxWidth = getMaxWidth(minIndex + 1, end);
            maxWidth = Math.max(maxWidth, tmpMaxWidth);
        }

        return maxWidth;
    }
}