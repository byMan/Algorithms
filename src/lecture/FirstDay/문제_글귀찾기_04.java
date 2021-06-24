package lecture.FirstDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문제_글귀찾기_04 {
	static int de ; 
    static BufferedReader br = 
            new BufferedReader(new InputStreamReader(System.in));
    static char book[][];
    static int N,M; 
    static char pattern[];
    static int pn; 

    static int isSame(int y, int x, int pn) { 
        for(int i = 0 ; i < pn ; i ++) { 
            // book[y][x] vs pattern[i]
            if(y >= N) return 0 ; 
            if(book[y][x] != pattern[i]) return 0 ; 
            x ++; 
            if(x >= M) { 
                y ++; 
                x = 0 ; 
            }
        }
        return 1; 
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        book = new char[N][M];
        for(int y = 0 ; y < N; y ++) { 
            book[y] = br.readLine().toCharArray();
        }
        pn = Integer.parseInt(br.readLine());
        pattern = new char[pn]; 
        String str = br.readLine();
        for(int i = 0 ; i < pn; i ++) { 
            pattern[i] = str.charAt(i); 
        }

        for(int y = 0;y < N; y ++) { 
            for(int x= 0 ; x < M; x++) { 
                int ret = isSame(y,x,pn); 
                if(ret == 1) { 
                    System.out.println("(" + y + "," + x +")");
                }
            }
        }
    }
}
