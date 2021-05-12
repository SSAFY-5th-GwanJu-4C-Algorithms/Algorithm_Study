package study.April.sweek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2529_부등호 {
	static int k, result[];
	static long min = Long.MAX_VALUE, max = 0;
	static String minv;
	static ArrayList<Character> ineqs;
	static boolean[] choice;
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(bf.readLine()); // k 2~9 부등호 개수
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine()," ");
		ineqs = new ArrayList<Character>();
		result = new int[k+1];
		choice = new boolean[10]; //0~9
		for(int i = 0; i < k; i++) {
			ineqs.add(st.nextToken().charAt(0)); // string을 char로 어떻게..?
		}//입력 끝
		
		dfs(0);
		
		System.out.println(max);
		System.out.println(minv);
		return;
	}
	
	private static void dfs(int cnt) {
		//기저조건
		if(cnt == k + 1) { //조건에 맞게 3개 선택되면
			sb.setLength(0);
			for(int i = 0; i < k+1; i++) {
				if(result[i] == 0) sb.append(0);
				else sb.append(result[i]);
			}
			long tmp = Long.parseLong(sb.toString());
			if(max < tmp) max = tmp;
			if(min > tmp) {
				min = tmp;
				minv = sb.toString();
			}
			return;
		}
		
		for(int i = 0; i < 10; i++) {
			if(!choice[i]) {
				choice[i] = true;
				if(cnt == 0) {
					result[cnt] = i;
					dfs(cnt + 1);
				}
				else {//1개 이상 선택됨
					char c = ineqs.get(cnt - 1);
					if(c == '<' && result[cnt-1] < i) {
						result[cnt] = i;
						dfs(cnt + 1);
						
					}
					else if(c == '>' && result[cnt-1] > i) {
						result[cnt] = i;
						dfs(cnt + 1);
					}
				}
				choice[i] = false;
			}
			
		}
	}
}
