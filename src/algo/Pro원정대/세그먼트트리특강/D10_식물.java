package algo.Pro원정대.세그먼트트리특강;

import java.io.*;
import java.util.StringTokenizer;

public class D10_식물 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int max_range = 100000;
    static int tree[] = new int[max_range * 4 + 1];

    static void update(int node, int start, int end, int index, int value)
    {
        if(index < start || end < index)
            return;
        if (start == end)
        {
            tree[node] += value;
            return;
        }
        update(node * 2, start, (start + end) / 2, index, value);
        update(node * 2 + 1, (start + end) / 2 + 1, end, index, value);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static int query(int node, int start, int end, int left, int right)
    {

        if(right < start || end < left)
            return 0;
        if(left <= start && end <= right)
            return tree[node];
        int m1 = query(node * 2, start, (start + end) / 2, left, right);
        int m2 = query(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        return m1 + m2;

    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        int n;
        n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            int L = query(1, 1, 100000, 1, left);
            int R = query(1, 1, 100000, 1, right);
            System.out.println(L + R);

            update(1, 1, 100000, left, -L);
            update(1, 1, 100000, left + 1, L);

            update(1, 1, 100000, right, -R);
            update(1, 1, 100000, right + 1, R);

            update(1, 1, 100000, left + 1, 1);
            update(1, 1, 100000, right, -1);

        }
    }
}
