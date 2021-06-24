package lecture.SecondDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 문제_금나와라황금보자기 {

	static class Node implements Comparable<Node>{
		int n;
		int dol;		//0이면 황금, 1이면 돌
		public Node(int n, int dol) {
			this.n = n;
			this.dol = dol;
		}
		@Override
		public int compareTo(Node o) {
			//오름차순 정렬
			if(this.n < o.n) return -1;
			if(this.n > o.n) return 1;
			
			//황금이 먼저 나오도록..
			if(this.dol < o.dol) return -1;
			if(this.dol > o.dol) return 1;
			return 0;
		}
	}
	
	
	static int N;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int val = Integer.parseInt(st.nextToken());
			q.add(new Node(val, 0));
		}
		
		int cnt = 0;
		while(true) {
			Node node1 = q.poll();
			Node node2 = q.poll();
			
			if(node1.dol == 0) {
				cnt++;
			} else {
				break;
			}
			
			if(node2.dol == 0) {
				cnt++;
			}else {
				break;
			}
			
			q.add(new Node(node2.n * 2, 1));
		}
		
		System.out.println(cnt);
	}

}


/*

도깨비의 보자기에는 n개 황금이 들어있습니다.
이 보자기는 한번 꺼낼 때가장 가벼운 물건만 꺼내지는마법의 보자기입니다.
보자기에 있는 황금을 꺼내면 보자기의 무게는 점점 가벼워집니다.
도깨비에게 들키지 않도록, 황금을 2개씩 꺼낼 때 마다 무거운 짱돌 1개를 넣어두려고 합니다.
00e90049d4a1f3c2f2d30189b61a26e2.png
황금의 개수 n과
보자기에 들어있는 황금의 무게들을 입력 받습니다.
보자기에서 2 개의 내용물을 꺼냅니다. 그리고 마지막으로 꺼낸 황금의 2 배 무게를 가진 짱돌 1개 넣습니다.
꺼내어진 돌이 황금이 아닐 때까지위 동작을 반복 합니다.
[예시]
보자기에 1 3 3 4 9 무게의 황금이 보자기에 들어 있습니다.
1회 시도 : 보자기에서 가장 가벼운 돌 2개를 뺀 후 (황금 1, 3), 마지막으로 뺀 황금의 2배 더 무거운 짱돌 (<6>)을 넣는다. -----> 3, 4,<6>, 9
2회 시도 : 보자기에서 가장 가벼운 돌 2개를 뺀 후 (황금 3, 4), 마지막으로 뺀 황금의 2배 더 무거운 짱돌 (<8>)을 넣는다. -----><6>, <8>,9
3회 시도 : 보자기에서 가장 가벼운 돌 2개를 뺐는데, 짱돌<6>이 꺼내졌기에, 이러한 행동을 그만 둔다.
도깨비보자기에서 꺼내어진 황금의 개수를 출력 해 주세요.
위 예시에서는 총 4개의 황금을 얻을 수 있기에, 출력결과는 4 입니다.
[세부사항]
1.황금과 짱돌이 같은 무게일때는,황금이 먼저나와야 합니다.
2. 꺼낸 돌이 짱돌일 경우, 즉시 꺼내는 작업을 중단합니다.

[입력]
5
1 3 3 4 9

[출력]
4


*/