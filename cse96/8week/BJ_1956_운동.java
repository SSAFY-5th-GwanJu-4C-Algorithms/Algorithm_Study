package study.April.fweek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1956_운동 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine()," ");
		int V = Integer.parseInt(st.nextToken()); //마을 개수 2~400
		int E = Integer.parseInt(st.nextToken()); //도로 개수 0~V(V-1)
		
		int d[][] = new int[V+1][V+1];
		for(int i = 0; i <= V; i++) {
			Arrays.fill(d[i], 10000);
		}//초기화
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(bf.readLine()," ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			d[from][to] = weight;
		}//입력 끝
		
		for(int k = 1; k <= V; k++) {//경
			for(int i = 1; i <= V; i++) { //출발
				for(int j = 1; j <= V; j++) { //도착
					if(d[i][k] + d[k][j] < d[i][j]) {
						d[i][j] = d[i][k] + d[k][j];
					}
				}
			}
		}
		
		int total = 10000;
		for(int i = 1; i <= V; i++) {
			for(int j = 1; j <= V; j++) {
				if(d[i][j] != 10000 && d[j][i] != 10000) { //양쪽다 연결되있다면
					total = Math.min(total, d[i][j] + d[j][i]);
				}
			}
		}
		if(total != 10000) {
			System.out.println(total);
		}
		else
			System.out.println(-1);
		
	}

}
