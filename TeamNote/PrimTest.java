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
 * ������ ������ �ϳ� �����ؼ� ����
 * ������ ������ �����ϴ� ������ ���� �ּ� ����� ������ �����ϴ� ���� ����
 * ��� ������ ���õɶ����� �� ���� �ݺ�
 * ���μ��� 2���� ���� ���� ����
 * Ʈ�� ����-MST����� ���� ���õ� ����
 * ��Ʈ�� ����-���õ��� ���� ������
 */
	/*
	�Է��� ���º� ���� �����Խ��ϴ�.
	�Է� ��
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

	�Է� �� ���� ��
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

	���� �� ����
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
	static int N;//������ ��
	static int E;//������ ��
	static int result = 0, cnt = 0;//���� ��밪result, ������ŭ ��ȸ�� ī��Ʈ���� cnt
	static ArrayList<ArrayList<Edge>> graph;//��������Ʈ
	static boolean[] visit;
	static PriorityQueue<Edge> pq; 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
		N = Integer.valueOf(br.readLine());//��� ����
		E = Integer.valueOf(br.readLine());//���� ����
		
		pq = new PriorityQueue<Edge>();
		graph = new ArrayList<>();
		visit = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());//�� �������� ����Ʈ �ʱ�ȭ ����
		}
		StringTokenizer st;
		for(int i = 0; i < E; i++) {//���� �� ��ŭ �������� �Է�
			st = new StringTokenizer(br.readLine());
			//ArrayList�� ArrayList�� 0�� �ε������� ����(�Է°��� 0���������ͽ���)
			//�Է°��� 1���ͽ����̸� A,B���� -1���ָ� �˴ϴ�.
			int A = Integer.valueOf(st.nextToken());
			int B = Integer.valueOf(st.nextToken());
			int cost = Integer.valueOf(st.nextToken());
			
			graph.get(A).add(new Edge(B,cost));
			graph.get(B).add(new Edge(A,cost));
		}
		//������ ����(0)���� ����.
		//0������, �Ÿ� 0(ȥ���̹Ƿ�)
		pq.add(new Edge(0,0));
		/*
			ó���� 0�������� poll�ǰ� 0���� �湮ó�� �˴ϴ�.
			0�������� ����� ����� �湮 �������Ƿ� �� pq�� �־��ְ� while�� ����
		*/
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			//�湮�ߴٸ� continue;
			if(visit[edge.node]) continue;
			visit[edge.node] = true;
			//���������Ƿ� �Ÿ� ����
			result += edge.cost;
			System.out.println("����� ����: " + edge.node + " " + edge.cost);
			
			//�׷����� ����� ��带 ���鼭 �湮���� ������ pq�� �־��ش�.
			for(Edge next : graph.get(edge.node)) {//��縮��Ʈ�� ����� ������
				if(!visit[next.node]) {
					pq.add(next);
				}
			}
			//��� ������ ��ȸ
			if(++cnt == N) break;
		}
		System.out.println("�ּҺ��:"+result);
	}

	
	
	static class Edge implements Comparable<Edge>{
		int node; //������ȣ
		int cost; //���
		
		
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
