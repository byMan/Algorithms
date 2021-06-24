package lecture.FirstDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 문제_미확인신호_슬라이딩윈도우_07 {
	
	static char[] arr;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		
		for(int k=1; k<=T; k++) {
			arr = new char[250];
			int w = Integer.parseInt(br.readLine());
			String str = br.readLine();
			boolean isDuplicate = slidingWindow(str, w);
			System.out.println("#" + k + " " + (isDuplicate ? "FAIL" : "PASS"));
		}
	}

	
	private static boolean slidingWindow(String str, int w) {
		//첫구간 체크
		for(int i=0; i<w; i++) {
			char ch = str.charAt(i);
			if(arr[ch] == 0) {
				arr[ch] = 1;
			} else {
				return true;
			}
		}
		
		for(int i=1; i+w-1<str.length(); i++) {
			char preCh = str.charAt(i-1);
			char nextCh = str.charAt(i+w-1);
			
			//첫번째 제거
			arr[preCh] = 0;

			//중복된 값이 존재하는지 체크하고 중복이면 중지
			if(arr[nextCh] == 1) {
				return true;
			}
			
			//다음거 추가
			arr[nextCh] = 1;
			
		}
		
		return false;
	}
}
