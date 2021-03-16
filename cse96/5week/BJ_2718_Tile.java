package com.ssafy.March.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2718_Tile {

	
		public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		int tile[] = new int[1001];
		tile[0] = 1;//tile[0]부분이 있어야 N번째 타일에서 추가되는 타일의 모양이 추가됨**
		tile[1] = 1;
		tile[2] = 5;
		tile[3] = 11;
		int tmp = 3;//3번칸 까지는초기화 됨
		for(int i = 0; i < T; i++) {
			int N = Integer.parseInt(bf.readLine());
			if(N <= tmp) {//3번칸 전이거나 이전 반복문에서 처리한 경우
				System.out.println(tile[N]);
				continue;
			}
			else {//이전값 tmp보다 큰 N
				for(int j = tmp + 1; j <= N; j++) {//j: tmp이후의 칸부터 입력된 N까지의 칸들 계산
					for(int k = j - 1; k >= 0; k--) {//k: j부터 1번칸 까지
						int dist = j-k;
						if(k == j - 1) {      //한칸전
							tile[j] += tile[k];
						}
						else if(k == j - 2) { //2칸전
							tile[j] += tile[k]*4;
						}
						else if( dist % 2 == 0) { //짝수칸 떨어진 경우         =
							tile[j] += tile[k]*3;//                =   
 						}
						else if( dist % 2 == 1) { //홀수칸 떨어진 경우
							tile[j] += tile[k]*2;
						}
					}
				}
			}
			System.out.println(tile[N]);
			tmp = N;
		}
	}
}
