package algo.Pro원정대.세그먼트트리특강;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class D09_달리기 {
    static class Pair implements Comparable<Pair>{
        int first;
        int second;
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
        @Override
        public int compareTo(Pair right) {
            if(first < right.first) return -1;
            if(first > right.first) return 1;
            return 0;
        }
    }
    static int tree[];
    static void update(int node, int st, int en, int index)
    // node위치에는 st ~ en능력치인 사람의 수
    // update : index 능력치인 사람을 1명 추가(기록)
    {
        if (index < st || en < index)
            return;
        if (st == en)
        {
            tree[node] = 1;
            return;
        }
        update(node * 2, st, (st + en) / 2, index);
        update(node * 2 + 1, (st + en) / 2 + 1, en, index);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static int query(int node, int st, int en, int s, int e)
    {
        if (e < st || en < s)
            return 0;
        if (s <= st && en <= e)
            return tree[node];
        int m1 = query(node * 2, st, (st + en) / 2, s, e);
        int m2 = query(node * 2 + 1, (st + en) / 2 + 1, en, s, e);
        return m1 + m2;
    }
    static Pair p[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
        int n = Integer.parseInt(br.readLine());
        p = new Pair[n];
        tree = new int[n * 5];
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
        for(int i = 0; i < n; i++)
        {
            int a = Integer.parseInt(br.readLine());
            pq.add(new Pair(a, i));
        }
        int cnt = 1;
        while(!pq.isEmpty())
        {
            Pair now = pq.poll();
            p[now.second] = new Pair(cnt++, now.second);
        }
        for (int i = 0; i < n; i++)
        {
            System.out.println(p[i].second - query(1, 1, n, 1, p[i].first) + 1);
            update(1,1,n, p[i].first);
        }
    }
}
