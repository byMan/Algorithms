import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Bj_11266_단절점 {
    private static int V;
    private static int E;
    private static List<Integer>[] input;
    private static boolean[] visit;
    private static int[] visitOrder;
    private static int order;
    private static boolean[] cut;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream(new File("단절점찾기")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        visit = new boolean[V+1];
        visitOrder = new int[V+1];
        cut = new boolean[V+1];

        //무방향이므로 인접리스트 처리를 위해 초기화
        input = new List[V+1];
        for(int i=1; i<=V; i++) {
            input[i] = new ArrayList<Integer>();
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            input[a].add(b);
            input[b].add(a);
        }

        //문제에서 주어진 그래프가 연결 그래프가 아닐 수 있기 떄문에 모든 노드에 대해서 탐색
        for(int i=1; i<=V; i++) {
            if(!visit[i]) {
                dfs(i, true);
            }
        }

        int count = 0;
        for(int i=0; i<cut.length; i++) {
            if(cut[i]) {
                count++;
            }
        }

        bw.append(String.valueOf(count)).append("\n");

        for(int i=0; i<cut.length; i++) {
            if(cut[i]) {
                bw.append(String.valueOf(i)).append(" ");
            }
        }

        bw.close();
    }

    private static int dfs(int curNode, boolean isRoot) {
        visitOrder[curNode] = ++order;  //현재 노드 방문 순서
        visit[curNode] = true;
        int child = 0;

        int result = visitOrder[curNode];

        //자식노드 탐색
        for(int next : input[curNode]) {
            //자식 노드 미방문인 경우
            if(!visit[next]) {
                //미방문 자식 수를 센다. 최초 시작 노드가 단절점인지 아닌지 파악하기 위해서...
                child++;

                //자식 노드의 방문 순서중 가장 작은 값
                int low = dfs(next, false);

                //루트 노드가 아니고 현재 노드의 자식 노드들의 순번 값이 현재 노드의 순번보다 값이 크다면 현재 노드는 단절점이다.
                //현재 노드가 단절점이 아니라면 현재노드의 순번보다 자식노드를 통해 전달반은 순번 값이 더 작은 값이 되므로 이 경우는 단절점이 아니다.
                if(!isRoot && low >= visitOrder[curNode]) {
                    cut[curNode] = true;
                }

                //현재 노드와 현재 노드의 자식 노드 중 최소 값을 result에 저장한다.
                result = Math.min(result, low);

            }else {
                //이미 방문한 자식인 경우, 그 자식과 현재 노드의 방문 순서중 작은 값을 선택 (우회 확인)
                result = Math.min(result, visitOrder[next]);
            }
        }

        //루트 노드인 경우 자식이 2개 이상일 때 무조건 단절점
        if(isRoot && child > 1) {
            cut[curNode] = true;
        }

        return result;
    }
}