import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int arr[];
    static int tree[];
    static void init(int node, int start, int end)
    {
        if(start == end)
        {
            tree[node] = arr[start];
            return;
        }
        init(node * 2, start, (start + end) / 2);
        init(node * 2 + 1, (start+ end) / 2 + 1, end);
        tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
    }

    static int query(int node, int start, int end, int left, int right)
    {
        if(right < start || end < left) // 원하는 구간을 벗어나는 구간값을 갖는 경우
            return Integer.MAX_VALUE; // 계산에 영향을 끼치지 않는 값
        if(left <= start && end <= right)
            return tree[node];
        int m1 = query(node * 2, start, (start + end) / 2, left, right);
        int m2 = query(node * 2 + 1, (start+end)/2+1, end, left, right);
        return Math.min(m1, m2);
    }

    static void update(int node, int start, int end, int index, int value)
    {
        if(index < start || end < index)
            return;
        if(start == end)
        {
            tree[node] = value;
            return;
        }
        update(node * 2, start, (start + end) / 2, index, value);
        update(node * 2 + 1, (start + end) / 2 + 1, end, index, value);
        tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
    }

    public static void main(String[] args) throws IOException {
        int n;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        tree = new int[n * 5];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());
        init(1, 0, n - 1);
        for(int i = 0 ; i < m; i++)
        {
            int type, a , b;
            st = new StringTokenizer(br.readLine());
            type = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if(type == 1)
            {
                arr[a - 1] = b;
                update(1, 0, n - 1, a - 1, b);
            }
            else if(type == 2)
                System.out.println(query(1, 0, n - 1, a - 1, b - 1));
        }
    }
}