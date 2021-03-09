package com.ssafy.March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_16198_에너지모으기 {
	static int ans = Integer.MIN_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		ArrayList<Integer> biz = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(bf.readLine()," ");
		for(int i = 0; i < N; i++) {
			biz.add(Integer.parseInt(st.nextToken()));
		}//입력 끝
		
		int n = biz.size();
		collectEnergy(biz, n, 0);
		System.out.println(ans);
	}

	private static void collectEnergy(ArrayList<Integer> biz, int n, int sum) {
		ans = Math.max(sum, ans);
		for(int i = 1; i < n - 1; i++) {
			int temp = biz.get(i - 1) * biz.get(i + 1);
			int tmp = biz.get(i);
			biz.remove(i);
			collectEnergy(biz, biz.size(), sum + temp);
			biz.add(i, tmp);
		 }
	}
}
