package algo.LowLevelExample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class mira_수송차량대여 {
    static int n, w, ans, tot;
    static int[] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        map = new int[n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int rh = Integer.parseInt(st.nextToken());
            int rw = Integer.parseInt(st.nextToken());
            //가로 세로 중 작은 값만 저장
            map[i] = rh > rw ? rw : rh;
        }

        //오름차순 정렬
        Arrays.sort(map);

        //몇대의 트럭이 필요한지 계산한다.
        truckCount();
        System.out.println(ans+"대");
    }

    /**
     * 얼마나 많은 트럭이 필요한지 세기 위해 다음과 같이 진행한다.
     * 정렬된 값의 0번 인덱스와 맨 마지막 인덱스를 각각 i, j로 가리킨다.
     * j가 가리키는 인덱스의 값이 가장 큰 값이므로 가장 큰 값을 위주로 먼저 누적해서 트럭의 길이를 초과하기 직전까지만 더한다.
     * 이후 나머지 공간에 i가 가리키는 작은 값으로 빈자리를 최대한 채워나간다.
     * 이 작업을 i와 j 인덱스가 교차할 순간까지 진행시킨다.
     */
    private static void truckCount(){
        int i=0, j=n-1;

        while (true) {

            //큰 값이 들어 있는 인덱스부터 작은 값이 있는 인덱스로 이동해가며 값을 누적시킨다.
            while(true){
                if(j<i) break;
                tot += map[j--];
                //누적 값이 트럭 길이를 초과하면 초과 직전까지만 누적하고 종료한다.
                if(tot > w){
                    j++;
                    tot -= map[j];
                    break;
                }
            }

            //작은 값이 들어 있는 인덱스부터 큰 값이 들어 있는 인덱스로 이동해가며 값을 누적시킨다.
            while (true) {
                if(i>j) break;
                tot += map[i++];
                //누적 값이 트럭 길이를 초과하면 트럭 1대분의 적재가 완료된 것인데 완료처리를 위해 반복을 종료시킨다.
                if(tot > w){
                    i--;
                    tot -= map[i];
                    break;
                }
            }

            //i와 j가 교차되는 순간 인덱스 오류 방지를 위한 처리
            int ii = i >= n ? n-1 : i;
            int jj = j < 0 ? 0 : j;

            //트럭 한대분이 찬 경우는 큰 값의 누적과 작은 값의 누적합이 트럭 길이를 초과하는지 확인하여 처리한다.
            if(tot + map[jj] > w && tot + map[ii] > w){
                ans++;      //트럭 대수를 증가시킨다.
                tot = 0;    //누적 변수를 초기화한다. 다시 1대분의 적재를 카운트하기 위해서..
            }

            //맨 마지막 짜투리가 남은 경우인지를 체크해서 짜투리가 남은 경우 트럭을 한대 추가한다.
            if((i>j || j<i) && tot > 0 ){
                ans++;
            }

            //모든 자료의 검색이 완료되면 종료한다.
            if(i>j || j<i) break;
        }
    }
}
