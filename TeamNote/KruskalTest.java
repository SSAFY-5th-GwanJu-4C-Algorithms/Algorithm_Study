package study.May;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*간선을 하나씩 선택해서 MST를 찾는 알고리즘
1. 최초, 모든 간선을 가중치에 따라 오름차순 정렬
2. 가중치가 낮은 간선부터 선택하면서 트리를 만들어감
 -(사이클이 생기면 다음으로 가중치가 낮은간선 선택)
3.n-1개의 간선이 선택될때까지 2.반복
 */

/*
입력은 오픈북 보고 가져왔습니다.
입력 값
7
10
0 2 31
1 2 21
2 6 25
5 4 40
0 5 60
0 6 51
2 4 46
5 3 18
3 4 34
4 6 51

입력 값 정렬 후
5 3 18
1 2 21
2 6 25
0 2 31
3 4 34
5 4 40
2 4 46
0 6 51
4 6 51
0 5 60

선택 된 간선
5 3 18
1 2 21
2 6 25
0 2 31
3 4 34
2 4 46
 */
public class KruskalTest {
	static String input = "7\r\n" + 
			"10\r\n" + 
			"0 2 31\r\n" + 
			"1 2 21\r\n" + 
			"2 6 25\r\n" + 
			"5 4 40\r\n" + 
			"0 5 60\r\n" + 
			"0 6 51\r\n" + 
			"2 4 46\r\n" + 
			"5 3 18\r\n" + 
			"3 4 34\r\n" + 
			"4 6 51";
	static int N; //노드(정점)의 개수
	static int E; //간선의 개수
	static PriorityQueue<Edge> pq;//간선 값을 최소정렬 우선순위큐
	static int[] parent; //union find를 위한 각 노드의 부모 노드 저장 배열
	static boolean[] visit;//방문한 노드 여부
	static int result; //결과값 저장
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
		N = Integer.valueOf(br.readLine());//노드 개수
		E = Integer.valueOf(br.readLine());//간선 개수
		
		parent = new int[N+1];//1~N번 노드 번호로 인덱스
		visit = new boolean[N+1];
		result = 0;
		
		pq = new PriorityQueue<Edge>();
		int from = 0, to = 0, cost = 0;
		for(int i = 0; i < E; i++) {//간선 수 만큼 저장
			StringTokenizer st = new StringTokenizer(br.readLine());
			from = Integer.valueOf(st.nextToken());//시작정점
			to = Integer.valueOf(st.nextToken());  //끝 정점
			cost = Integer.valueOf(st.nextToken());//비용
			pq.add(new Edge(from,to,cost));//우선순위 큐에 저장 & 비용순으로 정렬
		}//입력 끝
		
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}//union-find의 초기화(자기 자신의 부모노드는 자기 자신)
		
		for(int i = 0; i < E; i++) {//모든 간선에 대해
			Edge minedge = pq.poll();// 우선순위 큐에서 최소비용간선 꺼냄
			
			int start = minedge.s;
			int end = minedge.e;
			//간선 선택시 사이클 여부 판단을 위해 부모노드를 찾는다
			int a = find(start);
			int b = find(end);
			if(a == b) continue; //부모노드가 같으면 넘어감
			
			union(start,end);//두개의 부모노드가 다르면 한쪽의 부모노드를 다른 한쪽의 부모노드로 설정
			result += minedge.v;//선택된 간선이므로 간선비용을 더함
			System.out.println("선택된간선:" + start + " " + end + " " + minedge.v);
		}
		
		System.out.println("최종 비용:" + result);
	}//main
	
	private static void union(int a, int b) {//union에서는 부모노드가 다른경우만 들어옴
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot != bRoot) {
			parent[aRoot] = b;
		}
		else {
			return;
		}
	}

	private static int find(int a) {
		if(a==parent[a]) return a; //초기화된 상태면 자신을 리턴
		parent[a] = find(parent[a]);//find할 때마다 부모는 최상위 부모로 설정
		
		return parent[a];
	}

	static class Edge implements Comparable<Edge>{
		int s;//시작 정점
		int e;//끝 정점
		int v;//비용
		
		public Edge(int s, int e, int v) {
			super();
			this.s = s;
			this.e = e;
			this.v = v;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.v - o.v;
		}
		
	}
}
