package algo.그래프.다익스트라;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Bj_5719_거의최단경로 {
    static int N, M;
    static int[] dist;
    static List<Pair>[] v;
    static boolean[][] check;
    static List<Integer>[] parent;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {

            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);

            if (N == 0 && M == 0)
                break;

            dist = new int[N];
            v = new List[N];
            check = new boolean[N][N];
            //parent라는 ArrayList 리스트 배열을 만들어서 현재 노드의 직전 노드를 저장하는 공간을 만들어주었다.
            //현재 노드에 최소 경로로 도달할 수 있는 직전 노드가 여러 개 있으면, ArrayList에 여러 개를 넣는다.
            parent = new ArrayList[N];

            for (int i = 0; i < N; i++) {
                v[i] = new ArrayList<>();
                parent[i] = new ArrayList<>();
            }

            input = br.readLine().split(" ");
            int S = Integer.parseInt(input[0]);
            int D = Integer.parseInt(input[1]);

            for (int i = 0; i < M; i++) {
                input = br.readLine().split(" ");
                int U = Integer.parseInt(input[0]);
                int V = Integer.parseInt(input[1]);
                int P = Integer.parseInt(input[2]);

                v[U].add(new Pair(V, P));

            }

            //우선 첫 번째 다익스트라를 통해 각각의 노드에 대해 최소 경로가 되는 직전 노드를 찾아서 parent[n]에 넣어준다.
            dijkstra(S, D);     // 첫번째 다익스트라
            print("parent[]", parent);
//            print("dist[]", dist);
//            print("check[]", check);

            backTracking(S, D); // 백트래킹
//            print("check[]", check);

            //check[u][v] = false인 경로로만 최소 경로를 찾아야 하므로, 이 때 구한 최소 경로는 문제에서 요구한 "거의 최단 경로"가 된다.
            dijkstra(S, D);     // 두번째 다익스트라
//            print("dist[]", dist);
//            print("check[]", check);

            if (dist[D] == Integer.MAX_VALUE)
                bw.write(-1 + "\n");
            else
                bw.write(dist[D] + "\n");
        }

        bw.flush();

    }

    /**
     * parent[n]를 통해 D에서부터 시작하여 최소 경로를 백트래킹한다.
     * 최소 경로에 해당하는 간선은 "거의 최단 경로"를 구할 때 사용이 불가능하다. 따라서, 백트래킹을 하면서 최소 경로에 해당하는 간선을 체크해야 한다.
     * 예를 들어, 위의 예제에서는 0과 1사이의 간선, 1과 5사이의 간선, 5와 6사이의 간선, 0과 3사이의 간선, 3과 6사이의 간선이 최소 경로에 해당한다.
     * 따라서 check[0][1] = true, check[1][5] = true, check[5][6] = true, check[0][3] = true, check[3][6] = true 로 해준다.
     * check[u][v] = true가 된 간선은, 다음 두 번째 다익스트라에서 최소 경로를 찾는 과정에서 사용할 수 없다.
     * @param S
     * @param node
     */
    public static void backTracking(int S, int node) {
        if (node == S)
            return;

        for (int n : parent[node]) {
            if (!check[n][node]) {
                check[n][node] = true;
                backTracking(S, n);
            }
        }
    }

    public static void dijkstra(int S, int D) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(S, 0));
        dist[S] = 0;

        while (!pq.isEmpty()) {
            Pair pair = pq.poll();
            int cur = pair.n;
            for (Pair np : v[cur]) {
                int next = np.n;
                int nextW = dist[cur] + np.w;
                if (!check[cur][next]) {
                    if (dist[next] == nextW) {
                        parent[next].add(cur);
                    } else if (dist[next] > nextW) {
                        dist[next] = nextW;
                        parent[next].clear();
                        pq.offer(new Pair(next, nextW));
                        parent[next].add(cur);
                    }
                }
            }
        }
    }

    static class Pair implements Comparable<Pair> {
        int n, w;
        Pair(int n, int w) {
            this.n = n;
            this.w = w;
        }

        @Override
        public int compareTo(Pair o) {
            return w - o.w;
        }
    }

    private static void print(String name, int[] arr){
        System.out.println();
        System.out.println(name);
        System.out.println(Arrays.toString(arr));
    }

    private static void print(String name, boolean[][] arr){
        System.out.println();
        System.out.println(name);
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private static void print(String name, List<Integer>[] parent) {
        Arrays.stream(parent).forEach(o -> System.out.println("o = " + o));
    }
}

/*

[솔루션]

1. 다익스트라 -> 최단 경로 제거 -> 다익스트라 형태로 진행하면 된다.
    1) 첫 번째 다익스트라 알고리즘으로 최단 경로를 찾는다.
    2) 찾은 최단 경로를 제거한다.
    3) 두 번째 다익스트라 알고리즘으로 최단 경로를 찾는다.
        3-1) 이는 최단 경로를 제거한 이후의 최단 경로를 찾는 것이므로 우리가 원하는 거의 최단 경로 (최단 경로에 속하지 않는 경로를 통해 도착지점으로 가는 경로) 인 겻이다.
    4) 최단 경로를 구할 때에는 경로를 역추적 (도착 지점에서부터 시작) 하는 방식으로 구한다.
    5) 또한, 첫 번째 다익스트라 알고리즘을 수행하고 난 뒤에 리스트에 담기는 노드들이 항상 최단 경로를 이루지는 않는다.
        5-1) 따라서 최단 경로를 제거하는 부분에서 최단 경로인지 확인해줘야 한다.(비용이 최단 경로와 동일한지)


 */