package algo.유용한알고리즘모음;

public class 중복순열 {

    private static int n = 5;
    private static int r = 3;
    private static int[] list = {1,2,3,4,5};
    private static int[] answer = new int[r];

    public static void main(String[] args) {
        중복순열(0);
    }

    private static void 중복순열(int depth) {
        if(depth == r) {
            print(answer);
            return;
        }

        for (int index = 0; index < n; index++) {
            answer[depth] = list[index];
            중복순열(depth + 1);
        }
    }


    private static void print(int[] answer) {
        for (int i :answer) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}