package lecture.FirstDay;

public class 배열활용_1교시_03 {

	static int[] in = new int[] {3,2,1,5};
	static int[][] arr = new int[][] {
		{3,3,1,2},
		{5,1,2,5},
		{1,2,3,4},
		{5,6,7,8}
	};
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int k=0; k<in.length; k++) {
			int cnt = 0;
			for(int i=0; i<arr.length; i++) {
				for(int j=0; j<arr[i].length; j++) {
					if(in[k] == arr[i][j]) {
						cnt++;
					}
				}
			}
			System.out.println(in[k] + " = " + cnt + "개");
		}
	}

}
