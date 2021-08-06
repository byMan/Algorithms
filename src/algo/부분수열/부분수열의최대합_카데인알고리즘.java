package algo.부분수열;

public class 부분수열의최대합_카데인알고리즘 {
    static int[] arr = {3, -5, 4, -2, 5, 3, -1, 1};

    public static void main(String[] args) {
        System.out.println(kadanesAlgorith(0, arr.length));
        System.out.println(kadanesAlgorith(0, 5));
    }

    private static int kadanesAlgorith(int start, int end){
        int cursum = 0;
        int maxsum = 0;
        for(int i=start; i<end; i++){
            cursum = Math.max(cursum + arr[i], arr[i]);
            maxsum = Math.max(maxsum, cursum);
        }
        return maxsum;
    }
}
