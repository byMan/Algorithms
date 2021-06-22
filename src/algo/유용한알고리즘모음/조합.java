package algo.유용한알고리즘모음;

public class 조합 {

    private static int n = 5;
    private static int r = 3;
    private static int[] list = {1,2,3,4,5};
    private static int[] answer = new int[r];

    public static void main(String[] args) {
        조합(0,0);
    }

    private static void 조합(int index, int depth) {

        if(depth == r) {
            print(answer);
            return;
        }

        if(index == n) {
            return;
        }

        answer[depth] = list[index];
        조합(index + 1, depth + 1);
        조합(index + 1, depth);
    }

    private static void print(int[] answer) {
        for (int i :answer) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
