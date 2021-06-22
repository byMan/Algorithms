package algo.유용한알고리즘모음;

import java.util.Arrays;

public class 중복없는조합구하기 {

    private static int N, tc;
    private static int[] numbers;

    public static void main(String[] args) throws Exception {
        int n = 4;
        int m = 4;

//        for(int i=0; (1 << n) > i; ++i){
//            System.out.print(i + " ");
//        }
//
//        System.out.println();
//
//        for(int i=0; i<(1 << n); i++){
//            if(Integer.bitCount(i) == m){
//                System.out.print(i + " ");
//            }
//        }
//
//        System.out.println(1 << 2);

        N = 4;
        numbers = new int[N];
        permutation(0,0);
//        bitmaskSunyol(n, m);

    }


    private static void permutation(int idx, int val){
        //원소 개수가 4이면
        if(idx == N){
            tc++;
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for(int i=1; i<=N; i++){
            //중복체크 결과값이 0이면 미방문이고, 1이면 방문한 것
            if((val & 1 << i) != 0)
                continue;
            
            //중복되지 않은 경우
            numbers[idx] = i;

            //flag | 1 << i 는 i 만큼 옮긴거랑 or을 해서 사용했으면 1 체크
            permutation(idx+1, val | 1 << i);   //다른 요소 뽑기
        }
    }


    private static void bitmaskSunyol(int n, int m) {
        int tot = 0;
        for(int i = 0; i < (1 << n); i++){
//            System.out.print("i:" + i + "\t1<<n = " + Integer.toBinaryString(1<<n) + "\ti = " + Integer.toBinaryString(i) + "\t\t");
            if(Integer.bitCount(i) == m){
                tot = 0;
                for(int j = 0; j< n; j++){
//                        System.out.println("j="+j + "\t(1<<j) = " + Integer.toBinaryString(1<<j) + "\ti="+Integer.toBinaryString(i) + "\tj&i=" + ((1<<j)&i));
                    if(((1 << j) & i) > 0) {
                        System.out.print(j + " ");
                        tot += j;
                    }
                }
                System.out.print(" \ttot:" + tot + "\n");
            }
        }
    }
}
