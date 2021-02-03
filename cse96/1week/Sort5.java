package com.baek.io;

import java.util.Scanner;

public class Sort5 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int count = 1;
		int div = N;
		
		while(div >= 10) {
			div /= 10;
			count++;
		}
			
		int[] num = new int[count];
			
		for(int i = 0; i < count; i++) {
			num[i] = N % 10;
			N /= 10;
		}
		
		int temp = 0;
		for(int i = 0; i < num.length;i++) {
			for(int j = 0;j < num.length; j++) {
				if(j == num.length-1) break;
				if(num[j] < num[j+1]) {
					temp = num[j];
					num[j] = num[j+1];
					num[j+1] = temp;
				}
			}
		}
		for(int s : num) {
			System.out.print(s);
		}		
	}	
}
