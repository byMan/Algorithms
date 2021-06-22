package algo.비트마스크;

import java.io.*;
import java.util.StringTokenizer;

public class Bj_11723_집합 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 출력을 위한 객체
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int bitset = 0;

        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            int num;

            // 수행연산
            switch (op) {
                case "add":
                    num = Integer.parseInt(st.nextToken());
                    // num - 1인 이유는 20이 들어왔을 때
                    // 2^19 자리가 20번째이기 때문이다.
                    bitset |= (1 << (num - 1));
                    break;
                case "remove":
                    num = Integer.parseInt(st.nextToken());
                    bitset = bitset & ~(1 << (num - 1));
                    break;
                case "check":
                    num = Integer.parseInt(st.nextToken());
                    sb.append((bitset & (1 << (num - 1))) != 0 ? "1\n" : "0\n");
                    break;
                case "toggle":
                    num = Integer.parseInt(st.nextToken());
                    bitset ^= (1 << (num - 1));
                    break;
                case "all":
                    bitset |= (~0);
                    break;
                case "empty":
                    bitset &= 0;
                    break;
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}

/*
[입력]
26
add 1
add 2
check 1
check 2
check 3
remove 2
check 1
check 2
toggle 3
check 1
check 2
check 3
check 4
all
check 10
check 20
toggle 10
remove 20
check 10
check 20
empty
check 1
toggle 1
check 1
toggle 1
check 1

[출력]
1
1
0
1
0
1
0
1
0
1
1
0
0
0
1
0


 */