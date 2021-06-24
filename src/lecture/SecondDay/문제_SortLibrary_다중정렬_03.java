package lecture.SecondDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 문제_SortLibrary_다중정렬_03 {

	static int de;
	static Integer[] arr;
	static Node[] nodeArr;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static class Node implements Comparable<Node>{
		int n;
		char ch;
		public Node(int n, char ch) {
			this.n = n;
			this.ch = ch;
		}
		@Override
		public int compareTo(Node o) {
			//<- 짝수 홀수 -->
			if(this.n % 2 == 0 && o.n % 2 == 1) return -1;
			if(this.n % 2 == 1 && o.n % 2 == 0) return 1;
			
			//<- 작은 수   큰 수 -->
			if(this.n < o.n) return -1;
			if(this.n > o.n) return 1;
			
			//<--알바벳 빠른   알파벳 느린 ->
			if(this.ch < o.ch) return -1;
			if(this.ch > o.ch) return 1;
			
			return 0;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		arr = new Integer[N];
		nodeArr = new Node[N];
		
		st = new StringTokenizer(br.readLine());
		for(int j=0; j<N; j++) {
			arr[j] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int j=0; j<N; j++) {
			nodeArr[j] = new Node(arr[j], st.nextToken().charAt(0));
		}
		
		
		//오름차순
		Arrays.sort(arr);
		for(int i=0; i<N; i++)
			System.out.print(arr[i] + " ");
		
		System.out.println();
		//내림차순
		Arrays.sort(arr, Collections.reverseOrder());
		for(int i=0; i<N; i++)
			System.out.print(arr[i] + " ");
		
		System.out.println();
		Arrays.sort(nodeArr);
		for(int i=0; i<N; i++)
			System.out.print(nodeArr[i].n + " ");
		
		System.out.println();
		for(int i=0; i<N; i++)
			System.out.print(nodeArr[i].ch + " ");
	}

}
