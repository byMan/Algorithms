package algo.Pro원정대;

public class CountingSort {
    public static void main(String[] args) throws Exception {
        int[] vect = {5,2,2,1,2};
        int[] bucket = new int[6];
        int[] result = new int[5];

        //1.DAT
        for(int i=0; i<5; i++){
            bucket[vect[i]]++;
        }

        //2.누적합
        for(int i=1; i<6; i++){
            bucket[i] += bucket[i-1];
        }

        //3.결과취합
        for(int i=0; i<5; i++){
            int index = bucket[vect[i]] -1;
            result[index] = vect[i];
            bucket[vect[i]]--;
        }
    }
}

/*
    누적합을 이용하여 정렬하는 알고리즘
    주의점
     1. 음수가 포함되거나 정렬할 값이 너무 크다면, 다른 Sort를 써야함
 */