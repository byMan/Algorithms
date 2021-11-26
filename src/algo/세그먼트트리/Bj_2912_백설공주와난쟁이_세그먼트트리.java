package algo.세그먼트트리;

import java.io.*;
import java.util.StringTokenizer;

public class Bj_2912_백설공주와난쟁이_세그먼트트리 {
    private static int N;
    private static int C;
    private static int M;
    private static int[] input;
    private static int[][] color;
    private static int[] count;
    private static Dwarf[] tree;
    private static int NN;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream(new File("백설공주와난쟁이")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        input = new int[N + 1];
        color = new int[C + 1][];
        count = new int[C + 1];

        NN = 1;
        while (N > NN) {
            NN *= 2;
        }
        tree = new Dwarf[NN * 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int c = Integer.parseInt(st.nextToken());
            input[i] = c;
            count[c]++;
        }

        for (int i = 1; i <= C; i++) {
            color[i] = new int[count[i]];
            count[i] = 0;
        }

        for (int i = 1; i <= N; i++) {
            int c = input[i];
            color[c][count[c]] = i;
            count[c]++;
        }

//		for(int i=1; i<=C; i++) {
//			System.out.print(i + " : ");
//			for(int a : color[i]) {
//				System.out.print(a + " ");
//			}
//			System.out.println();
//		}

        initTree(1, 1, N);

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //a ~ b구간까지의 가장 많은 색상과 갯수
            Dwarf dwarf = query(1, 1, N, a, b);

            if (dwarf.color == 0) {
                bw.append("no").append("\n");
                continue;
            }
            //그때 나온 색상의 갯수과 a~b구간에서 과반수가 넘는가?
            if ((b - a + 1) / 2 < dwarf.count) {
                bw.append("yes " + dwarf.color).append("\n");
            } else {
                bw.append("no").append("\n");
            }
        }

        bw.close();
    }

    private static Dwarf query(int node, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) {
            return new Dwarf(0, 0);
        }

        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = (l + r) / 2;

        Dwarf left = query(2 * node, l, mid, ql, qr);
        Dwarf right = query(2 * node + 1, mid + 1, r, ql, qr);

        if (left.color == right.color) {
            return new Dwarf(left.color, upper(left.color, qr) - lower(left.color, ql));
        }

        int lCount = upper(left.color, qr) - lower(left.color, ql);
        int rCount = upper(right.color, qr) - lower(right.color, ql);

        if (lCount < rCount) {
            return new Dwarf(right.color, rCount);

        } else if (lCount > rCount) {
            return new Dwarf(left.color, lCount);

        } else {
            //갯수가 같은 경우
            return new Dwarf(0, 0);
        }
    }

    private static Dwarf initTree(int node, int l, int r) {
        if (l == r) {
            //1 ~ 1 구간이다 : input[1]에 있는 1번색상, 갯수 1
            return tree[node] = new Dwarf(input[l], 1);
        }

        int mid = (l + r) / 2;

        Dwarf left = initTree(2 * node, l, mid);
        Dwarf right = initTree(2 * node + 1, mid + 1, r);

        if (left.color == right.color) {
            //관리구간 l ~ r
            return tree[node] = new Dwarf(left.color, upper(left.color, r) - lower(left.color, l));
        }

        //왼쪽 색상과 오른쪽 색상이 다를 때,

        int lCount = upper(left.color, r) - lower(left.color, l);
        int rCount = upper(right.color, r) - lower(right.color, l);

        if (lCount < rCount) {
            return tree[node] = new Dwarf(right.color, rCount);

        } else if (lCount > rCount) {
            return tree[node] = new Dwarf(left.color, lCount);

        } else {
            //갯수가 같은 경우
            return tree[node] = new Dwarf(0, 0);
        }
    }

    private static int upper(int num, int target) {
        if (num == 0) {
            return 0;
        }

        int left = 0;
        int right = color[num].length;
        int mid = 0;

        while (left < right) {
            mid = (left + right) / 2;

            //l mid target r
            if (color[num][mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    private static int lower(int num, int target) {
        if (num == 0) {
            return 0;
        }

        int left = 0;
        int right = color[num].length;
        int mid = 0;

        while (left < right) {
            mid = (left + right) / 2;

            //l mid target r
            if (color[num][mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    public static class Dwarf {
        public int color;
        public int count;

        public Dwarf(int color, int count) {
            this.color = color;
            this.count = count;
        }
    }
}
