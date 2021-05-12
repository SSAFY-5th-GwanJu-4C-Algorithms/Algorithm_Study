package fourthweek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
 * 물고기와 상어가 존재
 * 상어는 처음에 (0,0)에서 시작 그 위치에 있던 물고기의 방향을 가지게 됨 (sum += 물고기 번호)
 * 물고기들은 1번부터 차례대로 이동을 시작
 * 본인이 가진 방향부터 체크하여 반시계 방향으로 45도씩 살펴보며 이동할 수 있는 곳을 알아본다.
 * 상어가 있는 곳이나 경계를 넘어가는 곳으로는 이동할 수 없다.
 * 물고기의 이동이 끝나면 상어가 다시 이동할 수 있다.
 * 상어가 가진 방향으로 몇칸이든 이동 할 수 있다. 
 * 상어의 이동이 끝나면 다시 물고기들의 이동이 반복된다.
 * 
 */

class Fish {

	int x;
	int y;
	int direction;	
	
	public Fish(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
}
public class baekjoon_19236 {
	
	static Fish[] fishArr = new Fish[17];
	static int[][] map = new int[4][4];
	static boolean[] isAlive = new boolean[17];
	static int shark ;
	static int shark_direction;
	static int[] dx = {0, -1, -1, 0, +1, +1, +1, 0, -1}; // 0, 상, 좌상, 좌, 좌하, 하, 우하, 우, 우상 -> 1,2,3,4,5,6,7,8
	static int[] dy = {0, 0, -1, -1, -1, 0, +1, +1, +1};
	static int result = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// fishArr에 fish로 채워넣음
		for(int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine() , " ");
			for(int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				fishArr[num] = new Fish(i, j, dir);
			}
		}
		
		// 상어가 0,0에 위치해서 원래 0,0에 있던 물고기 먹고, 그 방향을 가짐
		isAlive[map[0][0]] = false;
		int tmp = map[0][0];
		map[0][0] = -1; // 상어
		

	
	}
	// 상어가 움직이면서 물고기 먹음
	public static void sharkMove(int r, int c, int direction, int sum, int cnt) {
		int[][] copyMap = new int[4][4];
		Fish[] copyFishArr = new Fish[17];
		
		// 배열 복사
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		
		for(int i = 1; i<=16; i++) {
			copyFishArr[i] = fishArr[i];
		}
		
		fishMove();
		
		
		for(int i = 1; i <= 3; i++) {
			
			int nr = r + (dx[direction]*i);
			int nc = c + (dy[direction]*i);
			
			if(nr >= 0 && nr < 4 && nc >= 0 && nc < 4) {
				
				if(map[nr][nc] == 0) continue;
				
				map[r][c] = 0; // 상어 원래 있던 자리는 0
				int n = map[nr][nc];
				map[nr][nc] = -1; // 상어이동하면 -1로 표시
				isAlive[n] = false; // 그자리 물고기 죽음
				
				sharkMove(nr, nc, fishArr[n].direction, sum+n, cnt+1);
				
				isAlive[n] = true;
				map[nr][nc] = n;
				map[r][c] = -1;
				
				
			} else break;
			
		}
		
		// 배열 복사
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				map[i][j] = copyMap[i][j];
			}
		}
		
		for(int i = 1; i<=16; i++) {
			fishArr[i] = copyFishArr[i];
		}
		
		
		result = Math.max(sum, result);
		
	}
	
	
	
	// 물고기 이동시키는 method
	public static void fishMove() {
		
		for(int i = 1; i <= 16; i++) {
			if(!isAlive[i]) continue;
			
			Fish fish = fishArr[i]; // 현재 움직이는 물고기
			
			int nd = fish.direction;
			int nx = fish.x, ny = fish.y;
			boolean flag = false;
			
			int[] changeDir = {0,2,3,4,5,6,7,8,1}; // 새로운 방향
			
			for(int j = 0; j < 8; j++) {
				
				nx = fish.x + dx[nd]; //새로운 물고기 자리
				ny = fish.y + dy[nd];
				
				// 경계안에 있다면
				if(nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
					
					// 물고기 방향에 상어가 있는 경우 방향 바꾸기
					if(map[nx][ny] == -1) {
						nd = changeDir[nd];
						continue;
					}
					// 빈칸이거나 상어가 없는 곳은 이동할 수 있다.
					if(map[nx][ny] == 0 || map[nx][ny] != -1) {
						flag = true; // 물고기 이동
						break;
					}
					
					
				}
				// 경계를 넘어가면 방향 변환
				else
					nd = changeDir[nd];
			}
			
			if(!flag) continue;
			
			// 물고기 자리 교환
			int temp = map[nx][ny];
			map[nx][ny] = map[fish.x][fish.y];
			map[fish.x][fish.y] = temp;
			
			// 값 갱신
			fishArr[i] = new Fish(nx, ny, nd); // 현재 움직일 물고기
			
			if(temp != 0) fishArr[temp] = new Fish(fish.x, fish.y, fishArr[temp].direction);
			
		}

	}
	

}
