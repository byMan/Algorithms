package lecture.SecondDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class PriorityQueue활용_01 {

	static int de;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		int[] arr = {5, 432, 54,2, 9, -1, -59, 39, 487, 87, 100};
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i=0; i<arr.length; i++) {
			pq.add(arr[i]);
		}
		
		while(!pq.isEmpty()) {
			System.out.print(pq.poll() + " ");
		}
		System.out.println();
		
		PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>(Collections.reverseOrder());
		for(int i=0; i<arr.length; i++) {
			pq2.add(arr[i]);
		}
		
		while(!pq2.isEmpty()) {
			System.out.print(pq2.poll() + " ");
		}
		System.out.println();
	}

}
