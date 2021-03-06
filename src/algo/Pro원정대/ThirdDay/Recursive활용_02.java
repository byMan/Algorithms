package algo.Pro원정대.ThirdDay;

public class Recursive활용_02 {

	static int de;
	public static void main(String[] args) {
		run(0);
	}

	private static void run(int n) {
		if(n == 3) {
			return;
		}
		
		run(n+1);	//왼쪽
		run(n+1);	//가운데
		run(n+1);	//오른쪽
		de = -1;
	}
}

/*

자식을 3개씩 가진 트리로 3Level까지 검사 후 종료되는 구조다.
동작 순서는 

1. 왼쪽자식 -> 왼쪽자식 -> 왼쪽자식
	               -> 가운데자식
	               -> 오른쪽자식
	     -> 가운데자식 -> 왼쪽자식
	               -> 가운데자식
	               -> 오른쪽자식
	     -> 오른쪽자식 -> 왼쪽자식
	                -> 가운데자식
	                -> 오른쪽자식
	                
   가운데자식  -> 왼쪽자식 -> 왼쪽자식
	               -> 가운데자식
	               -> 오른쪽자식
	     -> 가운데자식 -> 왼쪽자식
	               -> 가운데자식
	               -> 오른쪽자식
	     -> 오른쪽자식 -> 왼쪽자식
	                -> 가운데자식
	                -> 오른쪽자식

   오른쪽자식 -> 왼쪽자식 -> 왼쪽자식
	               -> 가운데자식
	               -> 오른쪽자식
	     -> 가운데자식 -> 왼쪽자식
	               -> 가운데자식
	               -> 오른쪽자식
	     -> 오른쪽자식 -> 왼쪽자식
	                -> 가운데자식
	                -> 오른쪽자식
*/