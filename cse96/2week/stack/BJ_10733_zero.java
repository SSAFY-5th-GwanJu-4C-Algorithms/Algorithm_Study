package com.algo.BJ.stack;

import java.util.Scanner;
import java.util.Stack;

public class BJ_10733_zero {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		int temp = 0;
		for(int i = 0; i < k; i++) {
			temp = sc.nextInt();
			if(temp == 0) stack.pop();
			else stack.push(temp);
		}
		int sum = 0;
		while(!stack.isEmpty()) {
			sum += stack.pop();
		}
		System.out.println(sum);
		sc.close();
	}

}
