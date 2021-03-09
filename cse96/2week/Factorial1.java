package com.algo.BJ;

import java.util.Scanner;

public class Factorial1 {

	public static int[] fact;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		fact = new int[N];
		int result = factorial(N);
		System.out.println(result);
		sc.close();
	}

	private static int factorial(int n) {
		// TODO Auto-generated method stub
		if(n <= 1) return 1;
		return n * factorial(n-1);
	}

}
