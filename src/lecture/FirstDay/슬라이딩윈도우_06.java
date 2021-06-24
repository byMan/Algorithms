package lecture.FirstDay;

public class 슬라이딩윈도우_06 {

	static int[] arr = new int[] { 2,5,2,6,5,-3,9,4,2,-7 };
	public static void main(String[] args) {
		//이중for문으로 O(N^2)
		구간합구하기();
		System.out.println();
		
		//단일for문으로 O(N)
		slidingWindow();
	}

	private static void slidingWindow() {
		//구간의 크기 = 5;
		int w = 5;
		//첫 구간의 합 구하기
		int sum = 0;
		for(int i=0; i<5; i++) {
			sum += arr[i];
		}
		
		System.out.println(sum);
		//슬라이딩 윈도우
		for(int s=1; s + w - 1 < arr.length; s++) {
			//다음구간
			sum -= arr[s-1];
			sum += arr[s+w-1];	//구간의 마지막 값
			System.out.println(sum);
		}
	}

	private static void 구간합구하기() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<=arr.length-5; i++) {
			int sum = 0;
			sb = new StringBuilder();
			for(int j=i; j<i+5; j++) {
				sb.append(arr[j]).append(" ");
				sum += arr[j];
			}
			System.out.println(sb.toString() + " " + sum);
		}
	}

}
