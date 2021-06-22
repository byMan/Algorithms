package 기출;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MinMax_0326 {

    static long INF = Long.MAX_VALUE;
    static int MAX = 100001;
    static int SEGMAX = 1<<18; //세그먼트 트리의 최대 크기
    static Node0326[] tree;
    static Node0326[] sortedArr;
    static int[] cnt = new int[MAX];
    static int[] arr = new int[MAX];
    static int T, N, K, res;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("D:\\SW 검정 Pro\\기출문제\\0326 수열(세그먼트트리_이분탐색)\\input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int k=1; k<=T; k++){
            tree = new Node0326[SEGMAX];
            for(int i=0; i<SEGMAX; i++){
                tree[i] = new Node0326(Integer.MIN_VALUE,Integer.MAX_VALUE);
            }

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            sortedArr = new Node0326[N+1];
            sortedArr[0] = new Node0326(Integer.MAX_VALUE, Integer.MAX_VALUE);
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++){
                arr[i] = Integer.parseInt(st.nextToken());
                sortedArr[i] = new Node0326(arr[i], i);
            }
            res = MAX;

            setTree(1, 1, N);

            solve();

            if(res == Integer.MAX_VALUE){
                System.out.println("#" + k + " -1");
            }else{
                System.out.println("#" + k + " " + res + " " + cnt[res]);
            }
        }
    }


    private static void solve() {
        //arr을 오름차순으로 정렬
        //sortedArr.nodeMax 는 값, sortedArr.nodeMin 은 정렬전 인덱스 값
        Arrays.sort(sortedArr);
        int start, end, mid;
        Node0326 val;
        for(int i=1; i<N; i++){
            //이분탐색으로 차이가 K인 원소 2개를 찾는다.
            start = i;
            end = N;
            while(start <= end){
                mid = (start + end) / 2;
                if(sortedArr[mid].nodeMax - sortedArr[i].nodeMax == K){
                    //차이가 K인 값이 있는 인덱스
                    long sidx, eidx, length;
                    sidx = sortedArr[i].nodeMin;
                    eidx = sortedArr[mid].nodeMin;
                    length = Math.abs(sidx - eidx) + 1;
                    if(sidx < eidx){
                        val = getMinMax(1, 1, N, sidx, eidx);
                    }else{
                        val = getMinMax(1, 1, N, eidx, sidx);
                    }

                    //차이가 K인 값이 있는 인덱스를 찾았지만, 해당 인덱스 사이에 더 큰 최대값이나
                    //더 작은 최소값이 존재할 수 있기 때문에 해당 구간의 차이가 diff가 맞는지 검사한다.
                    long diff = val.nodeMax = val.nodeMin;
                    if(diff == K){
                        cnt[(int)length] += 1;
                        res = Math.min(res, (int)length);
                    }
                    break;
                }else if((sortedArr[mid].nodeMax - sortedArr[i].nodeMax) > K){
                    end = mid - 1;
                }else{
                    start = mid + 1;
                }
            }
        }
    }



    private static Node0326 setTree(int node, int start, int end){
        if(start == end){
            tree[node].nodeMax = arr[start];    //최대값
            tree[node].nodeMin = arr[start];    //최소값
            return tree[node];
        }else{
            int mid = (start + end) / 2;
            Node0326 left = setTree(node*2, start, mid);
            Node0326 right = setTree(node*2+1, mid+1, end);
            tree[node].nodeMax = Math.max(left.nodeMax, right.nodeMax); //최대값
            tree[node].nodeMax = Math.min(left.nodeMin, right.nodeMin); //최소값
            return tree[node];
        }
    }


    private static Node0326 getMinMax(int node, int start, int end, long left, long right){
        if(left > end || right < start){
            return new Node0326(0, INF);
        }
        if(left <= start && end <= right){
            return tree[node];
        }
        int mid = (start + end) / 2;
        Node0326 l = getMinMax(node*2, start, mid, left, right);
        Node0326 r = getMinMax(node*2+1, mid+1, end, left, right);
        return new Node0326(Math.max(l.nodeMax, r.nodeMax), Math.min(l.nodeMin, r.nodeMin));
    }
}

class Node0326 implements Comparable<Node0326>{
    long nodeMax;
    long nodeMin;
    public Node0326(long max, long min){
        this.nodeMax = max;
        this.nodeMin = min;
    }

    @Override
    public int compareTo(Node0326 o) {
        if(this.nodeMax < o.nodeMax){
            return -1;
        }else{
            return 1;
        }
    }
}