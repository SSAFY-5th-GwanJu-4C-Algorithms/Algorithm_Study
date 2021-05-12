package com.algo.BJ.brute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1018_chess {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] chess = new char[N][M];
		for(int i = 0; i < N; i++) {
			String s = bf.readLine();
			for(int j = 0; j < M; j++) {
				chess[i][j] = s.charAt(j);
			}
		}
//		for(char[] ch : chess ) {
//			for (char c : ch)
//				System.out.print(c);
//			System.out.println();
//		}
		int B_cnt = 0, W_cnt = 0;
		int total = Integer.MAX_VALUE;
		for(int n = 0; n + 8 <= N; n++) {
			for(int m = 0; m + 8 <= M; m++) {
				for(int i = n; i < n + 8; i++) {
					for(int j = m; j < m + 8; j++) {
						//0,0이 B일때
						if( (i + j) % 2 == 0 && chess[i][j] == 'W') {
							B_cnt++;
						}
						else if( (i + j) % 2 == 1 && chess[i][j] == 'B') {
							B_cnt++;
						}
						
						//0,0이 W일때
						if( (i + j) % 2 == 0 && chess[i][j] == 'B') {
							W_cnt++;
						}
						else if( (i + j) % 2 == 1 && chess[i][j] == 'W') {
							W_cnt++;
						}
					}
				}//for
				if(total > B_cnt) total = B_cnt;
				if(total > W_cnt) total = W_cnt;
				B_cnt = 0;
				W_cnt = 0;
			}
		}
//		System.out.println(B_cnt);
//		System.out.println(W_cnt);
		System.out.println(total);
	}

}
