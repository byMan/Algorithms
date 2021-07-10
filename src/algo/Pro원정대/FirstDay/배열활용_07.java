package algo.Pro원정대.FirstDay;

public class 배열활용_07 {

	static int[] arr = new int[] {3,2,1,5,10,7,9};
	static int[] check = new int[11];
	
	public static void main(String[] args) {
		
		System.out.println(isDuplicate());
	}

	
	private static String isDuplicate() {
		for(int i=0; i<arr.length; i++) {
			if(check[arr[i]] == 0) {
				check[arr[i]] = 1;
			}else {
				return "중복";
			}
		}
		return "중복 아님";
	}
}
