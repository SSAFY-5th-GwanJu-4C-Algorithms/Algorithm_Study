package com.algo.BJ.brute;

import java.util.Scanner;

public class BJ_1436syom {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		//ÀÔ·Â³¡
		int cnt = 1;
		int num = 666;
		while(cnt != N) {
			num++;
			if(String.valueOf(num).contains("666")) {
				cnt++;;
			}
		}
		System.out.println(num);
		
		sc.close();

	}

}
