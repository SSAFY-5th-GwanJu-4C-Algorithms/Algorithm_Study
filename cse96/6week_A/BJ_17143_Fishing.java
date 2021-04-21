import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	//s:속력 d:이동방향 1:위, 2: 아래, 3: 오른쪽, 4: 왼쪽/z:크기
	static int R,C,M,ans,map[][];
	//위 오른쪽 아래 왼쪽
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	static ArrayList<Shark> l = new ArrayList<Shark>();
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine()," ");
		R = Integer.valueOf(st.nextToken());//세로 크기
		C = Integer.valueOf(st.nextToken());//가로 크기
		M = Integer.valueOf(st.nextToken());//상어 수
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine()," ");
			int r = Integer.valueOf(st.nextToken());
			int c = Integer.valueOf(st.nextToken());//위치
			int s = Integer.valueOf(st.nextToken());//속력
			int d = Integer.valueOf(st.nextToken());//방향 1위 2아래 3오른쪽 4왼쪽
			if(d == 2) d = 3;//아래
			else if(d == 3) d = 2;//오른쪽//  0:위 1:오른쪽 2:아래 3:왼쪽
			int z = Integer.valueOf(st.nextToken());//크기
			d=d-1;
			if(d==0 || d==2)l.add(new Shark(r,c,s%((R-1)*2) ,d,z));
			else if(d==1 || d==3)l.add(new Shark(r,c,s%((C-1)*2) ,d,z));
		}
		map = new int[R + 1][C + 1];
		for(int i = 1; i <= C; i++) {
            //초기화
		    for(int j = 1; j <= R; j++) {
			    Arrays.fill(map[j], 0);
		    }
			fishing(i);//낚시왕 위치 i
			Move();//상어이동
		}
		System.out.println(ans);
	}
	private static void Check(int until) {
		Shark cur = l.get(until);
		for(int i = 0; i < until; i++) {
			Shark s = l.get(i);
			if(s.fished || !s.isAlive) continue;
			if(s.r == cur.r && s.c == cur.c) {//같은위치
				if(s.z < cur.z) s.setAlive(false);
				else cur.setAlive(false);
				break;
			}
		}
	}
	private static void Move() {
		for(int i = 0; i < M; i++) {
			Shark s = l.get(i);
			if(s.fished || !s.isAlive) continue; //잡혔거나 먹힌거면 없는채로 둔다.
			int x = s.r;
			int y = s.c;
			int dir = s.d;
			int nx = x + (dx[dir]*s.s);
			int ny = y + (dy[dir]*s.s);
			if(nx <= R && ny <= C && nx > 0 && ny > 0) { //방향그대로, 위치 변경
				s.r = nx;
				s.c = ny;
			}
			//범위를 벗어나므로 방향이 바뀌고 위치도 변경된다.
			else if(ny > C || ny < 1 && nx == s.r){
				//좌우로 이동
				int cnt = 0;
				while(cnt < s.s) {
					int c = y + dy[dir];//임시값으로 다음칸 계산
					if(c < 1 || c > C) dir = (dir+2) % 4;//계산한 다음 칸이 경계를 벗어나는 경우 방향을 미리 전환
					y += dy[dir];//미리전환됬거나 올바른방향으로 한칸 이동
					cnt++;
				}
				ny = y;
				s.c = ny;//위치갱신
				s.d = dir;//방향 갱신
			}
			else if(nx > R || nx < 1 && ny == s.c) {
				//위아래로 이동
				int cnt = 0;
				while(cnt != s.s) {
					int c = x + dx[dir];
					if(c < 1 || c > R) dir = (dir + 2) % 4;
					x += dx[dir];
					cnt++;
				}
				nx = x;
				s.r = nx;//위치갱신
				s.d = dir;//방향갱신
			}
			if(map[s.r][s.c] == 0) map[s.r][s.c] = s.z;
			else Check(i);
		}
	}
	private static void fishing(int loc) {//상어잡기
		Shark target = null;
		for(int i = 0; i < M; i++) {//반복문돌면서 상어 찾기
			Shark s = l.get(i);
			if(s.fished || !s.isAlive) continue;
			if(loc == s.c) {
				if(target == null) {//loc와 같은위치의 상어가 지금까지 없었다면
					target = s;//target을 현재 i번째 상어로 지정
					continue;
				}
				else if(target.r > s.r){//target이 null이 아니면
					target = s;//target보다 현재 i번째 상어가 위에 있는경우 target갱신
				}
			}
		}
		if(target != null) {//잡기
			ans += target.z;
			target.setFished(true);
		}
	}
	static class Shark{
		int r,c,s,d,z;
		boolean fished = false;
		boolean isAlive = true;
		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;//상어 위치
			this.c = c;
			this.s = s;//속력
			this.d = d;//이동방향
			this.z = z;//크기
		}
		public void setFished(boolean fished) {
			this.fished = fished;
		}
		public void setAlive(boolean isAlive) {
			this.isAlive = isAlive;
		}
	}
}
