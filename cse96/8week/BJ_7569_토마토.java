package study.April.fweek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 1: 익은 토마토
 * 0: 익지 않은 토마토
 * -1: 토마토가 없는 칸
 */
public class BJ_7569_토마토 {
	static int maxday =  0 ,M,N,H;
	static int[] dx = {-1,1,0,0} , dy = {0,0,-1,1};
	static int[][][] boxes;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine()," ");
		Queue<tomato> t = new LinkedList<tomato>(); 
		M = Integer.parseInt(st.nextToken());//가로 칸의 수 2~100
		N = Integer.parseInt(st.nextToken());//세로 칸의 수 2~100
		H = Integer.parseInt(st.nextToken());//상자의 수 1~100
		boxes = new int[H][N][M];
		
		boolean flag = false;
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < N; j++) {
				st = new StringTokenizer(bf.readLine()," ");
				for (int k = 0 ; k < M; k++) {
					boxes[i][j][k] = Integer.parseInt(st.nextToken());
					if (boxes[i][j][k] == 1) t.offer(new tomato(i,j,k,0)); //익은 토마토
					if (boxes[i][j][k] == 0) flag = true;
				}
			}
		}
		
		if(!flag) {
			System.out.println(0);
			return;
		}
		
		while(!t.isEmpty()) {
			tomato to = t.poll();
			int h = to.getH();
			int x = to.getX();
			int y = to.getY();
			int day = to.getTime();
			if(day > maxday) maxday = day;
			for(int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if(boxes[h][nx][ny] == 0) {
					boxes[h][nx][ny] = 1;
					t.offer(new tomato(h,nx,ny,day + 1));
				}
			}//4방탐색 끝
			if(h + 1 < H && boxes[h+1][x][y] == 0) {
				boxes[h+1][x][y] = 1;
				t.offer(new tomato(h + 1,x,y,day + 1));
			}
			if(h - 1 >= 0 && boxes[h-1][x][y] == 0) {
				boxes[h-1][x][y] = 1;
				t.offer(new tomato(h - 1,x,y,day + 1));
			}
			
		}
		if(check()) {
			System.out.println(maxday);
		}
		else {
			System.out.println(-1);
		}
		
	}//main
	
	
	private static boolean check() {
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < N; j++) {
				for (int k = 0 ; k < M; k++) {
					if(boxes[i][j][k] == 0) return false;
				}
			}
		}
		return true;
	}

}

class tomato{
	int h;
	int x;
	int y;
	int time;
	public int getH() {
		return h;
	}
	public int getX() {
		return x;
	}
	public int getTime() {
		return time;
	}
	public tomato(int h, int x, int y,int time) {
		this.h = h;
		this.x = x;
		this.y = y;
		this.time = time;
	}
	public int getY() {
		return y;
	}
}