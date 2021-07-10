package algo.Pro원정대.SecondDay;

import java.util.PriorityQueue;

public class PriorityQueue활용_02 {
	
	static class Node implements Comparable<Node>{
		int n;
		int ch;
		public Node(int n, char ch) {
			this.n = n;
			this.ch = ch;
		}
		@Override
		public int compareTo(Node o) {
			//숫자 오름차순으로
			if(this.n < o.n) return -1;
			if(this.n > o.n) return 1;
			
			//문자 내림차순으로
			if(this.ch < o.ch) return 1;
			if(this.ch > o.ch) return -1;
			return 0;
		}
		
		public String toString() {
			return n + " " + (char)ch;
		}
	}
	
	public static void main(String[] args) {
		Node[] node = {
				new Node(3, 'A'),
				new Node(2, 'B'),
				new Node(1, 'C'),
				new Node(1, 'D'),
				new Node(1, 'E'),
				new Node(5, 'F'),
				new Node(2, 'K'),
				new Node(9, 'Q'),
		};
		
		PriorityQueue<Node> q = new PriorityQueue<>();
		
		for(int i=0; i<node.length; i++) {
			q.add(node[i]);
		}
		
		while(!q.isEmpty()) {
			System.out.println(q.poll().toString());
		}
	}

}
