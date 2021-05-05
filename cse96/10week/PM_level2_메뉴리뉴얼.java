package study.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PM_level2_메뉴리뉴얼 {

	static StringBuilder sb;
	static String[] orders;
	static ArrayList<String> answer = new ArrayList<String>();
	static int index = 0;
	static ArrayList<String> l = new ArrayList<String>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.valueOf(bf.readLine());
		orders = new String[N];
		for(int i = 0; i < N; i++) {
			orders[i] = bf.readLine();
		}
		int[] course = new int[3];
		for(int i = 0; i < 3; i++) {
			course[i] = Integer.valueOf(bf.readLine());
		}
		
		sb = new StringBuilder();
		
		int size = orders.length;
		for(int i = 0; i < size; i++) {
			String tmp = orders[i];
			int len = tmp.length();
			for(int j = 0; j < len; j++) {
				String s = tmp.substring(j,j+1);
				String comp = sb.toString();
				if(!comp.contains(s)) {
					sb.append(s);
				}
			}
		}
		//orders: String, course: int

		char[] comp = sb.toString().toCharArray();//비교용 String생성
		int comp_size = comp.length;
		boolean[] choice = new boolean[comp_size];//비교용 String

		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < size; j++) {
				combination(0, 0, course[i], comp_size ,comp,choice);
			}
		}
		
		size = answer.size();
		int[] remove = new int[size];
		for(int i = size-1; i >= 0; i--) {
			String s = answer.get(i);
			for(int j = 0; j < i; j++) {
				String s2 = answer.get(j);
				if(s.contains(s2)) remove[j]++;
			}
		}
		for(int i = 0; i < size; i++) {
			if(remove[i] != 0) answer.remove(i);
			size=answer.size();
		}
		System.out.println(answer.toString());
		
	}
	//start:시작, cnt: 몇개 선택됬는지, N:고를메뉴 수, target:for문 끝을 위한 변수, boolean 배열
	private static void combination(int start, int cnt, int N, int comp_size, char[] comp, boolean[] choice) {
		if(cnt == N) {
			StringBuilder sb = new StringBuilder();
			char[] a = new char[N];
			int idx = 0;
			for(int i = 0; i < comp_size; i++) {
				if(choice[i]) {
					a[idx++] = comp[i];
				}
			}
			Arrays.sort(a);
			for(int i = 0; i < a.length; i++) {
				sb.append(a[i]);
				//System.out.print(a[i]);
			}
			String s = sb.toString();
			if(l.contains(s)) return;
			l.add(s);
			int count = 0;
			for(int i = 0; i < orders.length; i++) {
				if(orders[i].contains(s)) {
					count++;
				}
			}
			if(count > 1) {
				if(count > index) {
					answer.clear();
					index = count;
				}
				index = Math.max(count, idx);
				answer.add(s);
				System.out.println(s);
			}
			//orders[i]에 포함되면 answer배열에 저장
			
			return;
		}//기저 조건
		
		for(int i = start; i < comp_size; i++) {
			choice[i] = true;
			combination(i+1,cnt+1,N,comp_size,comp,choice);
			choice[i] = false;
		}
	}
}
