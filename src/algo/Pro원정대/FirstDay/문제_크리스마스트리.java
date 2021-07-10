package algo.Pro원정대.FirstDay;

import java.util.ArrayList;
import java.util.Scanner;

public class 문제_크리스마스트리 {
	static int de;
	static Scanner sc = new Scanner(System.in);
	static int n;
	static int left[];// left[a] a의 왼쪽 노드 번호
	static int right[]; // right[a] a 의 오른쪽 노드번호
	static ArrayList<Integer> preorder = new ArrayList<Integer>();
	static ArrayList<Integer> inorder = new ArrayList<Integer>();
	static ArrayList<Integer> postorder = new ArrayList<Integer>();

	static void run(int now) {
		int Left = left[now];
		int Right = right[now];

		preorder.add(now); // preorder
		if (Left != -1)
			run(Left);
		inorder.add(now); // inorder
		if (Right != -1)
			run(Right);
		postorder.add(now); // post order
		de = -1;
		return;
	}

	public static void main(String args[]) {
		n = sc.nextInt();
		left = new int[110];
		right = new int[110];
		for (int i = 1; i <= n; i++) {
			int now = sc.nextInt();
			int l = sc.nextInt();
			int r = sc.nextInt();
			left[now] = l;
			right[now] = r;
		}

		run(1);
		for (Integer node : inorder)
			System.out.print(node + " ");
		System.out.println();

		for (Integer node : preorder)
			System.out.print(node + " ");
		System.out.println();

		for (Integer node : postorder)
			System.out.print(node + " ");
		System.out.println();
		return;
	}
}
