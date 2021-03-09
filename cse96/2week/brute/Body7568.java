package com.algo.BJ.brute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Body7568 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		StringTokenizer st;
		int[][] body = new int[T][2];
		for(int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(bf.readLine()," ");
			body[tc][0] = Integer.parseInt(st.nextToken());
			body[tc][1] = Integer.parseInt(st.nextToken());
		}//ют╥б
		
		int cnt = 0;
		int grade[] = new int[T];
		for(int i = 0; i < T; i++) {
			for(int j = 0; j < T; j++) {
				if(i == j) continue;
				if(body[i][0] < body[j][0] && body[i][1] <body[j][1]) {
					cnt++;
				}
			}
			grade[i] = cnt + 1;
			cnt = 0;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < T; i++) {
			sb.append(grade[i]).append(" ");
		}
		System.out.println(sb);
	}

}
