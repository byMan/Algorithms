package algo.분할정복;

import java.util.Arrays;

public class 병합정렬NlogN보장알고리즘 {
    static int number = 8;
    static int[] sorted = new int[8];
    static int[] a;

    public static void main(String[] args) throws Exception {
        a = new int[]{7, 6, 5, 8, 3, 5, 9, 1};

        mergeSort(0, number-1);

        System.out.println(Arrays.toString(a));
    }

    private static void merge(int start, int middle, int end){
        int i = start;
        int j = middle + 1;
        int k = start;

        //작은 순서대로 배열에 삽입
        while(i <= middle && j<= end){
            if(a[i] <= a[j]){
                sorted[k] = a[i];
                i++;
            } else {
                sorted[k] = a[j];
                j++;
            }
            k++;
        }

        //남은 데이터도 삽입
        if(i > middle){
            for(int t=j; t<=end; t++){
                sorted[k] = a[t];
                k++;
            }
        } else {
            for(int t=i; t<=middle; t++){
                sorted[k] = a[t];
                k++;
            }
        }

        //정렬된 배열을 삽입
        for(int t=start; t<=end; t++){
            a[t] = sorted[t];
        }
    }


    private static void mergeSort(int start, int end){
        if(start < end){
            int middle = (start + end) / 2;
            mergeSort(start, middle);
            mergeSort(middle+1, end);
            merge(start, middle, end);
        }
    }
}
