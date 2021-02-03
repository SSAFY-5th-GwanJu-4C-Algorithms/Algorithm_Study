package first.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Problem_10814 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		
		String[][] arr = new String[N][2];
		
		for(int i = 0; i < N; i++) {
			arr[i][0] = scanner.next();
			arr[i][1] = scanner.next();
 		}
		
		
		Arrays.sort(arr, new Comparator<String[]>() {
			
			@Override
			public int compare(String[]o1, String[]o2) {
				
				//if(o1[0].equals(o2[0])) return 
				
				return Integer.valueOf(o1[0])- Integer.valueOf(o2[0]);
				
			}
		});
		
		for(int i = 0; i < N; i++) {
			System.out.println(arr[i][0] + " " + arr[i][1]);
		}

	}

}
