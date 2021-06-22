package algo.Pro원정대;

import java.util.Arrays;

public class MergeSort {
    static int[] vect = {1,2,6,8,2,4,5};
    static int[] result = new int[7];

    public static void main(String[] args) throws Exception {
        mergeSort(0,6);
        System.out.println(Arrays.toString(vect));
    }

    private static void mergeSort(int start, int end) {
        int mid = (start + end) / 2;

        if(start >= end) return;

        mergeSort(start, mid);
        mergeSort(mid+1, end);

        int a = start;  //a 영역은 0부터 3까지
        int b = mid+1;  //b 영역은 4부터 6까지

        int t = 0;

        while (true) {
            if(a > mid && b > end) break;

            if(a > mid){
                //a 영역이 끝났으면 b 영역 값을 등록
                result[t++] = vect[b++];
            }else if(b > end){
                //b 영역이 끝났으면 a 영역 값을 등록
                result[t++] = vect[a++];
            }else if(vect[a] <= vect[b]){
                //vect[a]와 vect[b] 비교해서 작은 것을 result에 넣기
                result[t++] = vect[a++];
            }else{
                result[t++] = vect[b++];
            }
        }

        //result에 있는 정렬된 배열을 vect에 적용시켜줌
        for(int i=0; i<t; i++){
            vect[start + i] = result[i];
        }
    }
}
