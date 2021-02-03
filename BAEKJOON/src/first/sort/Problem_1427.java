package first.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Problem_1427 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		String N = scanner.next();

		char[] tmp = N.toCharArray();
		Integer[] arr = new Integer[N.length()];
		int temp = 0;
		
		for(char c : tmp)
			arr[temp++] = Character.getNumericValue(c);
		
		Arrays.sort(arr, Collections.reverseOrder());
		
		for(int i : arr)
			System.out.print(i);

	}

}
