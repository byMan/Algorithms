package algo.Pro원정대.FifthDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 문제_신규도로_강사코드 {
	static int de;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final int INF = (int) 21e8;

	static class Node implements Comparable<Node> {
		int num;
		int cost;

		Node(int a, int b) {
			num = a;
			cost = b;
		}

		@Override
		public int compareTo(Node target) {
			// TODO Auto-generated method stub
			if (this.cost < target.cost)
				return -1;
			if (this.cost > target.cost)
				return 1;
			return 0;
		}

	}

	static int N, M;
	static ArrayList<Node> adj[];
	static int dist1[];
	static int distN[];

	static void dijkstra(int start, int dist[]) {
		// init
		for (int i = 1; i <= N; i++)
			dist[i] = INF;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (dist[now.num] < INF)
				continue;
			dist[now.num] = now.cost;
			for (Node next : adj[now.num]) {
				pq.add(new Node(next.num, next.cost + now.cost));
			}
		}
	}

	static int bSearch(int arr[], int target) {
		// arr[x] <= target 만족하는 x 의 최댓값을 찾게 되면
		// x 이하 모든 원소들이 조건 만족
		int s = 0;
		int e = arr.length;
		int ans = -1;
		while (s <= e) {
			int mid = (s + e) / 2;
			if (arr[mid] <= target) {
				ans = mid;
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}
		if (ans == -1)
			return 0;
		else {
			return ans + 1;
		}
	}

	public static void main(String[] args) throws IOException {
		int tc = Integer.parseInt(br.readLine());
		for (int tt = 1; tt <= tc; tt++) {
			input();
			dist1 = new int[N + 1]; // 1 ~ N
			distN = new int[N + 1];

			dijkstra(1, dist1);
			dijkstra(N, distN);

			int arr[] = new int[N - 2]; // distN 복사 , 1번,N번 베타x
			for (int i = 2; i <= N - 1; i++)
				arr[i - 2] = distN[i];
			Arrays.sort(arr);

			int cnt = 0;
			for (int i = 2; i <= N - 1; i++) {
				// dist1에서 선택
				int a = dist1[i];
				int ori = dist1[N]; // 원래 비용(1->N)

				int ret = bSearch(arr, ori - a - 2);// arr[x] <= ori - a - 2의 x의 최댓값찾기
				cnt += ret;
			}
			System.out.println("#" + tt + " " + cnt);
		}

	}

	static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			adj[i] = new ArrayList<Node>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adj[a].add(new Node(b, weight));
			adj[b].add(new Node(a, weight));
		}
	}
}