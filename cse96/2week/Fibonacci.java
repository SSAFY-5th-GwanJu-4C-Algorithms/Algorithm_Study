package com.algo.BJ;

import java.util.Scanner;

public class Fibonacci {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int result = fibo(N);
		System.out.println(result);
		sc.close();
	}
	
	private static int fibo(int n) {
		if(n == 0 ) return 0;
		if(n <= 2) return 1;
		return fibo(n-1) + fibo(n-2);
	}

}
