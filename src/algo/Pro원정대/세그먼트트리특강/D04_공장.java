package algo.Pro원정대.세그먼트트리특강;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D04_공장 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int arr[];
    static long tree[];

    static void update(int node, int start, int end, int index, int value)
    {
        if(index < start || end < index)
            return;
        if(start== end)
        {
            tree[node] = value;
            return;
        }
        int mid = (start + end) / 2;
        update(node*2, start, mid, index, value);
        update(node*2 + 1, mid + 1, end, index, value);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static long query(int node, int start, int end, int left, int right)
    {
        if(right < start || end < left)
            return 0;
        if(left <= start && end <= right)
            return tree[node];
        int mid = (start + end) / 2;
        long m1 = query(node * 2, start, mid, left, right);
        long m2 = query(node * 2 + 1, mid + 1, end, left, right);
        return m1 + m2;
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int a[] = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
        {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int b[] = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
        {
            b[i] = Integer.parseInt(st.nextToken());
        }

        int index[] = new int[1000001];
        tree = new long[n * 5];
        // DAT, index : 기계 번호, value : 해당 기계가 연결될 위치
        for(int i = 0; i < n; i++)
        {
            index[ b[i] ] = i;
        }
        long ans = 0;
        for(int i = 0; i < n; i++)
        {
            ans += query(1, 0, n - 1, index[ a[i] ], n - 1);
            update(1, 0, n-1, index[a[i]], 1);
        }
        System.out.println(ans);
    }
}