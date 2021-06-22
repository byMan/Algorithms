package miracom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Level32_구조체삽입정렬 {
    static int n;
    static List<StructInsert> list = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = parseInt(st.nextToken());

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            list.add(new StructInsert(parseInt(st.nextToken()), st.nextToken()));
        }

        Collections.sort(list, (a, b) -> { return a.compareTo(b); });

        StructInsert temp;
        for(int i=0; i<n-1; i++){
            for(int j=i+1; j<n; j++){
                if(list.get(i).num == list.get(j).num){
                    if(list.get(i).str.charAt(0) > list.get(j).str.charAt(0)){
                        temp = new StructInsert();
                        temp.num = list.get(i).num;
                        temp.str = list.get(i).str;
                        list.get(i).num = list.get(j).num;
                        list.get(i).str = list.get(j).str;
                        list.get(j).num = temp.num;
                        list.get(j).str = temp.str;
                    }
                }
            }
        }

        for(int i=0; i<n; i++){
            System.out.println(list.get(i).num + " " + list.get(i).str);
        }
    }
}
class StructInsert implements Comparable<StructInsert>{
    int num;
    String str;
    public StructInsert(){}
    public StructInsert(int num, String str){
        this.num = num;
        this.str = str;
    }

    @Override
    public int compareTo(StructInsert o) {
        return this.num - o.num;
    }
}
