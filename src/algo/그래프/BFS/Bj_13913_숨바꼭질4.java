package algo.그래프.BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Bj_13913_숨바꼭질4 {
    static int n, k, min;
    static int[] visit = new int[100001];
    static int[] path = new int[100001];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;


        bfs();
    }

    private static void bfs(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        path[n] = n;
        visit[n] = 1;

        int next;
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if(cur == k){
                System.out.println(visit[cur] -1);

                Stack<Integer> stack = new Stack<>();
                while (path[cur] != cur){
                    stack.add(cur);
                    cur = path[cur];
                }
                stack.add(n);

                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty()) {
                    sb.append(stack.pop()).append(" ");
                }
                System.out.println(sb);
                return;
            }


            next = cur * 2;
            if(next <= 100000 && visit[next] == 0){
                SetValue(queue, next, cur);
            }

            next = cur + 1;
            if(next <= 100000 && visit[next] == 0){
                SetValue(queue, next, cur);
            }

            next = cur - 1;
            if(next >= 0 && visit[next] == 0){
                SetValue(queue, next, cur);
            }
        }
    }

    private static void SetValue(Queue<Integer> queue, int next, int cur) {
        visit[next] = visit[cur] + 1;
        path[next] = cur;
        queue.add(next);
    }
}
