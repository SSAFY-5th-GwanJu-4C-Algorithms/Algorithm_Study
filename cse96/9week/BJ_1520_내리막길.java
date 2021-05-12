package study.April.sweek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1520_내리막길 {
	static int N, M, dp[][], map[][], dx[] = {-1,1,0,0}, dy[] = {0,0,-1,1};
	static boolean[][] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dp = new int[N][M];
		map = new int[N][M];
		visit = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine()," ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}//입력 끝
		System.out.println(dfs(0,0));
		
		
	}
	private static int dfs(int x, int y) {
		if(x == N-1 && y == M-1) {
			return 1;
		}
		
		if(dp[x][y] == -1) {
			dp[x][y] = 0;
			for(int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if(map[x][y] > map[nx][ny]) {
					dp[x][y] += dfs(nx,ny);
				}
			}
		}
		
		return dp[x][y];
	}
}
