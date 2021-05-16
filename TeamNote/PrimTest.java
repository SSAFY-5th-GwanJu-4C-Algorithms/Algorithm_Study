package study.May;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PrimTest {
/*
 * 임의의 정점을 하나 선택해서 시작
 * 선택한 정점과 인접하는 정점들 중의 최소 비용의 간선이 존재하는 정점 선택
 * 모든 정점이 선택될때까지 위 과정 반복
 * 서로소인 2개의 집합 정보 유지
 * 트리 정점-MST만들기 위해 선택된 정점
 * 비트리 정점-선택되지 않은 정점들
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
	static int N;//정점의 수
	static int E;//간선의 수
	static int result = 0, cnt = 0;//최종 비용값result, 정점만큼 순회를 카운트위한 cnt
	static ArrayList<ArrayList<Edge>> graph;//간선리스트
	static boolean[] visit;
	static PriorityQueue<Edge> pq; 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
		N = Integer.valueOf(br.readLine());//노드 개수
		E = Integer.valueOf(br.readLine());//간선 개수
		
		pq = new PriorityQueue<Edge>();
		graph = new ArrayList<>();
		visit = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());//각 정점마다 리스트 초기화 생성
		}
		StringTokenizer st;
		for(int i = 0; i < E; i++) {//간선 수 만큼 간선정보 입력
			st = new StringTokenizer(br.readLine());
			//ArrayList의 ArrayList라 0번 인덱스부터 시작(입력값도 0번정점부터시작)
			//입력값이 1부터시작이면 A,B값에 -1해주면 됩니다.
			int A = Integer.valueOf(st.nextToken());
			int B = Integer.valueOf(st.nextToken());
			int cost = Integer.valueOf(st.nextToken());
			
			graph.get(A).add(new Edge(B,cost));
			graph.get(B).add(new Edge(A,cost));
		}
		//임의의 정점(0)부터 시작.
		//0번정점, 거리 0(혼자이므로)
		pq.add(new Edge(0,0));
		/*
			처음에 0번정점이 poll되고 0번이 방문처리 됩니다.
			0번정점과 연결된 노드중 방문 안햇으므로 다 pq에 넣어주고 while문 시작
		*/
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			//방문했다면 continue;
			if(visit[edge.node]) continue;
			visit[edge.node] = true;
			//선택했으므로 거리 증가
			result += edge.cost;
			System.out.println("연결된 간선: " + edge.node + " " + edge.cost);
			
			//그래프에 연결된 노드를 돌면서 방문하지 않으면 pq에 넣어준다.
			for(Edge next : graph.get(edge.node)) {//모든리스트에 저장된 정점들
				if(!visit[next.node]) {
					pq.add(next);
				}
			}
			//모든 정점을 순회
			if(++cnt == N) break;
		}
		System.out.println("최소비용:"+result);
	}

	
	
	static class Edge implements Comparable<Edge>{
		int node; //정점번호
		int cost; //비용
		
		
		public Edge(int node, int cost) {
			super();
			this.node = node;
			this.cost = cost;
		}


		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
		
	}
}
