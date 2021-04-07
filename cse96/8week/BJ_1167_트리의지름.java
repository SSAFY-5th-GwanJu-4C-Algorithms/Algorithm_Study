package study.April.fweek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1167_트리의지름 {
	static ArrayList<Node>[] graph;
	static int max = 0;
	static boolean visit[];
	static Node t1;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(bf.readLine());
		
		graph = new ArrayList[V+1];
		visit = new boolean[V+1];
		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
			
		}
		for(int i = 1; i <= V; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine()," ");
			int from = Integer.parseInt(st.nextToken());
			while(true) {
				int to = Integer.parseInt(st.nextToken());
				if(to == -1) break;
				int weight = Integer.parseInt(st.nextToken());
				graph[from].add(new Node(to,weight));
			}
		}//그래프 초기화 & 입력
		
		dfs(1,0);
		Arrays.fill(visit, false);
		dfs(t1.getTo(),0);
		System.out.println(max);
	}
	
	private static void dfs(int cur, int weight) {
		if(weight > max) {
			max = weight;
		}
		visit[cur] = true;
		int size = graph[cur].size();
		if(size == 0) return;
		for(int i = 0; i < size; i++) {
			Node next = graph[cur].get(i);
			if(!visit[next.getTo()]) {//방문하지 않았다면
				dfs(next.getTo() , weight+next.getWeight());
				if(max == weight+next.getWeight()) t1 = next;
			}
		}
	}
}

class Node{
	int to;
	int weight;
	public Node(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}
	public int getTo() {
		return to;
	}
	public int getWeight() {
		return weight;
	}
}