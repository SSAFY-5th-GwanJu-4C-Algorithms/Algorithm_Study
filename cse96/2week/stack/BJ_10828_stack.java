package com.algo.BJ.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_10828_stack {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		StringTokenizer st;
		Stack<Integer> stack = new Stack<Integer>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine()," ");
			String s = st.nextToken();
			
			switch (s) {
			case "push":
				stack.push(Integer.parseInt(st.nextToken()));
				break;
			case "pop":
				if(stack.isEmpty()) System.out.println("-1");
				else System.out.println(stack.pop());
				break;
			case "size":
				System.out.println(stack.size());
				break;
			case "empty":
				if(stack.isEmpty()) System.out.println("1");
				else System.out.println("0");
				break;
			case "top":
				if(stack.isEmpty()) System.out.println("-1");
				else System.out.println(stack.peek());
				break;

			}
		}
		
	}

}
