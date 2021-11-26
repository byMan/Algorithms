package algo.유용한알고리즘모음;

import java.util.Arrays;

public class LowerBoundUpperBound {
    public static void main(String[] args) {
        int[] arr = {1, 3, 11, 7, 25, 4, 2, 10, 32};

        Arrays.sort(arr);

        System.out.println(Arrays.toString(arr));

        int lower = lowerBound(arr, 7);
        System.out.println("lower = " + lower + "\tarr[lower] = " + arr[lower]);

        int upper = upperBound(arr, 10);
        System.out.println("upper = " + upper + "\tarr[upper] = " + arr[upper]);
    }

    private static int lowerBound(int[] arr, int target) {
        int start = 0;
        int end = arr.length;

        while (start < end) {
            int mid = (start + end) / 2;

            if (arr[mid] >= target) {
                end = mid;
            }else{
                start = mid + 1;
            }
        }

        return end;
    }


    private static int upperBound(int[] arr, int target) {
        int start = 0;
        int end = arr.length;

        while (start < end) {
            int mid = (start + end) / 2;

            if (arr[mid] > target) {
                end = mid;
            }else{
                start = mid + 1;
            }
        }

        return end;
    }
}
