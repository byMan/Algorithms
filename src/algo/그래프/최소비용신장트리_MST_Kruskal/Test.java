//package algo.그래프.MST_Kruskal;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.LinkedList;
//import java.util.PriorityQueue;
//import java.util.StringTokenizer;
//
//public class Test {
//    static int n, m, ans;
//    static LinkedList<NodeT>[] list;
//    static boolean[] visit;
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//        n = Integer.parseInt(br.readLine());
//        m = Integer.parseInt(br.readLine());
//
//        list = new LinkedList[n+1];
//        visit = new boolean[n+1];
//
//        for(int i=0; i<=n; i++){
//            list[i] = new LinkedList<>();
//        }
//
//        for(int i=0; i<m; i++){
//            st = new StringTokenizer(br.readLine());
//            int s = Integer.parseInt(st.nextToken());
//            int e = Integer.parseInt(st.nextToken());
//            int v = Integer.parseInt(st.nextToken());
//            list[s].add(new NodeT(e, v));
//            list[e].add(new NodeT(s, v));
//        }
//
//        visit[1] = true;
//
//        PriorityQueue<NodeT> queue = new PriorityQueue<>();
//        for(NodeT node : list[1]){
//            queue.add(new NodeT(node.s, node.v));
//        }
//
//        while (!queue.isEmpty()) {
//            NodeT node = queue.poll();
//            int curIdx = node.s;
//            int curV = node.v;
//            if(visit[curIdx]) continue;
//            visit[curIdx] = true;
//            ans += curV;
//
//            for(NodeT child : list[curIdx]){
//                if(!visit[child.s]){
//                    queue.add(new NodeT(child.s, child.v));
//                }
//            }
//        }
//
//        System.out.println(ans);
//    }
//}
////class NodeT implements Comparable<NodeT>{
////    int s;
////    int v;
////    public NodeT(int s, int v){
////        this.s = s;
////        this.v = v;
////    }
////    @Override
////    public int compareTo(NodeT o) {
////        return this.v - o.v;
////    }
////}