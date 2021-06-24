package lecture.SecondDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 문제_회의실배정_04 {
	
	static int de;
	static Node[] node;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static class Node implements Comparable<Node>{
		int start;
		int end;
		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Node o) {
			//종료시간이 작은 값이 먼저 오도록
			if(this.end < o.end) return -1;
			if(this.end > o.end) return 1;
			
			//시작 시간이 큰 값이 먼저 오도록
			if(this.start < o.end) return 1;
			if(this.start > o.end) return -1;
			return 0;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		
		node = new Node[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			node[i] = new Node(start, end);
		}
		
		Arrays.sort(node);
		
		de = -1;

		int start = 0, end = 0, cnt = 0;
		for(int i=0; i<N; i++) {
			if(i==0) {
				start = node[i].start;
				end = node[i].end;
				cnt++;
			}else {
				if(end > node[i].start) continue;
				start = node[i].start;
				end = node[i].end;
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}
