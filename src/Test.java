import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test {
    private static long[] arr = new long[1000001];
    private static long[] tree = new long[1000001];
    private static int N, M, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for(int i=1; i<=N; i++){
            int val = Integer.parseInt(br.readLine());
            arr[i] = val;
            update(i, val);
        }

        int count = 0;
        while(count++ < M + K){
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            if(op == 1){
                int index = Integer.parseInt(st.nextToken());
                long val = Long.parseLong(st.nextToken());
                update(index, val - arr[index]);
                arr[index] = val;
            }else{
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                System.out.println(intervalSum(start, end));
            }
        }
    }


    private static void update(int i, long dif){
        while(i <= N){
            tree[i] += dif;
            i += (i & -i);
        }
    }


    private static long intervalSum(int start, int end){
        return prefixSum(end) - prefixSum(start -1);
    }


    private static long prefixSum(int i){
        long result = 0;
        while(i > 0){
            result += tree[i];
            i -= (i & -i);
        }
        return result;
    }
}