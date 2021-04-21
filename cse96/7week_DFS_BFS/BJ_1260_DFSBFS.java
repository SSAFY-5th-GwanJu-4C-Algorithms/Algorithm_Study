import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer>[] graph;
	static boolean[] visit;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine()," ");
		int N = Integer.parseInt(st.nextToken());//정점 수
		int M = Integer.parseInt(st.nextToken());//간선 수
		int V = Integer.parseInt(st.nextToken());//시작 정점
		graph = new ArrayList[N+1];
		visit = new boolean[N+1];
		sb = new StringBuilder();
		for(int i = 1; i <=N; i++) {
			graph[i] = new ArrayList<Integer>();
		}//그래프 초기화
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine()," ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);
			graph[to].add(from);
		}
		
		for(int i = 1; i <= N; i++) {
			Collections.sort(graph[i], (o1,o2) -> o1-o2);
		}
		
		dfs(V);
		System.out.println(sb);
		bfs(V);
		
	}
	private static void bfs(int v) {
		Queue<Integer> b = new LinkedList<Integer>();
		sb.setLength(0);
		Arrays.fill(visit, false);
		b.add(v);
		visit[v] = true;
		sb.append(v+" ");
		while(!b.isEmpty()) {
			int vertex = b.poll();//연결된 정점
			
			int size = graph[vertex].size();
			for(int i = 0; i < size; i++) {
				int next = graph[vertex].get(i);
				if(!visit[next]) {
					b.add(next);
					visit[next] = true;
					sb.append(next+" ");
				}
			}
		}
		System.out.println(sb);
	}
	private static void dfs(int v) {
		visit[v] = true;
		sb.append(v+" ");
		int size = graph[v].size();
		for(int i = 0; i < size; i++) {
			int next = graph[v].get(i);
			if(!visit[next]) {
				dfs(next);
			}
		}
	}

}
