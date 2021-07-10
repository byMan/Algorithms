package algo.Pro원정대.ThirdDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 문제_BFS탐색기초_01 {
	static int de;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static ArrayList<Integer>[] adj = new ArrayList[8];
	
	static class Node {
		int num;
		int level;
		public Node(int num, int level) {
			this.num = num;
			this.level = level;
		}
	}
	
	public static void main(String[] args) throws Exception {
		for(int i=0; i<8; i++)
			adj[i] = new ArrayList<>();
		
		adj[0].add(1);
		adj[0].add(2);
		adj[1].add(3);
		adj[1].add(4);
		adj[2].add(5);
		adj[3].add(6);
		adj[3].add(7);
		
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0));
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int now = node.num;
			int level = node.level;
			System.out.print(now + ":" + level + "\t");
			
			//자식노드 큐 등록
			for(int next : adj[now]) {
				q.add(new Node(next, level+1));
			}
		}
	}

}
