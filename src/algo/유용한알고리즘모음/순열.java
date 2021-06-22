package algo.유용한알고리즘모음;

public class 순열 {

    private static int n = 5;                           // 전체 개수
    private static int r = 3;                           // 찾을 개수
    private static int[] list = {1,2,3,4,5};            // 전체 리스트
    private static int[] answer = new int[r];           // 찾는 리스트
    private static boolean[] check = new boolean[n];    // check

    public static void main(String[] args) {
        순열(0);
    }

    private static void 순열(int depth) {
        if(r == depth) {
            print(answer);
            return;
        }

        for (int index = 0; index < n; index++) {
            if(!check[index]) {
                check[index] = true;
                answer[depth] = list[index];
                순열(depth + 1);
                check[index] = false;
            }
        }
    }

    private static void print(int[] answer) {
        for (int i :answer) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}