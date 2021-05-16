package study.May;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*������ �ϳ��� �����ؼ� MST�� ã�� �˰���
1. ����, ��� ������ ����ġ�� ���� �������� ����
2. ����ġ�� ���� �������� �����ϸ鼭 Ʈ���� ����
 -(����Ŭ�� ����� �������� ����ġ�� �������� ����)
3.n-1���� ������ ���õɶ����� 2.�ݺ�
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
	static int N; //���(����)�� ����
	static int E; //������ ����
	static PriorityQueue<Edge> pq;//���� ���� �ּ����� �켱����ť
	static int[] parent; //union find�� ���� �� ����� �θ� ��� ���� �迭
	static boolean[] visit;//�湮�� ��� ����
	static int result; //����� ����
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
		N = Integer.valueOf(br.readLine());//��� ����
		E = Integer.valueOf(br.readLine());//���� ����
		
		parent = new int[N+1];//1~N�� ��� ��ȣ�� �ε���
		visit = new boolean[N+1];
		result = 0;
		
		pq = new PriorityQueue<Edge>();
		int from = 0, to = 0, cost = 0;
		for(int i = 0; i < E; i++) {//���� �� ��ŭ ����
			StringTokenizer st = new StringTokenizer(br.readLine());
			from = Integer.valueOf(st.nextToken());//��������
			to = Integer.valueOf(st.nextToken());  //�� ����
			cost = Integer.valueOf(st.nextToken());//���
			pq.add(new Edge(from,to,cost));//�켱���� ť�� ���� & �������� ����
		}//�Է� ��
		
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}//union-find�� �ʱ�ȭ(�ڱ� �ڽ��� �θ���� �ڱ� �ڽ�)
		
		for(int i = 0; i < E; i++) {//��� ������ ����
			Edge minedge = pq.poll();// �켱���� ť���� �ּҺ�밣�� ����
			
			int start = minedge.s;
			int end = minedge.e;
			//���� ���ý� ����Ŭ ���� �Ǵ��� ���� �θ��带 ã�´�
			int a = find(start);
			int b = find(end);
			if(a == b) continue; //�θ��尡 ������ �Ѿ
			
			union(start,end);//�ΰ��� �θ��尡 �ٸ��� ������ �θ��带 �ٸ� ������ �θ���� ����
			result += minedge.v;//���õ� �����̹Ƿ� ��������� ����
			System.out.println("���õȰ���:" + start + " " + end + " " + minedge.v);
		}
		
		System.out.println("���� ���:" + result);
	}//main
	
	private static void union(int a, int b) {//union������ �θ��尡 �ٸ���츸 ����
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
		if(a==parent[a]) return a; //�ʱ�ȭ�� ���¸� �ڽ��� ����
		parent[a] = find(parent[a]);//find�� ������ �θ�� �ֻ��� �θ�� ����
		
		return parent[a];
	}

	static class Edge implements Comparable<Edge>{
		int s;//���� ����
		int e;//�� ����
		int v;//���
		
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
