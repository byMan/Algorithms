import java.util.Arrays;

public class Test2 {

    private static long[][] memo;
    private static int[] arr = { 4, 7, -2, -8, 3 }, arrCopy, temp;
    private static int N, M;

    public static void main(String[] args) {
        N = arr.length;
        M = 4;              //N개중 M개 중복조합

        arrCopy = new int[N];
        temp = new int[M];
        memo = new long[1001][1001];
        comb(0, 0);
    }

    private static void comb(int index, int depth) {

        if (depth == 4) {
            System.out.println(Arrays.toString(temp));
            return;
        }

        if (index == arr.length) {
            return;
        }

        temp[depth] = arr[index];
        // index를 안늘려주면 다음 depth에서도 계속 같은 index 가르킨다
        comb(index, depth + 1);
        // 대신 출력된 이후에는 값이 바껴야 되므로 index 추가
        comb(index + 1, depth);
    }


    private static long memoization(int n, int r){
        if(memo[n][r] > 0){
            return memo[n][r];
        }

        if(r == 0 || n == r){
            return memo[n][r] = 1;
        }

        return memo[n][r] = memoization(n-1, r-1) + memoization(n-1, r);
    }
}