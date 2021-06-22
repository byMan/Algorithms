package algo.유용한알고리즘모음;

public class 중복조합 {

    private static int n = 5;
    private static int r = 3;
    private static int[] list = {1,2,3,4,5};
    private static int[] answer = new int[r];

    public static void main(String[] args) {
        중복조합(0,0);
    }

    private static void 중복조합(int index, int depth) {

        if(depth == r) {
            print(answer);
            return;
        }

        if(index == n) {
            return;
        }

        answer[depth] = list[index];
        // index를 안늘려주면 다음 depth에서도 계속 같은 index 가르킨다
        중복조합(index, depth + 1);
        // 대신 출력된 이후에는 값이 바껴야 되므로 index 추가열
        중복조합(index + 1, depth);
    }

    private static void print(int[] answer) {
        for (int i :answer) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
