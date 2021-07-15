package algo.분할정복;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_10090_CountingInversions {
    static int N;
    static long ANS;
    static int[] arr, temp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        temp = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        mergeSort(0, N);

        System.out.println(ANS);
    }

    private static void mergeSort(int start, int end) {
        int mid = (start + end) / 2;
        if(start < mid){
            mergeSort(start, mid);
            mergeSort(mid, end);
            merge(start, mid, end);
        }
    }

    private static void merge(int start, int mid, int end) {
        int p1, p2, index = 0;
        p1 = start;
        p2 = mid;

        while(p1 < mid && p2 < end){
            if(arr[p1] < arr[p2]){
                temp[index++] = arr[p1++];
            }else if(arr[p1] > arr[p2]){
                ANS += mid - p1;
                temp[index++] = arr[p2++];
            }else{
                temp[index++] = arr[p2++];
            }
        }

        while(p1 < mid){
            temp[index++] = arr[p1++];
        }

        while(p2 < end){
            temp[index++] = arr[p2++];
        }

        for(int i=0; i<end - start; i++){
            arr[start + i] = temp[i];
        }
    }
}
