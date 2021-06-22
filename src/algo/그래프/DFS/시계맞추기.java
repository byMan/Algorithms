package algo.그래프.DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 시계맞추기 {

    static int CNT_CLOCK = 16, CNT_BUTTON = 10, MIN = 9999;
    static int[] clockArray = new int[CNT_CLOCK];
    static int[][] button_Clock = new int[][] {
        {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1},
        {1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0},
        {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
        {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
        {0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1},
        {0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int loop = Integer.parseInt(br.readLine());
        int[] result = new int[loop];

        int tmp;

        while(loop-- > 0){
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<CNT_CLOCK; i++){
                clockArray[i] = Integer.parseInt(st.nextToken());
            }

            tmp = solve(0);

            //크게 나온 값은 불가능하다 판단하고 -1을 반환
            result[loop] = tmp >= MIN ? -1 : tmp;
        }

        for(int i=result.length-1; i>=0; i--){
            System.out.println(result[i]);
        }
    }

    /**
     * 모든 시계가 12시를 가르키는지 체크
     * @return
     */
    private static boolean isAllClock12() {
        for(int i=0; i<CNT_CLOCK; i++){
            if(clockArray[i] != 12)
                return false;
        }
        return true;
    }


    private static void clickButton(int button){
        for(int clock=0; clock<CNT_CLOCK; clock++){
            if(button_Clock[button][clock] == 1) {
                clockArray[clock] += 3;
                if(clockArray[clock] == 15){
                    clockArray[clock] = 3;
                }
            }
        }
    }


    private static int solve(int button){

        // 0~9번 버튼에 대해 각각 times번 눌렀을 때, 12시인지 확인.
        // 재귀 함수에 대해 헷갈렸던 부분.
        // 4^10 완전 탐색을 하므로 10중 for문을 돌린다고 생각하면 좀 더 이해하기 쉽다.
        //        출처: https://smallgiant.tistory.com/56 [나에게 남기는 지식]
        if(button == CNT_BUTTON){
            return isAllClock12() ? 0 : MIN;
        }

        int min = MIN;

        // 아래 for문을 재귀 함수로 10중 for문을 만든다고 생각하면 된다.
        // clickButton을 재귀 밑에 둠으로써 0번 눌렀을 때도 확인할 수 있으며,
        // times가 3일때도 한 번 더 눌러 원상태로 만들어 다음 재귀 함수를 돌린다.
        for(int times=0; times<4; times++){
            min = Math.min(min, times + solve(button + 1));
            clickButton(button);
        }

        return min;
    }
}


/*

문제 출처 : https://algospot.com/judge/problem/read/CLOCKSYNC

그림과 같이 4 x 4 개의 격자 형태로 배치된 16개의 시계가 있다.
이 시계들은 모두 12시, 3시, 6시, 혹은 9시를 가리키고 있다.
이 시계들이 모두 12시를 가리키도록 바꾸고 싶다.

시계의 시간을 조작하는 유일한 방법은 모두 10개 있는 스위치들을 조작하는 것으로, 각 스위치들은 모두 적게는 3개에서 많게는 5개의 시계에 연결되어 있다.
한 스위치를 누를 때마다, 해당 스위치와 연결된 시계들의 시간은 3시간씩 앞으로 움직인다.
스위치들과 그들이 연결된 시계들의 목록은 다음과 같다.

0	0, 1, 2
1	3, 7, 9, 11
2	4, 10, 14, 15
3	0, 4, 5, 6, 7
4	6, 7, 8, 10, 12
5	0, 2, 14, 15
6	3, 14, 15
7	4, 5, 7, 14, 15
8	1, 2, 3, 4, 5
9	3, 4, 5, 9, 13

시계들은 맨 윗줄부터, 왼쪽에서 오른쪽으로 순서대로 번호가 매겨졌다고 가정하자.
시계들이 현재 가리키는 시간들이 주어졌을 때, 모든 시계를 12시로 돌리기 위해 최소한 눌러야 할 스위치의 수를 계산하는 프로그램을 작성하시오.

[ 입력 ]
첫 줄에 테스트 케이스의 개수 C (<= 30) 가 주어진다.
각 테스트 케이스는 한 줄에 16개의 정수로 주어지며, 각 정수는 0번부터 15번까지 각 시계가 가리키고 있는 시간을 12, 3, 6, 9 중 하나로 표현한다.

[ 출력 ]
각 테스트 케이스당 한 줄을 출력한다. 시계들을 모두 12시로 돌려놓기 위해 눌러야 할 스위치의 최소 수를 출력한다. 만약 이것이 불가능할 경우 -1 을 출력한다.

[ 예제 입력 ]
2
12 6 6 6 6 6 12 12 12 12 12 12 12 12 12 12
12 9 3 12 6 6 9 3 12 9 12 9 12 12 6 6

[ 예제 출력 ]
2
9



 */