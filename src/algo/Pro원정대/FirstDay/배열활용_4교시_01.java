package algo.Pro원정대.FirstDay;

public class 배열활용_4교시_01 {

	public static void main(String[] args) {
		int[] arr1 = new int[] {1,3,2,7,1};
		int[] arr2 = new int[] {1,3,2,8,1};
		
		if(isSame(arr1, arr2)) {
			System.out.println("동일");
		}else {
			System.out.println("다름");
		}
	}

	private static boolean isSame(int[] arr1, int[] arr2) {
		for(int i=0; i<arr1.length; i++) {
			if(arr1[i] != arr2[i])
			{
				return false;
			}
		}
		return true;
	}

}
