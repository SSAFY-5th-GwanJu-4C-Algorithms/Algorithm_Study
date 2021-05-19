package study.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * ���� ���� ��ĭ (1,1) ���� ������ �Ʒ�ĭ(N,N)�� ������ִ�.
 */
public class BJ_21610_��ٶ�� {
	static int N,M,water[][];
	//�� �»� �� ��� �� ���� �� ����
	static int dx[] = {0,0,-1,-1,-1,0,1,1,1};
	static int dy[] = {0,-1,-1,0,1,1,1,0,-1};
	static Queue<Cloud> cloud;
	static boolean visit[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());//N*Nũ��
		M = Integer.valueOf(st.nextToken());//M���̵�
		water = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				water[i][j] = Integer.valueOf(st.nextToken());
			}
		}//���� ���� �Է�
		visit = new boolean[N+1][N+1];
		cloud = new LinkedList<Cloud>();
		cloud.add(new Cloud(N,1));
		cloud.add(new Cloud(N,2));
		cloud.add(new Cloud(N-1,1));
		cloud.add(new Cloud(N-1,2));

		for(int i = 0; i < M; i++) {//m�� �����̵�
			st = new StringTokenizer(br.readLine());
			int d = Integer.valueOf(st.nextToken());//����
			int s = Integer.valueOf(st.nextToken());//�Ÿ�
			MoveCloud(d,s);//���� �̵�
			WaterCopy();//�밢 4���⸸ ���
			CreateCloud();//���� ����
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
						water[i][j] -= 2;//2���̰� ��������
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
			visit[x][y] = true;//������ ����� ĭ
			//2,4,6,8,�� �밢��
			for(int dir = 2; dir < 9; dir += 2) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				if(nx >= 1 && ny >= 1 && nx < N+1 && ny < N +1 && water[nx][ny] > 0) {
					//���� �ִ� �ٱ��� �� ��ŭ ����
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
			water[nx][ny]++;//���� �� 1 ����
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
