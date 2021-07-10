package algo.Pro원정대.FirstDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문제_슬라이딩윈도우_07 {
	static long max;
	static int start, end;
	static int[] arr;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			slidingWindow(N, W);
			System.out.println("#" + t + " " + start + " " + end + " " + max);
		}
	}

	private static void slidingWindow(int n, int w) {
		long sum = 0;
		
		for(int i=0; i<w; i++) {
			sum += arr[i];
		}
		
		start = 0;
		end = w-1;
		max = sum;
		for(int i=1; i+w-1 < n; i++) {
			sum -= arr[i-1];
			sum += arr[i+w-1];
			if(max < sum) {
				max = sum;
				start = i;
				end = i+w-1;
			}
		}
	}
}
