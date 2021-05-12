import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, input[], ans = Integer.MAX_VALUE , map[][];
	static boolean visit[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		input = new int[N/2];
		map = new int[N][N];
		visit = new boolean[N];
		StringTokenizer st;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine()," ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		combination(0,0);
		System.out.println(ans);
	}

	private static void combination(int start, int cnt) {
		if(cnt == N / 2) { //기저조건
			int start_team = 0;
			int link_team = 0;
			for(int i = 0; i < N - 1; i++) {
				for(int j = i + 1; j < N ; j++) {
					if(visit[i] && visit[j]) {
						start_team += map[i][j];
						start_team += map[j][i];
					}
					else if(visit[i] == false && visit[j] == false) {
						link_team += map[i][j];
						link_team += map[j][i];
					}
				}
			}
			int min = Math.abs(link_team - start_team);
			ans = Math.min(min, ans);
			return;
		}
		
		
		for(int i = start; i < N; i++) {
			if(visit[i]) continue;
			input[cnt] = i;
			visit[i] = true;
			combination(i + 1 ,cnt + 1);
			visit[i] = false;
		}
	}


}
