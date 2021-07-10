package algo.Pro원정대.FourthDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문제_인디언합창단_06 {
	static int n, de;
	static int groupCnt;		//생성된 그룹 수 체크용
	static int personCnt = 26;	//그룹멤버를 제외하고 남은 멤버 수
	static int[] memCnt = new int[100];
	static char[] parent = new char[100];
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		for(char ch='A'; ch<='Z'; ch++) {
			parent[ch] = ch;
			memCnt[ch] = 1;
		}
		
		n = Integer.parseInt(br.readLine());
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			char ch1 = st.nextToken().charAt(0);
			char ch2 = st.nextToken().charAt(0);
			union(ch1, ch2);
		}
		
		System.out.println(groupCnt);
		System.out.println(personCnt);
	}

	
	private static char find(char ch) {
		//최종부모 = 그룹의 대표
		if(ch == parent[ch]) return ch;
		
		char ret = find(parent[ch]);
		
		//경로 압축
		parent[ch] = ret;
		return ret; 
	}
	
	
	/**
	 * 서로 다른 그룹을 하나로 합침
	 * pa(ch1 해당 그룹 대표) <- pb (ch2 해당 그룹 대표)
	 * @param ch1
	 * @param ch2
	 */
	private static void union(char ch1, char ch2) {
		char pa = find(ch1);
		char pb = find(ch2);
		if(pa != pb) {
			parent[pb] = pa;
			
			//둘다 개인이 그룹을 처음 만드는 경우
			if(memCnt[pa] == 1 && memCnt[pb] == 1) {
				//pa 개인, pb 개인
				groupCnt++;
				personCnt -= 2;
				memCnt[pa] += memCnt[pb];
				memCnt[pb] = 0;
			
			//pb 그룹이 pa 개인으로 속하여 그룹이 되는 경우 
			}else if(memCnt[pa] == 1 && memCnt[pb] >= 2){
				//pa는 개인, pb는 기존 그룹
				personCnt--;
				memCnt[pa] += memCnt[pb];
				memCnt[pb] = 0;
			
			//pa 그룹에 pb 개인이 그룹으로 포함되는 경우
			}else if(memCnt[pa] >= 2 && memCnt[pb] == 1) {
				//pa는 기존 그롭, pb는 개인
				personCnt--;
				memCnt[pa] += memCnt[pb];
				memCnt[pb] = 0;
				
			//pa 그룹에 pb 그룹이 포함되는 경우
			}else if(memCnt[pa] >= 2 && memCnt[pb] >= 2) {
				//pa도 기존 그룹, pb도 기존 그룹
				groupCnt--;
				memCnt[pa] += memCnt[pb];
				memCnt[pb] = 0;
			}
		}
	}
}
