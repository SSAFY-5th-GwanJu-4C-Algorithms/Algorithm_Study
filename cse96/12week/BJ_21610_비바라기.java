package study.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 가장 왼쪽 윗칸 (1,1) 가장 오른쪽 아랫칸(N,N)은 연결되있다.
 */
public class BJ_21610_비바라기 {
	static int N,M,water[][];
	//좌 좌상 상 우상 우 우하 하 좌하
	static int dx[] = {0,0,-1,-1,-1,0,1,1,1};
	static int dy[] = {0,-1,-1,0,1,1,1,0,-1};
	static Queue<Cloud> cloud;
	static boolean visit[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());//N*N크기
		M = Integer.valueOf(st.nextToken());//M번이동
		water = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				water[i][j] = Integer.valueOf(st.nextToken());
			}
		}//물의 정보 입력
		visit = new boolean[N+1][N+1];
		cloud = new LinkedList<Cloud>();
		cloud.add(new Cloud(N,1));
		cloud.add(new Cloud(N,2));
		cloud.add(new Cloud(N-1,1));
		cloud.add(new Cloud(N-1,2));

		for(int i = 0; i < M; i++) {//m번 구름이동
			st = new StringTokenizer(br.readLine());
			int d = Integer.valueOf(st.nextToken());//방향
			int s = Integer.valueOf(st.nextToken());//거리
			MoveCloud(d,s);//구름 이동
			WaterCopy();//대각 4방향만 고려
			CreateCloud();//구름 생성
		}
		int ans = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(water[i][j] > 0)
					ans+=water[i][j];
			}
		}
		System.out.println(ans);
	}
	private static void CreateCloud() {
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(visit[i][j]) {
					visit[i][j] = false;
				}else {
					if(water[i][j] >= 2) {
						water[i][j] -= 2;//2줄이고 구름생성
						cloud.add(new Cloud(i,j));
					}
				}
			}
		}
	}
	private static void WaterCopy() {
		while(!cloud.isEmpty()) {
			Cloud c = cloud.poll();
			int x = c.x;
			int y = c.y;
			visit[x][y] = true;//구름이 사라진 칸
			//2,4,6,8,이 대각선
			for(int dir = 2; dir < 9; dir += 2) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				if(nx >= 1 && ny >= 1 && nx < N+1 && ny < N +1 && water[nx][ny] > 0) {
					//물이 있는 바구니 수 만큼 증가
					water[x][y]++;
				}
					
			}
		}
	}

	
	private static void MoveCloud(int d, int s) {
		int size = cloud.size();
		for(int i = 0; i < size; i++) {
			Cloud c = cloud.poll();
			int nx = c.x + (dx[d] * s);
			int ny = c.y + (dy[d] * s);
			while(nx < 0) nx+=N;
			while(ny < 0) ny+=N;
			nx = nx%N;
			ny = ny%N;
			if(nx == 0)nx=N;
			if(ny == 0)ny=N;
			water[nx][ny]++;//물의 양 1 증가
			cloud.add(new Cloud(nx,ny));
		}
	}
	
	static class Cloud{
		int x;
		int y;
		public Cloud(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}		
	}
}
