package algo.Pro원정대.FirstDay;

public class 배열활용_06 {

	public static void main(String[] args) {
		int[] arr = new int[] {3,2,1,5,3,1,2};
		int[] dap = new int[8];
		
		for(int i=0; i<arr.length; i++) {
			dap[arr[i]]++;
		}
		
		for(int i=1; i<dap.length; i++) {
			if(dap[i] > 0) {
				System.out.println(i + " : " + dap[i] + "개");
			}
		}
	}

}
