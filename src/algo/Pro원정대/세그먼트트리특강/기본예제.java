package algo.Pro원정대.세그먼트트리특강;

public class 기본예제 {
    static int tree[];
    static int arr[] = {1,2,3,4,5,6,7,8,9,0};

    /**
     * 세그먼트트리 tree 배열 구성하기
     * @param node
     * @param start
     * @param end
     */
    static void init(int node, int start, int end){
        if(start == end){
            tree[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        init(node*2, start, mid);
        init(node * 2 + 1, mid + 1, end);

        tree[node] = tree[node*2] + tree[node*2+1];
//        최소값, 최대값인경우
//        tree[node] = Math.min(tree[node*2], tree[node*2+1]);
//        tree[node] = Math.max(tree[node*2], tree[node*2+1]);
    }


    /**
     * 구간의 합을 구하는 query
     * @param node
     * @param start
     * @param end
     * @param ts : 구하고자 하는 구간의 시작 index
     * @param te : 구하고자 하는 구간의 끝 index
     * @return
     */
    static int query(int node, int start, int end, int ts, int te){
        //구하고자 하는 구간이 아닌 경우
        if(start > te || end < ts){
            return 0;
        }

        if(start >= ts && end <= te){
            return tree[node];
        }

        int mid = (start + end) / 2;
        int left = query(node * 2, start, mid, ts, te);
        int right = query(node * 2 + 1, mid + 1, end, ts, te);

        return left + right;
    }


    /**
     * 특정 인덱스의 값을 변경하고자 할 경우
     * @param node
     * @param start
     * @param end
     * @param targetIndex : 변경하고자 하는 위치의 index 값
     * @param targetVlues : 변경하고자 하는 값
     */
    static void update(int node, int start, int end, int targetIndex, int targetVlues){
        //변경하고자 하는 targetIndex가 아닌 경우는 제외
        if(start > targetIndex || end < targetIndex){
            return;
        }

        //targetIndex 위치인 경우 값의 갱신 처리를 진행한다.
        if(start == end){
            tree[node] = targetVlues;
            return;
        }

        int mid = (start + end) / 2;
        update(node * 2, start, mid, targetIndex, targetVlues);
        update(node * 2 + 1, mid + 1, end, targetIndex, targetVlues);

        //변경된 값을 기준으로 해서 부모 노드의 결과 값을 갱신해 나간다.
        tree[node] = tree[node*2] + tree[node*2+1];
    }


    public static void main(String[] args) throws Exception {
        tree = new int[10 * 5];
        init(1, 0, 9);
        System.out.println(query(1, 0, 9, 3, 7));
        System.out.println(query(1, 0, 9, 0, 9));

        //특정 위치인 인덱스 9번 위치의 값을 10으로 변경하여 계산한다.
        arr[9] = 10;
        update(1, 0, 9, 9, 10);
        System.out.println(query(1, 0, 9, 0, 9));

        int de = -1;
    }
}
