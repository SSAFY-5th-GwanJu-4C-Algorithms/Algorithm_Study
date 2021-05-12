package study.April.sweek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_2667_단지번호붙이기 {
	static int N,map[][],dx[] = {-1,1,0,0},dy[] = {0,0,-1,1}, no = 0;
	static boolean[][] visit;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		Queue<House> q = new LinkedList<House>();
		Queue<House> q2 = new LinkedList<House>();
		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			String s = bf.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j) - '0';
				if(map[i][j] == 1) map[i][j] = -1;
			}
		}//입력 끝
		visit = new boolean[N][N];
		q.add(new House(0,0));
		//visit[0][0] = true;
		while(!q.isEmpty()) {
			House h = q.poll();
			int x = h.getX();
			int y = h.getY();
			for(int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N || visit[nx][ny]) continue;
				if(map[nx][ny] == 0) {
					q.add(new House(nx,ny));
					visit[nx][ny] = true;
				}
				else if(map[nx][ny] == -1) {
					q.add(new House(nx,ny));
					q2.add(new House(nx,ny));
					visit[nx][ny] = true;
				}
			}
		}
		for(int i = 0; i < N; i++) {
			Arrays.fill(visit[i], false);
		}
		int size = q2.size();
		for(int i = 0; i < size; i++) {
			House h = q2.poll();
			int x = h.getX();
			int y = h.getY();
			if(visit[x][y]) continue;
			no++;
			dfs(x,y,no);
		}
		
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//			
//		}
		
		System.out.println(no);
		int result[] = new int[no+1];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 0) continue;
				else {
					result[map[i][j]]++;
				}
			}
		}
		Arrays.sort(result);
		for(int i = 1; i <= no; i++) {
			System.out.println(result[i]);
		}
		
		
		
	}

	private static void dfs(int x, int y, int no) {
		visit[x][y] = true;
		map[x][y] = no;
		for(int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if(nx < 0 || ny < 0 || nx >= N || ny >= N || visit[nx][ny]) continue;
			if(map[nx][ny] == -1) {
				dfs(nx,ny,no);
				
			}
		}
	}

	static class House{
		int x;
		int y;
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		public House(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
}
