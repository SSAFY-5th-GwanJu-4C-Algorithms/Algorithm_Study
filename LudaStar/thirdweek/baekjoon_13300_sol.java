package thirdweek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 방배정
// 완전 탐색
public class baekjoon_13300_sol {

	public static void main(String[] args) throws Exception {
		
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 학생수 1 <= N <= 1000
		int K = Integer.parseInt(st.nextToken()); // 한방의 최대 인원수 1 <= K <= 1000
		
		//배열 2차원[성별][학년]
		int[][] room = new int[2][7]; // 남 : 0, 여 : 1 / 1~6
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken()); // 성별
			int y = Integer.parseInt(st.nextToken()); // 학년
			
			room[s][y]++;
		}
		// 배열 확인
//		for(int i = 0; i < room.length; i++) {
//			System.out.println(Arrays.toString(room[i]));
//		}
		
		// 방의 개수 구하기 각 배열 칸의 개수 r, (r-1) / k+1;
		// 필요한 방의 개수를 합산
		int cnt = 0; // 학생들을 배정하기 윙한 총 방의 개수
		for(int i = 0; i < 2; i++) {
			for(int j = 1; j < 7; j++) { // 0학년은 안 씀
				int r = room[i][j]; // 각 반의 각 성별인원
				
				if(r == 0) continue;
				cnt += (r-1) / K + 1;
			}
		}
			
		System.out.println(cnt);

	}

}
