package 기출;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 이사 {
    public static boolean[] visit;
    public static int N, M, R, K, ANS;
    public static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visit = new boolean[N+1];

        for(int i=0; i<=N; i++){
            arr.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr.get(a).add(b);
            arr.get(b).add(a);
        }

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());   //직장이 있는 지역
        K = Integer.parseInt(st.nextToken());   //환승 횟수

        bfs();

        System.out.println(ANS);
    }


    private static void bfs(){
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{R, 0});

        while(!q.isEmpty()){
            Integer[] numArr = q.poll();
            int num = numArr[0];
            int cnt = numArr[1];

            if(cnt > K) continue;

            if(!visit[num]){
                ANS++;
                visit[num] = true;
            }

            for(Integer childNum : arr.get(num)){
                q.add(new Integer[]{childNum, cnt + 1});
            }
        }
    }
}
