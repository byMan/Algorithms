package lecture.SecondDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 문제_PriorityQue_06 {
	
	static int N;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		String str = "";
		PriorityQueue<Integer> q = new PriorityQueue(Collections.reverseOrder());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			str = st.nextToken();
			int val = Integer.parseInt(st.nextToken());
			
			if("push".equalsIgnoreCase(str)) {
				q.add(val);
			}else if("pop".equalsIgnoreCase(str)) {
				for(int k=0; k<val; k++) {
					System.out.print(q.poll() + " ");
				}
				System.out.println();
			}else {
				q.add(q.poll() + val);
			}
		}
	}

}

/*
 
[입력]
8
push 2
push 3
pop 2 
push 10
push 5
push 12
add -3
pop 3

[출력]
3 2
10 9 5

*/