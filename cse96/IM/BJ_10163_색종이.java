package com.ssafy.IM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_10163_색종이 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int[][] paper = new int[101][101];
		for(int i = 0; i < 101; i++) {
			Arrays.fill(paper[i], 0);
		}//초기화
		
		int x1, x2, y1 ,y2;
		StringTokenizer st;
		for(int tc = 1; tc <= N; tc++) {
			st = new StringTokenizer(bf.readLine(), " ");
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			//입력 끝
			
			for(int i = x1; i < x1 + x2; i++) {
				for(int j = y1; j < y1 + y2; j++) {
					paper[i][j] = tc;
				}
			}
		}//색종이 두기 끝
		
		int cnt = 0;
		for(int tc = 1; tc <= N; tc++) {
			for(int i = 0; i < 101; i++) {
				for(int j = 0; j < 101; j++) {
					if(paper[i][j] == tc) cnt++;
				}
			}
			System.out.println(cnt);
			cnt = 0;
		}

	}

}
