package algo.세그먼트트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 구간 합 구하기 백준 2042
public class SegmentTree_2042 {

    static int N, M, K;                // N 숫자 개수, M 변경 개수, K 질의 개수
    static int nn;                    // 인덱스드트리 개수
    static long[] indexedTree;        // 인덱스드트리

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N, M, K 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (nn = 1; nn < N; nn *= 2) ;    // 인덱스드트리 크기 특정 및 선언   (N보다 큰 최소 2^N)
        indexedTree = new long[nn * 2 + 2];

        // 인덱스드트리에 데이터 입력	 - 트리 하단에 1~N개 수의 최초 값을 입력
        for (int i = 1; i <= N; i++) {
            indexedTree[nn + i - 1] = Integer.parseInt(br.readLine());
        }
        // 구간합 데이터 만들어줌 - 밑에서부터 데이터를 쌓아올림
        for (int i = nn - 1; i >= 1; i--) {
            indexedTree[i] = indexedTree[i * 2] + indexedTree[i * 2 + 1];
        }

        int size = M + K;    // 변경과 쿼리의 합을 반복문 크기로 설정
        for (int i = 1; i <= size; i++) {
            int type, x, y;
            st = new StringTokenizer(br.readLine());
            // 1이면 edit, 2이면 쿼리
            type = Integer.parseInt(st.nextToken());
            // type=1 바꿀 수 / type=2 startId
            x = Integer.parseInt(st.nextToken());
            // type=1 변경값 / type=2 endId
            y = Integer.parseInt(st.nextToken());

            if (type == 1) {
                edit(x, y);
            } else {
                bw.write(String.valueOf(sum(x, y)) + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    // 인덱스드트리 수정 1) 해당 id 값 수정 2) 위로 올라가면서 값 갱신 -> logN의 시간복잡도
    static void edit(int id, int value) {
        int x = id + nn - 1;
        // 인덱스드트리 위치에 value로 값을 덮어쓰고
        indexedTree[x] = value;
        x /= 2;
        while (x > 0) {
            indexedTree[x] = indexedTree[x * 2] + indexedTree[x * 2 + 1];
            x /= 2;
        }
        return;
    }

    // 인덱스드트리 합 구하기 위로 올라가면서 합 구하기
    static long sum(int start, int end) {
        int l = start + nn - 1;
        int r = end + nn - 1;
        long ret = 0;
        while (l <= r) {
            // 왼쪽 id가 홀수이면 값이 튀므로 더해주고 l++ 해줌 (짝수부터 시작해야하니까)
            if ((l & 1) == 1) ret += indexedTree[l++];
            // 오른쪽 id가 짝수이면 값이 튀므로 더해주고 r-- 해줌 (홀수로 끝나야하니까)ㄴ
            if ((r & 1) == 0) ret += indexedTree[r--];
            // 위로 올라가기
            l /= 2;
            r /= 2;
        }
        return ret;
    }
}


/*

문제
어떤 N개의 수가 주어져 있다. 그런데 중간에 수의 변경이 빈번히 일어나고 그 중간에 어떤 부분의 합을 구하려 한다. 만약에 1,2,3,4,5 라는 수가 있고, 3번째 수를 6으로 바꾸고 2번째부터 5번째까지 합을 구하라고 한다면 17을 출력하면 되는 것이다. 그리고 그 상태에서 다섯 번째 수를 2로 바꾸고 3번째부터 5번째까지 합을 구하라고 한다면 12가 될 것이다.

입력
첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)과 M(1 ≤ M ≤ 10,000), K(1 ≤ K ≤ 10,000) 가 주어진다. M은 수의 변경이 일어나는 횟수이고, K는 구간의 합을 구하는 횟수이다. 그리고 둘째 줄부터 N+1번째 줄까지 N개의 수가 주어진다. 그리고 N+2번째 줄부터 N+M+K+1번째 줄까지 세 개의 정수 a, b, c가 주어지는데, a가 1인 경우 b(1 ≤ b ≤ N)번째 수를 c로 바꾸고 a가 2인 경우에는 b(1 ≤ b ≤ N)번째 수부터 c(b ≤ c ≤ N)번째 수까지의 합을 구하여 출력하면 된다.

입력으로 주어지는 모든 수는 -263보다 크거나 같고, 263-1보다 작거나 같은 정수이다.

출력
첫째 줄부터 K줄에 걸쳐 구한 구간의 합을 출력한다. 단, 정답은 -263보다 크거나 같고, 263-1보다 작거나 같은 정수이다..


[입력예제]
5 2 2
1
2
3
4
5
1 3 6
2 2 5
1 5 2
2 3 5


[출력결과]
17
12

 */