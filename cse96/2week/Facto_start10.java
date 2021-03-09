package com.algo.BJ;

import java.util.Arrays;
import java.util.Scanner;

public class Facto_start10 {
	public static int N;
	public static char[][] star;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.close();
		star = new char[N][N];
		star(0,0,N);
		for(int i = 0; i < N; i++) {
			System.out.println(star[i]);
		}
	}

	private static void star(int col, int row , int n) {
		// TODO Auto-generated method stub
		if(n == 1) {
			star[col][row] = '*';
			return ;
		}
		
		int m = n / 3;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(i == 1 && j == 1) continue;
				star(col + (m*i), row + (m*j), m);
			}
		}
	}
}




