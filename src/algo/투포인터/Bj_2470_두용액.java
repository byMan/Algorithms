package algo.투포인터;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_2470_두용액 {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        /*
            투 포인터를 활용하여 푸는데 핵심은 배열을 정렬하고 이후 배열 시작위치와 끝 위치에 포인터를 각각 두고
            두 포인터가 같아지는 순간 종료하도록 처리한다.
         */
        Arrays.sort(arr);

        int left=0, right=N-1;
        int sum=Integer.MAX_VALUE, val1=0, val2=0;

        while(left != right){
            int tot = arr[left] + arr[right];
            if(sum > Math.abs(tot)){
                sum = Math.abs(tot);
                val1 = arr[left];
                val2 = arr[right];
            }

            int a = arr[left+1] + arr[right];
            int b = arr[left] + arr[right-1];
            if(Math.abs(a) < Math.abs(b)){
                left++;
            }else{
                right--;
            }
        }

        System.out.println(val1 + " " + val2);
    }
}

/*
입력
5
-2 4 -99 -1 98

출력
-99 98


 */