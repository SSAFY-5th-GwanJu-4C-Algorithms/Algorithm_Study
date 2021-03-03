package fourthweek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_14889 {
	
	static int N;
	static int[][] arr;
	static boolean[] visit;
	
	static int Min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		visit = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		combi(0,0);
		System.out.println(Min);
		
	}

	public static void combi(int count, int start) {
		// 조합으로 한 팀을 결정한다
		if(count == N/2) {
			getDiff();
			return;
		}
		
		for(int i = start; i < N; i++) {
			
			if(!visit[i]) {
				visit[i] = true;
				combi(count+1, i+1);
				visit[i] = false;
			}
		}
		
	}

	public static void getDiff() {
		
		int team_start = 0;
		int team_link = 0;
		
		for(int i = 0; i < N -1; i++) {
			for(int j = i+1; j < N; j++) {
				
				// 조합에서 뽑힌 팀은 스타트 팀으로 
				if(visit[i] == true && visit[j] == true) {
					team_start += arr[i][j];
					team_start += arr[j][i];
				}
				
				// 조합에서 안 뽑힌 팀은 링크팀으로 넣어준다.
				else if (visit[i] == false && visit[j] == false){
					team_link += arr[i][j];
					team_link += arr[j][i];
					
					
				}
			} // for j
			
		} //for i
		
		// 차이점수
		int val = Math.abs(team_start - team_link);
		
		if(val == 0) {
			System.out.println(val);
			System.exit(0); // 프로그램 정상 종료
		}
		
		// 최소값을 구한다.
		Min = Math.min(val, Min);
		

	}

}
