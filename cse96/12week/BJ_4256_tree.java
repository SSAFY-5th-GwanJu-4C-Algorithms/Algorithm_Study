package study.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_4256_tree {
	static int pre[], in[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.valueOf(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int n = Integer.valueOf(br.readLine());//노드의 개수
			StringTokenizer st = new StringTokenizer(br.readLine());
			pre = new int[n];
			for(int i = 0; i < n; i++) {
				pre[i] = Integer.valueOf(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			in = new int[n];
			for(int i = 0; i < n; i++) {
				in[i] = Integer.valueOf(st.nextToken());
			}
			maketree(0,n,0);
			System.out.println();
		}
	}
	private static void maketree(int left, int right, int root) {
		for(int i = left; i < right; i++) {
			if(in[i] == pre[root]) {
				maketree(left, i, root + 1);//i까지 왼쪽 서브트리
				maketree(i + 1, right, root + 1 + i - left);//i+1부터 끝까지 오른쪽 서브트리
				System.out.print(pre[root] +" ");
			}
		}
		
	}
	static class Node{
		Node left;
		Node right;
		int number;
		public Node(Node left, Node right, int number) {
			this.left = left;
			this.right = right;
			this.number = number;
		}
		
	}
}
