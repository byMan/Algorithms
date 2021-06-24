package lecture.SecondDay;

import java.util.Arrays;
import java.util.Collections;

public class 정렬활용_01 {

	static int de;
	
	public static void main(String[] args) {
		int[] vect = new int[] {5,2,3,6,8,1,9};
		Arrays.sort(vect);
		de = -1;
		
		Integer[] vect2 = {5,2,3,6,8,1,9};
		Arrays.sort(vect2, Collections.reverseOrder());
		de = -1;
	}

}
