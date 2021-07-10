package algo.Pro원정대.ThirdDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Recursive활용_04 {
	static int de;
	static int[] visit = new int[4];
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static ArrayList<Integer>[] adj = new ArrayList[4];
	
	public static void main(String[] args) throws Exception {
		for(int i=0; i<4; i++)
			adj[i] = new ArrayList<>();
		adj[0].add(1);
		adj[0].add(2);
		adj[0].add(3);
		adj[1].add(0);
		adj[1].add(3);
		adj[2].add(1);
		adj[2].add(3);
		
		visit[0] = 1;
		dfs(0);
	}

	
	private static void dfs(int n) {
		
		//다음 노드 찾아 재귀호출 adj[now]에서 찾기
		for(int val : adj[n]) {
			//방문체크
			if(visit[val] == 1) continue;
			visit[val] = 1;
			dfs(val);
		}
	}
}
