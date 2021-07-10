package algo.Pro원정대.ThirdDay;

public class Recursive활용_01 {

	public static void main(String[] args) {
		test(0);
	}
	
	private static void test(int n) {
		if(n==3) {
			return;
		}
		
		//0 1 2 출력
		System.out.print(n + " ");
		
		test(n+1);
		
		//2 1 0 출력
		System.out.print(n + " ");
	}

}
