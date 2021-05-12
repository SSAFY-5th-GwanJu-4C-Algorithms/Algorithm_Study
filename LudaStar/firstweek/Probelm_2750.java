package firstweek;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Probelm_2750 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		
		StringBuilder sb = new StringBuilder();
		
		//Arrays.sort(nums); // ��� �ð����⵵�� O(nlogn)������ �־��� ��� O(n^2)��
		
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0; i < N; i++) list.add(scanner.nextInt());
		
		//Collections.sort()�� Timesort�� �ݺ� �պ� + �������� �˰���
		//�պ������� ��� �ּ�, �־� ��� O(nlogn)�� ����, ���������� ��� �ּ��� O(n), �־��� O(n^2)
		Collections.sort(list);
		
		for(int l : list) sb.append(l).append('\n');
		System.out.println(sb);
		
		scanner.close();

	}

}
