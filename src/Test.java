import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test {
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ret = twoPoint1();

        System.out.println(ret);
    }

    private static int twoPoint() {
        int cnt = 0, sum = 0, start = 0, end = 0;
        while (true) {
            if(sum >= M) {
                sum -= arr[start++];
            }else if(end == N){
                break;
            }else{
                sum += arr[end++];
            }
            if(sum == M) cnt++;
        }
        return cnt;
    }


    private static int twoPoint1(){
        int start=0, end=0, sum=0, cnt=0;
        while(start < N){
            while(sum < M && end < N){
                sum += arr[end++];
            }
            if(sum == M) cnt++;
            sum -= arr[start++];
        }
        return cnt;
    }
}