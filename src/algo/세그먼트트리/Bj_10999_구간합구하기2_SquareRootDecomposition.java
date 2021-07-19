package algo.세그먼트트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_10999_구간합구하기2_SquareRootDecomposition {
    static int n, m , k;
    static int blockCount;
    static int blockSize;
    static long[] blockSum;
    static long[] valList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        blockSize = (int)Math.sqrt(n);
        blockCount = (n + blockSize - 1) / blockSize;
        blockSum = new long[blockCount];
        valList = new long[n];

        for(int i=0; i<n; i++){
            int val = Integer.parseInt(br.readLine());
            blockSum[i/blockSize] += val;
        }
    }

    private static void update(int i, int val){
        blockSum[i/blockSize] += val - valList[i];
        valList[i] = val;
    }

    private static long sumRange(int i, int j){
        int blockL = i / blockSize;
        int blockR = j / blockSize;

        long res = 0;
        if(blockL == blockR){
            while(i <= j){
                res += valList[i++];
            }
        }else{
            blockL++;
            while(i < blockL * blockSize)
                res += valList[i++];
            while(blockL < blockR)
                res += blockSum[blockL++];
            for(int k=blockR * blockSize; k<=j; k++)
                res += valList[k];
        }
        return res;
    }
}
