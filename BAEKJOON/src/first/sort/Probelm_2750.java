package first.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Probelm_2750 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		
		StringBuilder sb = new StringBuilder();
		
		//Arrays.sort(nums); // 평균 시간복잡도가 O(nlogn)이지만 최악의 경우 O(n^2)임
		
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0; i < N; i++) list.add(scanner.nextInt());
		
		//Collections.sort()는 Timesort로 반복 합병 + 삽입정렬 알고리즘
		//합병정렬의 경우 최선, 최악 모두 O(nlogn)을 보장, 삽입정렬의 경우 최선은 O(n), 최악은 O(n^2)
		Collections.sort(list);
		
		for(int l : list) sb.append(l).append('\n');
		System.out.println(sb);
		
		scanner.close();

	}

}
