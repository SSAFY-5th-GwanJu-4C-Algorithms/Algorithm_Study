package thirdweek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 색종이
// 2차원 배열, 카운팅
public class baekjoon_10163_sol {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine()); // 1 <= N <= 100
		
		int[][] m = new int[101][101]; // 색종이 평면
		
		for(int i = 1; i <= N; i++) { // 색종이가 순서대로 들어왕
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int c = Integer.parseInt(st.nextToken()); // 시작 열
			int r = Integer.parseInt(st.nextToken()); // 시작 행
			int w = Integer.parseInt(st.nextToken()); // 너비
			int h = Integer.parseInt(st.nextToken()); // 높이
			
			for(int j = r; j < r + h; j++) { // 행
				for(int k = c; k < c + w; k++) { // 열
					m[j][k] = i; // 색종이 번호를 저장
				}
				
			}

		}
		
//		for(int i = 0; i < m.length; i++) {
//			System.out.println(Arrays.toString(m[i]));
//		}
		
		int[] cnt = new int[N + 1]; // 색종이 보이는 칸수 카운팅, 0번 칸은 안 씀
		for(int i = 0; i < m.length; i++) {
			for(int j = 0; j < m.length; j++) {
				cnt[m[i][j]]++;
			}
		}
		
		System.out.println(Arrays.toString(cnt));
		
		for(int i = 1; i < cnt.length; i++) {
			System.out.println(cnt[i]);
		}

	}

}
