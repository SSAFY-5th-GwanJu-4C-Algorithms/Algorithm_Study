package SeventhWeek;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baekjoon_S1_2156_포도주시식 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[10001];
		int[][] DP = new int[10001][3];
		
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		} // 데이터 입력
		
		
		// 포도주를 연속 세잔을 마실 수 없음 
		//-> 이전 2잔 연속 마심 -> 지금 못 마심
		//-> 직전에 안 마심 -> 지금 마시거나 안 마심
		//-> 직전 한잔 마심 -> 지금 마시거나 안 마심
		
		DP[1][0] = 0;
		DP[1][1] = arr[1];
		DP[1][2] = arr[1];
		for(int i = 2; i <= N; i++) {
			DP[i][0] = Math.max(DP[i-1][2], Math.max(DP[i-1][1], DP[i-1][0]));
			DP[i][1] = DP[i-1][0] + arr[i];
			DP[i][2] = DP[i-1][1] + arr[i];
		}

		System.out.println(Math.max(DP[N][0], Math.max(DP[N][1], DP[N][2])));
	}

}
