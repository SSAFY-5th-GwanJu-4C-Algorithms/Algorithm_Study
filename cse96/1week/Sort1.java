package com.baek.io;

import java.util.Scanner;

public class Sort1 {

	public static void main(String[] args) {
		//Á¤·Ä 1¹ø
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int[] array = new int[N];
		for(int i = 0; i < N; i++) {
			array[i] = scan.nextInt();
		}
		
		int temp = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++ ) {
				if(j == N-1 )break;
				if(array[j] > array[j+1]) {
					temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
		for(int s : array) {
			System.out.println(s);
		}
		

	}

}
