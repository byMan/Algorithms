package lecture.ThirdDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 문제_SPY와조직도_인접행렬_강사코드 {
    static int de; 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int map[][] = new int[11][11]; // 0~10
    public static void main(String[] args) throws IOException {
        int id[] = {1004,1680,9941,3367,3261,2976,4889,1234,6461,7329,5518};
        map[0][1] = 1;
        map[0][2] = 1;
        map[1][3] = 1;
        map[1][4] = 1;
        map[2][5] = 1;
        map[2][6] = 1;
        map[4][7] = 1;
        map[4][8] = 1;
        map[5][9] = 1;
        map[5][10] = 1;

        int num = Integer.parseInt(br.readLine());
        // 몇번 노드인지 탐색
        int now = -1;
        for(int i = 0 ; i < 11; i ++) { 
            if(num == id[i]) { 
                now = i; break; 
            }
        }
        if(now == -1) { 
            System.out.println("no person");
            return;
        }

        // 부모(보스)찾기   [from(0~10)][now] from -> now
        int boss = -1;
        for(int from = 0 ; from < 11;from++) {
            if(map[from][now] == 1) { 
                boss = from; 
                break; 
            }
        }
        if(boss == -1) { 
            System.out.println("no boss");
        }
        else System.out.println(id[boss]);

        // 동료(형제) 찾기     1. 부모를 먼저 찾은 다음   2. 자식을 찾는다.
        if(boss == -1) {
            System.out.println("no company");
        }
        else {// boss -> next
            for(int next = 0; next < 11; next ++) { 
                if(map[boss][next] == 1 && next != now) { 
                    System.out.println(id[next]);
                }
            }
        }

        int flag = 0; 
        // 부하(자식) 찾기  [now][next] now->next
        for(int next = 0 ;next < 11; next ++) { 
            if(map[now][next] == 1) { 
                flag = 1;
                System.out.print(id[next] + " ");
            }
        }
        if(flag == 0) { 
            System.out.println("no junior");
        }
    }
}

