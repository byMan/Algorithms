package algo.Pro원정대.SecondDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Queue활용_01 {
	
	static int de; 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        Queue<Integer> qu = new LinkedList<Integer>(); 
        int vect[] = { 3,2,1,7,5,2,6,5,1,2,3,4};
        int t = 0 ; 
        // 4개 등록 
        for(int i = 0 ; i < 4; i ++) { 
            qu.add(vect[t]) ; 
            t ++ ; 
        }
        de = -1;
        // 3개처리 4개등록 
        while(!qu.isEmpty()) { 
            for(int i = 0 ; i < 3; i ++) { 
                int ret = qu.poll(); 
                System.out.print(ret + " ");  // 처리
            }
            System.out.println();
            if(t >= 12) continue; 
            // 4개입력
            for(int i = 0 ; i < 4; i ++) { 
                qu.add(vect[t]);
                t ++; 
            }
        }
    }
}
