package algo.부분수열;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 최대부분수열합_210806_기출 {
    static int INF = (int)-21E8;
    static int N, M;
    static int[] arr;
    static long[] order, reverseOrder;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("D:\\Workspace\\IntelliJ_Workspace\\Algorithms\\src\\algo\\부분수열\\최대부분수열합_testCase.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for(int k=1; k<=T; k++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new int[N];
            order = new long[N];
            reverseOrder = new long[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            setOrderArr();
            setReverseOrderArr();

            long sum = 0;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int leftIdx = Integer.parseInt(st.nextToken());
                int rightIdx = Integer.parseInt(st.nextToken());

                long leftMax = 0, rightMax = 0;
                if(leftIdx - 2 < 0){
                    leftMax = INF;
                }else{
//                    System.out.println("leftIdx : " + String.valueOf(leftIdx-2));
                    leftMax = order[leftIdx-2];
                }

                if(rightIdx >= N){
                    rightMax = INF;
                }else{
//                    System.out.println("rightIdx : " + String.valueOf(rightIdx));
                    rightMax = reverseOrder[rightIdx];
                }

                long ret = Math.max(leftMax, rightMax);

//                System.out.println(Arrays.toString(order));
//                System.out.println(Arrays.toString(reverseOrder));
//                System.out.println(i + " " + ret + "\tleftMax : " + leftMax + "\trightMax : " + rightMax);

                sum += ret == INF ? 0 : ret;
            }

            System.out.println("#" + k + " " + sum);
        }
    }

    private static void setOrderArr() {
        long localMax = INF;
        long globalMax = INF;
        for (int i = 0; i < N; i++) {
            localMax = Math.max(localMax + arr[i], arr[i]);
            globalMax = Math.max(globalMax, localMax);
            order[i] = globalMax;
        }
    }

    private static void setReverseOrderArr() {
        long localMax = INF;
        long globalMax = INF;
        for (int i = N-1; i >= 0; i--) {
            localMax = Math.max(localMax + arr[i], arr[i]);
            globalMax = Math.max(globalMax, localMax);
            reverseOrder[i] = globalMax;
        }
    }
}
