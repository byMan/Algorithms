package 기출;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class MinMax {
    static int t, n, diff, minLen, minLenCnt;
    static int[] arr, minTree, maxTree;
    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("F:\\Temp\\pro_20210326_in.txt"));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        for(int k=1; k<=t; k++){
            long start, end;
            start = System.nanoTime();

            minLen = Integer.MAX_VALUE;
            minLenCnt = 0;

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            diff = Integer.parseInt(st.nextToken());

            arr = new int[n];
            String[] strArr = br.readLine().split(" ");
            for(int i=0; i<n; i++){
                arr[i] = Integer.parseInt(strArr[i]);
            }

            minTree = new int[n*4];
            maxTree = new int[n*4];
            minTreeInit(0, n-1, 1);
            maxTreeInit(0, n-1, 1);

            solve();

            if(minLen == Integer.MAX_VALUE){
                System.out.println("#" + k + " -1");
            }else{
                System.out.println("#" + k + " " + minLen + " " + minLenCnt);
            }
            end = System.nanoTime();
            System.out.println((end-start)/1000000000.0 + "초");
        }
    }

    private static void solve() {
//        System.out.println("n = " + n);
        int start = 0, end = 1, len = 2;
        while(true){
            if(start == 0 && end == n-1){
                break;
            }

            int min = getMin(0, n-1, start, end, 1);
            int max = getMax(0, n-1, start, end, 1);

            if(max - min == diff && minLen >= (end-start+1)){
//                System.out.println("여기 안오냐?");
                minLen = end - start + 1;
                minLenCnt++;
            }

//            System.out.println("start="+start + "\tend="+end + "\tmax:"+max + "\tmin:" + min + "\tmax - min = " + (max - min) + "\t(end-start+1) = " + (end-start+1));

            if(end == n-1){
                start = 0;
                end = len++;
                continue;
            }

            if(start > 0 && end == n-1){
                start = 0;
                end = len++;
            }else{
                start++;
                end++;
            }
        }
    }

    private static int minTreeInit(int start, int end, int node){
        if(start == end){
            return minTree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        return minTree[node] = Math.min(minTreeInit(start, mid, node*2), minTreeInit(mid+1, end, node*2+1));
    }

    private static int maxTreeInit(int start, int end, int node){
        if(start == end){
            return maxTree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        return maxTree[node] = Math.max(maxTreeInit(start, mid, node*2), maxTreeInit(mid+1, end, node*2+1));
    }

    private static int getMin(int start, int end, int left, int right, int node){
        if(right < start || left > end){
            return Integer.MAX_VALUE;
        }

        if(start >= left && end <= right){
            return minTree[node];
        }

        int mid = (start + end) / 2;
        return Math.min(getMin(start, mid, left, right, node*2), getMin(mid+1, end, left, right, node*2+1));
    }

    private static int getMax(int start, int end, int left, int right, int node){
        if(right < start || left > end){
            return Integer.MIN_VALUE;
        }

        if(start >= left && end <= right){
            return maxTree[node];
        }

        int mid = (start + end) / 2;
        return Math.max(getMax(start, mid, left, right, node*2), getMax(mid+1, end, left, right, node*2+1));
    }

}

/*
[입력]
첫째줄 테스트 케이스
둘째줄 첫번째는 입력 수의 갯수
둘째줄 두번째는 구간의 최대값에서 최소값을 뺀 차이 값

3
10 5
1 2 3 4 5 6 7 8 9 11
2 100
20 30
9 15
50 55 60 65 90 120 110 109 105

[출력]
테스트번호를 출력하고 한칸 공백 입력 후 최소 길이 한칸 공백 후 동일 구간 갯수

#1 5 1
#2 -1
#3 4 2
 */
