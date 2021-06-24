package lecture.ThirdDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 문제_정중앙대학교_01 {

	static int N;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PriorityQueue<Integer> maxLeft = new PriorityQueue<>(Collections.reverseOrder());
	static PriorityQueue<Integer> minRight = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		minRight.add(500);
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			maxLeft.add(a);
			minRight.add(b);
			
			int left = maxLeft.peek();
			int right = minRight.peek();
			
			//minRight의 가장 처음 값을 정중앙 값으로 출력할 것이므로 정중앙 값보다 크다면 교환한다.
			if(left > right) {
				left = maxLeft.poll();
				right = minRight.poll();
				minRight.add(left);
				maxLeft.add(right);
			}
			
			System.out.println(minRight.peek());
		}
	}
}
