package lecture.ThirdDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 문제_프린터스케쥴링시스템코드 {
	static int de;

	static class Printer implements Comparable<Printer> {
		int possible_time; // 출력 가능한 시간
		int num; // 프린터 번호

		@Override // 프린터 우선순위 정하기
		public int compareTo(Printer target) {
			if (this.possible_time < target.possible_time)
				return -1;
			if (this.possible_time > target.possible_time)
				return 1;
			if (this.num < target.num)
				return -1;
			if (this.num > target.num)
				return 1;
			return 0;
		}

		Printer(int a, int b) {
			possible_time = a;
			num = b;
		}
	}

	static class Doc implements Comparable<Doc> {
		int req_time; // 요청한 시간
		int duration; // 출력에 걸리는 시간
		int index; // 문서 번호
		// 문서의 우선순위
		// 전체 대기시간(대기시간의 총합) 을 줄이기 위해서는 요청된 시간이 빠른것부터 출력을 해야한다.
		// 대기시간이 만약 같다면 출력이 빨리 끝나는것을 우선하되,
		// 동일한경우 문서번호가 작은것을 출력한다.

		@Override
		public int compareTo(Doc target) {
			if (req_time < target.req_time)
				return -1;
			if (req_time > target.req_time)
				return 1;
			if (duration < target.duration)
				return -1;
			if (duration > target.duration)
				return 1;
			if (index < target.index)
				return -1;
			if (index > target.index)
				return 1;
			return 0;
		}

		Doc(int a, int b, int c) {
			req_time = a;
			duration = b;
			index = c;
		}

	}

	static int N, M;
	static Printer printers[];
	static Doc documents[];

	public static void main(String args[]) throws IOException {
		// 문서를 우선순위대로 정렬한 뒤 ,
		// 출력가능한 프린터를 우선순위대로 선택하여 출력을 한다.
		input();
		Arrays.sort(documents, 1, N + 1); // 1~N 문서를 정렬한다.

		PriorityQueue<Printer> pq = new PriorityQueue<Printer>();
		for (int i = 1; i <= M; i++)
			printers[i] = new Printer(0, i);
		for (int i = 1; i <= M; i++)
			pq.add(printers[i]);

		// 출력가능시간에 프린터가 빠지면서 출력이 된다.

		int res[] = new int[N + 1];
		// 각 문서 번호(1~N) 에 대해 res[k] 는 k 번째 문서가 어떤 프린터를 썼는지를 저장한다.
		for (int i = 1; i <= N; i++) {
			Printer now = pq.poll();
			res[documents[i].index] = now.num;
			if (documents[i].req_time < now.possible_time)// 문서출력요청시간<프린터출력가능시간
			{
				// 출력을 기다렸다가 possible_time 에 맞춰서 출력을 한다.
				int fin = now.possible_time + documents[i].duration;
				pq.add(new Printer(fin, now.num));// 출력마무리되는시간, 번호를 다시 넣어준다.
			} else { // 프린터 출력가능시간 < 문서 출력요청시간
				int fin = documents[i].req_time + documents[i].duration;
				pq.add(new Printer(fin, now.num));
			}
		}
		for (int i = 1; i <= N; i++) {
			System.out.println(res[i]);
		}

		return;
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		documents = new Doc[N + 1];
		printers = new Printer[M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			documents[i] = new Doc(a, b, i); // 출력요청시간, 출력에 걸리는 시간, 문서번호
		}
	}

}