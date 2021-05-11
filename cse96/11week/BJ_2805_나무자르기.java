package study.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2805_나무자르기 {
	static long ans = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());
		
		int max = 0;
		int min = 0;
		
		st = new StringTokenizer(bf.readLine());
		int tree[] = new int[N];
		for(int i = 0; i < N; i++) {
			tree[i] = Integer.valueOf(st.nextToken());
			if(tree[i] > max) max = tree[i];
		}//입력 끝
		Arrays.sort(tree);
		BinarySearch(tree,min,max, M, N);
		System.out.println(ans);
	}
	
	//height:높이
	private static void BinarySearch(int[] tree, int min, int max, int target, int N) {
		int height = (min + max) / 2;
		long len = 0;
		
		for(int i = 0; i < N; i++) {
			if(tree[i] > height) len += tree[i] - height;
		}
		if(len == target) ans=height;
		else if(len > target) {
			if(ans == height) return;
			ans = Math.max(ans, height);
			BinarySearch(tree,height+1,max,target,N);
		}
		else {
			BinarySearch(tree,min,height-1,target,N);
		}
	}
}
