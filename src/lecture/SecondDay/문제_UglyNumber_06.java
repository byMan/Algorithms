package lecture.SecondDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 문제_UglyNumber_06 {

	static int de; 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        PriorityQueue<Long>pq = 
                new PriorityQueue<>();
        long res[] = new long[1501]; 
        int th = 1;

        pq.add((long)1); 
        long used = - 1;	//큐에서 뽑은 수가 중복인지 체크용
        while(true) {
            long now = pq.poll(); 
            if(now == used) continue;	//이전에 뽑은 수와 동일한 값이라면 건너뛴다. 
            res[th++] = now; 
            if(th >= 1501) break;
            pq.add(now * 2);
            pq.add(now * 3); 
            pq.add(now * 5); 
            used = now; 
        }
        de = -1;

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ;i < N; i ++) { 
            int a = Integer.parseInt(st.nextToken());
            System.out.print(res[a] + " ");
        }
    }
}
