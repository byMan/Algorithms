package algo.Pro원정대.DP특강;

public class DP02_피보나치수열_재귀_TopDown {
    //memoization 이라고 합니다.
    //memoization 적용한 Top-Down Code
    static int[] memo = new int[100];

    static int getFibo(int n)
    {
        if (n == 0) return 0;
        if (n == 1) return 1;

        //이미 구한 정답이 있다면, 구한 정답을 바로 리턴
        if (memo[n] != 0) return memo[n];

        int a = getFibo(n - 1);
        int b = getFibo(n - 2);

        //이 부분은
        //fibo(n) 의 정답을 구한 상태 (= a + b)
        memo[n] = a + b;
        return a + b;
    }

    public static void main(String[] args) {

        int ret = getFibo(10);

        System.out.println(ret);
    }
}

/*

DP 문제를 풀 때 두 가지 방식이 있다

for문으로 배열을 채우는 방식인 Bottom-Up 방식,
재귀로 값을 구하는 방식인 Top-Down 방식



 */