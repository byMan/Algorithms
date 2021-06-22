package 기출;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 디케의저울 {
    static boolean[][] isPossible;
    static int[] weight;
    static int n, ans;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        weight = new int[n];
        isPossible = new boolean[n+1][200001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            weight[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0,0);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++){
            int bead = Integer.parseInt(st.nextToken());

            if(isPossible[n][bead]){
                ans++;
            }
        }

        System.out.println(ans);
    }

    private static void dfs(int cnt, int w){
        if(isPossible[cnt][w]){
            return;
        }

        isPossible[cnt][w] = true;

        if(cnt == n){
            return;
        }

        //한쪽에 추가로 놓은 경우
        dfs(cnt+1, w+weight[cnt]);
        //아무쪽에도 놓지 않은 경우
        dfs(cnt+1, w);
        //반대쪽에 놓은 경우
        dfs(cnt+1, Math.abs(w-weight[cnt]));
    }
}


/*
[문제]
N개의 추가 있다.
만약, 7,3,5,1의 추를 보유하고 있다면,
7, 5, 3 kg 추를 사용하여 15kg 무게의 측정도 가능하고,
한쪽에는 1, 3, 5 kg을 올려두고, 한쪽에는 7 kg 추를 올려두어 2kg 무게도 측정할 수 있다.

[예시]
만약 N이 4이고, 1, 3, 5, 7 추를 보유하고 있습니다.
그리고 Q = 5 이고, 1, 2, 3, 17, 30 을 입력받는다면
1 2 3 은 측정 가능하며, 17과 30은 측정이 불가합니다.
따라서 3개의 Query에 대한 무게만 측정이 가능하므로 정답은 3입니다.


[입력]
첫줄에 추의 수 N을 입력받습니다. ( 1 <= N <= 20 )
다음줄에 N개의 추의 무게를 입력 받습니다.
같은 무게의 추가 있을 수 있으며, 추의 무게는 1kg ~ 10,000kg 까지 존재합니다.

다음 줄에는 Query의 개수 Q를 입력 받습니다. ( 1<= Q <= 100,000 )
그 다음줄에는 무게에 대한 질의를 Q개 입력 받습니다.

4
1 3 5 7
5
1 2 3 17 30


[출력]
3


 */
