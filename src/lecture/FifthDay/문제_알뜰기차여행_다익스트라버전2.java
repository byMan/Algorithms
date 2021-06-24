package lecture.FifthDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 문제_알뜰기차여행_다익스트라버전2 {

	static int de;
	static final int INF = (int) 21e8;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static class Node implements Comparable<Node> {
		int num;// 노드 번호
		int cost;// 비용

		Node(int a, int b) {
			num = a;
			cost = b;
		}

		@Override
		public int compareTo(Node target) {
			// <- 작은 비용 큰 비용->
			if (this.cost < target.cost)
				return -1;
			if (this.cost > target.cost)
				return 1;
			return 0;
		}
	}

	static ArrayList<Node> adj[];
	static int N, T;
	// 시작점 ~~~~~~> (ALL) K지점으로 최소 비용
	static int dist[];

	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist = new int[N];
		for (int i = 0; i < N; i++) {
			dist[i] = INF;
		}
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (now.cost > dist[now.num])
				continue;
			dist[now.num] = now.cost;
			for (Node next : adj[now.num]) {
				// now.cost + next.cost (시작점 -> now) + (now -> next )
				// dist[next.num] (시작점->next)
				if (now.cost + next.cost < dist[next.num]) {
					dist[next.num] = now.cost + next.cost;
					pq.add(new Node(next.num, dist[next.num]));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N]; // 0~N-1
		for (int i = 0; i < N; i++)
			adj[i] = new ArrayList<Node>();
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adj[from].add(new Node(to, weight));
		}
		dist = new int[N];
		dijkstra(0);
		if (dist[N - 1] == INF)
			System.out.println("impossible");
		else
			System.out.println(dist[N - 1]);
	}

}