import java.io.*;

public class Test {
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter("최대부분수열합_testCase.txt"));
        bw.write(1+"\n");
        bw.write(300000 + " " + 100000 + "\n");
        for(int i=1; i<=300000; i++){
//            if(i%2==0)
                bw.write(1 + " ");
//            else
//                bw.write(-1 + " ");
        }
        bw.write("\n");
        for(int i=1; i<=25000; i++){
            bw.write(1 + " " + 299999 + "\n");
        }
        for(int i=1; i<=25000; i++){
            bw.write(2 + " " + 300000 + "\n");
        }
        for(int i=1; i<=25000; i++){
            bw.write(2 + " " + 299999 + "\n");
        }
        for(int i=1; i<=25000; i++){
            bw.write(1 + " " + 300000 + "\n");
        }
        bw.flush();
        bw.close();
    }
}