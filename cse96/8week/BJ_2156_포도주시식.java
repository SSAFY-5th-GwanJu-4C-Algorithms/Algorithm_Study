package study.April.fweek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_2156_포도주시식 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine()); //포도주 잔의 개수
		int glass[] = new int[10001];
		int dp[] = new int[10001];
		
		for(int i = 1; i <= N; i++) {
			glass[i] = Integer.parseInt(bf.readLine());
		}
		
		dp[0] = glass[0];
		dp[1] = glass[0] + glass[1];
		dp[2] = glass[2] + Math.max(glass[0], glass[1]);
		dp[3] = glass[3] + Math.max(dp[1], glass[2] + dp[0]);
		for(int i = 4; i <= N; i++) {
			dp[i] = glass[i] + Math.max(Math.max(dp[i-2], glass[i-1] + dp[i-3]),glass[i-1] + dp[i-4]);
		}
		
		Arrays.sort(dp);
		System.out.println(dp[10000]);
	}

}
