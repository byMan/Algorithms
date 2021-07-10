package algo.Pro원정대.FifthDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 문제_알뜰기차여행_06 {

	static int n, g;
	static int[] dist;
	static ArrayList<Node>[] arr;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static class Node implements Comparable<Node>{
		int next, cost;
		Node(int a, int b){
			next = a; cost = b;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		
		dist = new int[n];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		arr = new ArrayList[n];
		for(int i=0; i<n; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for(int i=0; i<g; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			arr[from].add(new Node(to, weight));
		}
		
		dijkstra(0);
		if(dist[n-1] == Integer.MAX_VALUE) {
			System.out.println("impossible");
		}else {
			System.out.println(dist[n-1]);
		}
	}

	
	private static void dijkstra(int start) {
		PriorityQueue<Node> q = new PriorityQueue();
		q.add(new Node(start, 0));
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			int next = now.next;
			int cost = now.cost;
			//동일 index에 이미 값이 설정되었다면 이미 최소 값으로 입력된 경우이므로 패스한다.
			if(dist[next] < Integer.MAX_VALUE) continue;
			dist[next] = cost;
			for(Node other : arr[next]) {
				q.add(new Node(other.next, now.cost + other.cost));
			}
		}
	}
	
}
