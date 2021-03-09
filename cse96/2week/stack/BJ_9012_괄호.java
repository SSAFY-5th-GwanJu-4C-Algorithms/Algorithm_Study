package com.algo.BJ.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_9012_괄호 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> stack = new Stack<Character>();
		boolean flag;
		int T = Integer.parseInt(bf.readLine());
		for(int tc = 1; tc <= T; tc++) {
			stack.clear();
			flag = true;
			String s = bf.readLine();
			int size = s.length();
			for(int i = 0; i < size; i++) {
				if(s.charAt(i) == '(') {
					stack.push('(');
				}
				else {
					if(stack.isEmpty()) flag = false;
					else if(stack.peek() == '(') {
						stack.pop();
					}
					else if(stack.peek() == ')') {
						flag = false;
					}
				}
			}//for - 한줄 처리 후
			if(!stack.isEmpty()) flag = false;
			if(flag) System.out.println("YES");
			else System.out.println("NO");
		}//for-tc
		
	}

}
