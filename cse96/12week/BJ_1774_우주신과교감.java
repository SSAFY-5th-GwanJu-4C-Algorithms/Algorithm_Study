package study.May;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1774_���ֽŰ����� {
	static int parents[];
	static PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());//��� ��
		int M = Integer.valueOf(st.nextToken());//����� ���� ��
		
		Point[] map = new Point[N+1];
		parents = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parents[i] = i;//�θ��尡 �ڽ����� �ʱ�ȭ
		}
		//��ǥ �Է�
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken());
			int y = Integer.valueOf(st.nextToken());
			map[i] = new Point(x,y);
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int p1 = Integer.valueOf(st.nextToken());
			int p2 = Integer.valueOf(st.nextToken());
			union(p1,p2);//�� �� ��ǥ�� �����
		}
		//��� ���� �ĺ� ���� �� pq����
		for(int i = 1; i < N; i++) {
			for(int j = i + 1; j <= N; j++) {
				if(i == j) continue;
				if(parents[i] != parents[j]) {//����ȉ�����
					double x = Math.pow(map[i].x - map[j].x,2);
					double y = Math.pow(map[i].y - map[j].y,2);
					double dist = Math.sqrt(x + y);
					pq.add(new Edge(i,j,dist));
				}
			}
		}
		double ans = 0;
		//���� ���� �� union-find����
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			
			int start = edge.s;
			int end = edge.e;
			int aRoot = find(start);
			int bRoot = find(end);
			if(aRoot == bRoot) continue;
			
			union(start,end);
			ans += edge.v;
		}
		System.out.printf("%.2f",(double)Math.round(ans*100)/100);
		
	}
	
	private static void union(int a, int b) {//union������ �θ��尡 �ٸ���츸 ����
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot < bRoot) {
			parents[bRoot] = aRoot;
		}
		else if(bRoot < aRoot) {
			parents[aRoot] = bRoot;
		}
		else {
			return;
		}
	}

	private static int find(int a) {
		if(a == parents[a]) return a; //�ʱ�ȭ�� ���¸� �ڽ��� ����
		parents[a] = find(parents[a]);//find�� ������ �θ�� �ֻ��� �θ�� ����
		
		return parents[a];
	}

	
	static class Edge implements Comparable<Edge>{
		int s;//���� ����
		int e;//�� ����
		double v;//���
		
		public Edge(int s, int e, double v) {
			super();
			this.s = s;
			this.e = e;
			this.v = v;
		}

		@Override
		public int compareTo(Edge o) {
			if(this.v > o.v) {
				return 1;
			}
			else if(this.v < o.v) {
				return -1;
			}
			return 0;
		}
		
	}
}
