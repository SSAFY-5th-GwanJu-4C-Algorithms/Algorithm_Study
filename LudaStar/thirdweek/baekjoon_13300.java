package thirdweek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;


public class baekjoon_13300 {
	
	static int[][] student;
	static int N, K;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); // 총 학생 수
		K = Integer.parseInt(st.nextToken()); // 한 방에 배정할 수 있는 최대 인원
		
		student = new int[7][2];
		
		// 데이터 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int sex = Integer.parseInt(st.nextToken());
			int grade = Integer.parseInt(st.nextToken());

			student[grade][sex]++; // 각 반과 성별에 인원을 추가
		}
		
		System.out.println(Arrays.deepToString(student));
		
		System.out.println(getRoom());


	}

	public static int getRoom() {
		int count = 0; // 방 갯수
		
		for(int grade = 1; grade <= 6; grade++) {
			for(int sex = 0; sex <= 1; sex++) {
				int people = student[grade][sex]; // 각 반, 성별에 따른 인원
				
				count += people / K; // 인원수 / 방에 들어갈 수 있는 수
				count += (people % K == 0) ? 0 : 1; // 인원수 %방에 들어갈 수 있는 수
			}
		}
		
		return count;
	}

}
