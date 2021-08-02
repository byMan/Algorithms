package algo.그래프.BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Bj_1707_이분그래프 {
    static String ans;
    static int K, V, E;
    static int RED = 1, BLUE = 2;
    static int[] visit;
    static List<Integer>[] list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        K = Integer.parseInt(br.readLine());

        for(int t=0; t<K; t++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            list = new List[V+1];
            visit = new int[V+1];

            for (int i = 0; i <= V; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list[a].add(b);
                list[b].add(a);
            }

            ans = "YES";
            //연결그래프와 비연결그래프인 경우 모두 고려하기 위해 bfs를 반복으로 돌려줌.
            for(int i=1; i<=V; i++){
                if(visit[i] == 0)
                    bfs(i);
                if("NO".equals(ans))
                    break;
            }

            System.out.println(ans);
        }
    }


    private static void bfs(int start){
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{start, RED});
        visit[start] = RED;

        while(!q.isEmpty()){
            Integer[] now = q.poll();
            int num = now[0];
            int color = now[1];
            int nextColor = color == RED ? BLUE : RED;

            for(int next : list[num]){
                //현재 색깔과 다음 색깔이 같으면 실패
                if(visit[next] == color){
                    ans = "NO";
                    return;
                }

                if(visit[next] == 0){
                    visit[next] = nextColor;
                    q.add(new Integer[]{next, nextColor});
                }
            }
        }
    }
}
