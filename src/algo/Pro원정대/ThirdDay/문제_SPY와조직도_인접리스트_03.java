package algo.Pro원정대.ThirdDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 문제_SPY와조직도_인접리스트_03 {

	static int[] arr = { 1004, 1680, 9941, 3367, 3261, 2976, 4889, 1234, 6461, 7329, 5518 };;
	static int n;
	static ArrayList<Integer>[] list;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		n = 11;

		list = new ArrayList[11];

		for (int i = 0; i < n; i++) {
			list[i] = new ArrayList<>();
		}

		list[0].add(1);
		list[0].add(2);
		list[1].add(3);
		list[1].add(4);
		list[2].add(5);
		list[2].add(6);
		list[4].add(7);
		list[4].add(8);
		list[5].add(9);
		list[5].add(10);

		int empNo = Integer.parseInt(br.readLine());
		int nowIdx = findNowIdx(empNo);

		int parentIdx = findParent(nowIdx);
		findCompany(parentIdx, nowIdx);
		findJunior(nowIdx);
	}

	private static void findJunior(int nowIdx) {
		boolean notExist = true;
		for (int val : list[nowIdx]) {
			notExist = false;
			System.out.print(arr[val] + " ");
		}

		if (notExist) {
			System.out.println("no company");
		} else {
			System.out.println();
		}
	}

	private static void findCompany(int parentIdx, int nowIdx) {
		boolean notExist = true;
		for (int val : list[parentIdx]) {
			if (nowIdx != val) {
				notExist = false;
				System.out.println(arr[val]);
			}
		}

		if (notExist) {
			System.out.println("no company");
		} else {
			System.out.println();
		}
	}

	private static int findParent(int nowIdx) {
		for (int i = 0; i < n; i++) {
			for (int val : list[i]) {
				if (nowIdx == val) {
					System.out.println(arr[i]);
					return i;
				}
			}
		}
		System.out.println("no boss");
		return -1;
	}

	private static int findNowIdx(int empNo) {
		for (int i = 0; i < n; i++) {
			if (empNo == arr[i]) {
				return i;
			}
		}
		return -1;
	}
}
