package algo.유용한알고리즘모음;

import java.util.Arrays;

public class 자바_조합_순열_중복조합_중복순열 {

    private static int N = 3;   //뽑고자 하는 개수
    private static int[] arr = {1, 2, 3, 4, 5};
    private static boolean[] visited = new boolean[arr.length];
    private static int[] temp;
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());

        temp = new int[N];

        //조합
        System.out.println("조합 출력");
        makeCombination(N, temp, 0, 0);

        //중복 조합
        System.out.println("\n\n중복 조합 출력");
        makeOverlabCombination(N, temp, 0, 0);

        //순열
        System.out.println("\n\n순열 출력");
        makePermutation(N, temp, 0, visited);

        //순열 비트마스크
        System.out.println("\n\n순열 비트마스크");
        bitmaskPermutation(N, temp, 0, 0);

        //중복 순열
        System.out.println("\n\n중복 순열 출력");
        makeOverlabPermutation(N, temp, 0);

        //중복조합 백트래킹
        System.out.println("\n\n중복 조합 출력");
        중복조합(0, 0);

        //메모이제이션 중복조합
        dp = new int[11][11];
        combination(10, 4);
    }


    /**
     * [조합]
     * 1,2,3,4,5 중에서 3개를 뽑는 것이다.  1,2,3과 1,3,2 가 똑같은 것으로 치는 것이다. 1,1,2는 조합으로 안친다.
     * -> 총 개수 5개에서 3개를 뽑는 것이다.(순서는 상관없음.)
     * <p>
     * 코드 설명 : 조합을 재귀로 구현을 하였다.
     * current 값을 늘려주면서 r값에 도달하면 temp를 출력하게 했다.
     * start 값이 i + 1 인 이유는 나왔던 값이 또다시 반복되면 안 되기 때문.
     * (예를 들어 1이 배열에 들어갔는데 1이 또 배열에 들어가게 되면 조합이 성립이 안되기 때문.)
     *
     * @param r       : 뽑고자 하는 개수
     * @param temp    : r개를 뽑는 결과값을 저장해 놓는 배열
     * @param current : 현재 개수를 저장해 놓는 값
     * @param start   : 그 다음 반복문을 시작하는 값
     */
    private static void makeCombination(int r, int[] temp, int current, int start) {
        if (r == current) {
            System.out.println(Arrays.toString(temp));
        } else {
            for (int i = start; i < arr.length; i++) {
                temp[current] = arr[i];
                makeCombination(r, temp, current + 1, i + 1);
            }
        }
    }
/*
조합 출력
[1, 2, 3]
[1, 2, 4]
[1, 2, 5]
[1, 3, 4]
[1, 3, 5]
[1, 4, 5]
[2, 3, 4]
[2, 3, 5]
[2, 4, 5]
[3, 4, 5]
 */


    /**
     * [중복 조합]
     * <p>
     * 1,2,3,4,5 중에서 중복을 포함해서 3개를 뽑는 것이다. 조합에서 1,1,2 같은 중복되는 것도 중복 조합으로 친다.
     * -> 총 개수 5개에서 중복을 포함해서 3개를 뽑는 것이다.
     * <p>
     * 코드 설명 : 재귀로 구현하였다.
     * current 값을 늘려주면서 r값에 도달하면 temp를 출력하게 했다.
     * start 값이 i 인 이유는 나왔던 값이 또 나와도 되기 때문.
     *
     * @param r       : 뽑고자 하는 개수
     * @param temp    : r개를 뽑는 결과값을 저장해 놓는 배열
     * @param current : 현재 개수를 저장해 놓는 값
     * @param start   : 그 다음 반복문을 시작하는 값
     */
    private static void makeOverlabCombination(int r, int[] temp, int current, int start) {
        if (r == current) {
            System.out.println(Arrays.toString(temp));
        } else {
            for (int i = start; i < arr.length; i++) {
                temp[current] = arr[i];
                makeOverlabCombination(r, temp, current + 1, i);
            }
        }
    }
/*

중복 조합 출력
[1, 1, 1]
[1, 1, 2]
[1, 1, 3]
[1, 1, 4]
[1, 1, 5]
[1, 2, 2]
[1, 2, 3]
[1, 2, 4]
[1, 2, 5]
[1, 3, 3]
[1, 3, 4]
[1, 3, 5]
[1, 4, 4]
[1, 4, 5]
[1, 5, 5]
[2, 2, 2]
[2, 2, 3]
[2, 2, 4]
[2, 2, 5]
[2, 3, 3]
[2, 3, 4]
[2, 3, 5]
[2, 4, 4]
[2, 4, 5]
[2, 5, 5]
[3, 3, 3]
[3, 3, 4]
[3, 3, 5]
[3, 4, 4]
[3, 4, 5]
[3, 5, 5]
[4, 4, 4]
[4, 4, 5]
[4, 5, 5]
[5, 5, 5]

 */


    /**
     * [순열]
     * <p>
     * 1,2,3,4,5 중에서 순서를 생각해서 3개를 뽑는 것이다. 1,2,3과 1,3,2를 다른 것으로 친다.
     * -> 총 개수 5개에서 3개를 순서를 고려해서 뽑는 것이다. ( 3개를 뽑아서 순서를 다르게 정렬하는 것으로도 볼 수 있다.)
     * <p>
     * 코드 설명 : 재귀로 구현하였다.
     * 방문 여부를 구하는 visited로 방문 여부를 체크해서 방문했다면 배열에 넣지 않고 방문하지 않았다면 배열에 넣어서 순열을 구현했다.
     *
     * @param r       : 뽑고자 하는 개수
     * @param temp    : r개를 뽑는 결과값을 저장해 놓는 배열
     * @param current : 현재 개수를 저장해 놓는 값
     * @param visited : 방문 여부를 확인하는 배열
     */
    private static void makePermutation(int r, int[] temp, int current, boolean[] visited) {
        if (r == current) {
            System.out.println(Arrays.toString(temp));
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    temp[current] = arr[i];
                    makePermutation(r, temp, current + 1, visited);
                    visited[i] = false;
                }
            }
        }
    }
/*

순열 출력
[1, 2, 3]
[1, 2, 4]
[1, 2, 5]
[1, 3, 2]
[1, 3, 4]
[1, 3, 5]
[1, 4, 2]
[1, 4, 3]
[1, 4, 5]
[1, 5, 2]
[1, 5, 3]
[1, 5, 4]
[2, 1, 3]
[2, 1, 4]
[2, 1, 5]
[2, 3, 1]
[2, 3, 4]
[2, 3, 5]
[2, 4, 1]
[2, 4, 3]
[2, 4, 5]
[2, 5, 1]
[2, 5, 3]
[2, 5, 4]
[3, 1, 2]
[3, 1, 4]
[3, 1, 5]
[3, 2, 1]
[3, 2, 4]
[3, 2, 5]
[3, 4, 1]
[3, 4, 2]
[3, 4, 5]
[3, 5, 1]
[3, 5, 2]
[3, 5, 4]
[4, 1, 2]
[4, 1, 3]
[4, 1, 5]
[4, 2, 1]
[4, 2, 3]
[4, 2, 5]
[4, 3, 1]
[4, 3, 2]
[4, 3, 5]
[4, 5, 1]
[4, 5, 2]
[4, 5, 3]
[5, 1, 2]
[5, 1, 3]
[5, 1, 4]
[5, 2, 1]
[5, 2, 3]
[5, 2, 4]
[5, 3, 1]
[5, 3, 2]
[5, 3, 4]
[5, 4, 1]
[5, 4, 2]
[5, 4, 3]

 */


    /**
     * 순열 비트마스크
     *
     * @param reMake : 순열 생성된 배열
     * @param idx    : 생성된 순열 번호
     * @param val    : 중복 여부 체크용
     */
    private static void bitmaskPermutation(int n, int[] reMake, int idx, int val) {
        //원소 개수가 4이면
        if (idx == n) {
            System.out.println(Arrays.toString(reMake));
            return;
        }

        for (int i = 0; i < n; i++) {
            //중복체크 결과값이 0이면 미방문이고, 1이면 방문한 것
            if ((val & 1 << i) != 0)
                continue;

            //중복되지 않은 경우
            reMake[idx] = i;

            //flag | 1 << i 는 i 만큼 옮긴거랑 or을 해서 사용했으면 1 체크
            bitmaskPermutation(n, reMake, idx + 1, val | 1 << i);   //다른 요소 뽑기
        }
    }
/*
순열 비트마스크
[0, 1, 2, 3]
[0, 1, 3, 2]
[0, 2, 1, 3]
[0, 2, 3, 1]
[0, 3, 1, 2]
[0, 3, 2, 1]
[1, 0, 2, 3]
[1, 0, 3, 2]
[1, 2, 0, 3]
[1, 2, 3, 0]
[1, 3, 0, 2]
[1, 3, 2, 0]
[2, 0, 1, 3]
[2, 0, 3, 1]
[2, 1, 0, 3]
[2, 1, 3, 0]
[2, 3, 0, 1]
[2, 3, 1, 0]
[3, 0, 1, 2]
[3, 0, 2, 1]
[3, 1, 0, 2]
[3, 1, 2, 0]
[3, 2, 0, 1]
[3, 2, 1, 0]
 */


    /**
     * [중복 순열]
     * <p>
     * 1,2,3,4,5 중에서 순서를 고려해서, 중복을 포함한 3개를 뽑는 것이다. 1,2,1과 2,1,1을 다른 것으로 친다.
     * -> 총 개수 5개에서 중복 포함, 순서를 고려해서 3개를 뽑는 것이다.
     * <p>
     * 코드 설명 : 재귀로 구현하였다. 기존 순열의 코드에서 방문 여부만 제외해서 뽑게 되면 중복을 포함할 수 있다.
     *
     * @param r       : 뽑고자 하는 개수
     * @param temp    : r개를 뽑는 결과값을 저장해 놓는 배열
     * @param current : 현재 개수를 저장해 놓는 값
     */
    private static void makeOverlabPermutation(int r, int[] temp, int current) {
        if (r == current) {
            System.out.println(Arrays.toString(temp));
        } else {
            for (int i = 0; i < arr.length; i++) {
                temp[current] = arr[i];
                makeOverlabPermutation(r, temp, current + 1);
            }
        }
    }
/*

중복 순열 출력
[1, 1, 1]
[1, 1, 2]
[1, 1, 3]
[1, 1, 4]
[1, 1, 5]
[1, 2, 1]
[1, 2, 2]
[1, 2, 3]
[1, 2, 4]
[1, 2, 5]
[1, 3, 1]
[1, 3, 2]
[1, 3, 3]
[1, 3, 4]
[1, 3, 5]
[1, 4, 1]
[1, 4, 2]
[1, 4, 3]
[1, 4, 4]
[1, 4, 5]
[1, 5, 1]
[1, 5, 2]
[1, 5, 3]
[1, 5, 4]
[1, 5, 5]
[2, 1, 1]
[2, 1, 2]
[2, 1, 3]
[2, 1, 4]
[2, 1, 5]
[2, 2, 1]
[2, 2, 2]
[2, 2, 3]
[2, 2, 4]
[2, 2, 5]
[2, 3, 1]
[2, 3, 2]
[2, 3, 3]
[2, 3, 4]
[2, 3, 5]
[2, 4, 1]
[2, 4, 2]
[2, 4, 3]
[2, 4, 4]
[2, 4, 5]
[2, 5, 1]
[2, 5, 2]
[2, 5, 3]
[2, 5, 4]
[2, 5, 5]
[3, 1, 1]
[3, 1, 2]
[3, 1, 3]
[3, 1, 4]
[3, 1, 5]
[3, 2, 1]
[3, 2, 2]
[3, 2, 3]
[3, 2, 4]
[3, 2, 5]
[3, 3, 1]
[3, 3, 2]
[3, 3, 3]
[3, 3, 4]
[3, 3, 5]
[3, 4, 1]
[3, 4, 2]
[3, 4, 3]
[3, 4, 4]
[3, 4, 5]
[3, 5, 1]
[3, 5, 2]
[3, 5, 3]
[3, 5, 4]
[3, 5, 5]
[4, 1, 1]
[4, 1, 2]
[4, 1, 3]
[4, 1, 4]
[4, 1, 5]
[4, 2, 1]
[4, 2, 2]
[4, 2, 3]
[4, 2, 4]
[4, 2, 5]
[4, 3, 1]
[4, 3, 2]
[4, 3, 3]
[4, 3, 4]
[4, 3, 5]
[4, 4, 1]
[4, 4, 2]
[4, 4, 3]
[4, 4, 4]
[4, 4, 5]
[4, 5, 1]
[4, 5, 2]
[4, 5, 3]
[4, 5, 4]
[4, 5, 5]
[5, 1, 1]
[5, 1, 2]
[5, 1, 3]
[5, 1, 4]
[5, 1, 5]
[5, 2, 1]
[5, 2, 2]
[5, 2, 3]
[5, 2, 4]
[5, 2, 5]
[5, 3, 1]
[5, 3, 2]
[5, 3, 3]
[5, 3, 4]
[5, 3, 5]
[5, 4, 1]
[5, 4, 2]
[5, 4, 3]
[5, 4, 4]
[5, 4, 5]
[5, 5, 1]
[5, 5, 2]
[5, 5, 3]
[5, 5, 4]
[5, 5, 5]

 */


    /**
     * 중복 조합 백트랙킹
     *
     * @param index
     * @param depth
     */
    private static void 중복조합(int index, int depth) {

        if (depth == N) {
            System.out.println(Arrays.toString(temp));
            return;
        }

        if (index == arr.length) {
            return;
        }

        temp[depth] = arr[index];
        // index를 안늘려주면 다음 depth에서도 계속 같은 index 가르킨다
        중복조합(index, depth + 1);
        // 대신 출력된 이후에는 값이 바껴야 되므로 index 추가
        중복조합(index + 1, depth);
    }
/*
중복 조합 출력
[1, 1, 1]
[1, 1, 2]
[1, 1, 3]
[1, 1, 4]
[1, 1, 5]
[1, 2, 2]
[1, 2, 3]
[1, 2, 4]
[1, 2, 5]
[1, 3, 3]
[1, 3, 4]
[1, 3, 5]
[1, 4, 4]
[1, 4, 5]
[1, 5, 5]
[2, 2, 2]
[2, 2, 3]
[2, 2, 4]
[2, 2, 5]
[2, 3, 3]
[2, 3, 4]
[2, 3, 5]
[2, 4, 4]
[2, 4, 5]
[2, 5, 5]
[3, 3, 3]
[3, 3, 4]
[3, 3, 5]
[3, 4, 4]
[3, 4, 5]
[3, 5, 5]
[4, 4, 4]
[4, 4, 5]
[4, 5, 5]
[5, 5, 5]
 */


    public static int combination(int n, int r) {
        if (n == r || r == 0) {
            System.out.println("---------------------------------");
            for(int i=0; i<dp[n].length; i++){
                System.out.println(Arrays.toString(dp[i]));
            }
            System.out.println("---------------------------------");
            return 1;
        }

        if (dp[n][r] > 0) return dp[n][r];


        dp[n][r] = combination(n - 1, r - 1) + combination(n - 1, r);

        return dp[n][r];
    }
}