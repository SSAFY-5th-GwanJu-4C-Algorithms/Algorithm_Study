package study.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 선플레이어 홀수 선택
 * 후플레이어 짝수 선택
 */
public class BJ_20040_사이클게임 {
	static int N, M, ans, parents[];
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.valueOf(st.nextToken());//점의 개수
		M = Integer.valueOf(st.nextToken());//차례의 수
		
		parents = new int[N];//점의 개수만큼 배열 생성
		for(int i = 0; i < N; i++) {
			parents[i] = i;//초기화 아직 연결된것이 없어서 자기자신이 루트노드
		}
		
		for(int i = 1; i <= M; i++) {//차례만큼 반복
			st = new StringTokenizer(bf.readLine());
			int point1 = Integer.valueOf(st.nextToken());
			int point2 = Integer.valueOf(st.nextToken());
			//union확인
			if(union(point1,point2)) {
				ans = i;//i번째 차례에 사이클 형성
				break;
			}
		}
		System.out.println(ans);
		bf.close();
		return;
	}
	
	private static boolean union(int p1, int p2) {
		int aRoot = find(p1);//p1의 루트노드
		int bRoot = find(p2);//p2의 루트노드
		
		if(aRoot == bRoot) return true;//같다면 사이클이 형성됨.
		parents[bRoot] = aRoot; //p1-p2연결 p1이 p2의 루트노드가 됨
		return false;
	}
	
	private static int find(int n) {
		if(n == parents[n]) return n; //연결되지 않은 상태 자신이 루트노드
		return parents[n] = find(parents[n]);//본인의 루트노드를 리턴. 루트노드 갱신
	}
}
