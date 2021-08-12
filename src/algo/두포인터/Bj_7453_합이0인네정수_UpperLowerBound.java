package algo.두포인터;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_7453_합이0인네정수_UpperLowerBound {
    static long ANS;
    static int N, idx;
    static int[][] map;
    static int[] AB, CD;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        map = new int[N][4];
        AB = new int[N * N];
        CD = new int[N * N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                AB[idx] = map[i][0] + map[j][1];
                CD[idx] = map[i][2] + map[j][3];
                idx++;
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        System.out.println(Arrays.toString(AB));
        System.out.println(Arrays.toString(CD));

        int left = 0, right = idx - 1;
        while (left < idx && right >= 0) {
            int tot = AB[left] + CD[right];
            if (tot == 0) {
                //UpperBound, LowerBound 로 찾도록 만들면 시간 초과 걸린다.
                //어차피 정렬되어 있기 때문에 동일 값은 연속으로 존재한다.
//                int startIdx = lowerBound(AB, AB[left]);
//                int endIdx = upperBound(AB, AB[left]);
//                long ABdiff = endIdx - startIdx;
//                left += ABdiff;
//
//                startIdx = lowerBound(CD, CD[right]);
//                endIdx = upperBound(CD, CD[right]);
//                long CDdiff = endIdx - startIdx;
//                right += CDdiff;
                int nowAbVal = AB[left];
                long ABsameCnt = 0;
                while(left < idx && nowAbVal == AB[left]){
                    left++;
                    ABsameCnt++;
                }

                int nowCdVal = CD[right];
                long CDsameCnt = 0;
                while(right >= 0 && nowCdVal == CD[right]){
                    right--;
                    CDsameCnt++;
                }

                ANS += ABsameCnt * CDsameCnt;
                continue;
            }
            if (tot > 0) {
                right--;
            } else {
                left++;
            }
        }

        System.out.println(ANS);
    }


    /**
     * 범위 [start, end) 안의 원소들 중, 특정 target보다 크거나 같은 첫번째 원소의 인덱스를 리턴한다. 만약 그런 원소가 없다면 end 인덱스를 리턴한다.
     * 이때 리스트는 모두 정렬된 상태여야 한다. 즉, lower_bound가 성립하기 위해서는 각각의 요소들중 element < target를 만족하는 요소들은 만족하지 않는 요소들보다 앞에 있어야한다.
     *
     * @param param  탐색 대상 배열
     * @param target 찾고자 하는 값
     * @return
     */
    private static int lowerBound(int[] param, int target) {
        int start = 0;
        int end = idx;

        while (start < end) {
            int mid = (start + end) / 2;
            if (param[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end == idx ? 0 : end;
    }


    /**
     * 범위 [start, end) 안의 원소들 중, 특정 target보다 큰 첫번째 원소의 인덱스를 리턴한다. 만약 그런 원소가 없다면 end 인덱스를 리턴한다.
     * 이때 리스트는 모두 정렬된 상태여야 한다. 즉, upper_bound가 성립하기 위해서는 각각의 요소들중 element <= target를 만족하는 요소들은 만족하지 않는 요소들보다 앞에 있어야한다. lower_bound와 비교해보면 등호가 들어가는데, target보다 큰 인덱스를 구하기 위해서이다.
     *
     * @param param
     * @param target
     * @return
     */
    private static int upperBound(int[] param, int target) {
        int start = 0;
        int end = idx;

        while (start < end) {
            int mid = (start + end) / 2;
            if (param[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return end == idx ? 0 : end;
    }
}
