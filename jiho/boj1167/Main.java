import java.util.ArrayList;
import java.util.LinkedList;

/**
 * BOJ 1167 트리의 지름
 * @author jihogrammer@gmail.com
 * @see http://boj.kr/36e4b21bd7894304a033bd3d6096c51f
 * @see https://blog.naver.com/jihogrammer/222301059538
 */
class Main {

    static class Node {

        int to, weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

    }

    public static void main(String[] args) throws Exception {

        int V = read() + 1; // 정점 수: 인덱스 번호 맞추기 위해 1 더함

        ArrayList<ArrayList<Node>> graph = new ArrayList<>(V);     // 그래프 생성
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());  // 인접리스트 생성

        for (int i = 1; i < V; i++) {

            int to = read();
            ArrayList<Node> adjList = graph.get(to); // i번 정점의 인접리스트

            while ((to = read()) > 0)              // 입력값 할당하면서
                adjList.add(new Node(to, read())); // 인접리스트에 정점 담기

        }

        // BFS 두 번 수행하니까 Queue 미리 생성해 둠
        LinkedList<Integer> queue = new LinkedList<>();

        Node leafNode = bfs(1, graph, queue);           // Leaf Node 찾기
        Node diameter = bfs(leafNode.to, graph, queue); // Diameter 구하기

        System.out.print(diameter.weight); // 결과 출력

    }

    private static Node bfs(int V, ArrayList<ArrayList<Node>> G, LinkedList<Integer> Q) {

        int N = G.size();
        boolean[] visited = new boolean[N]; // 방문 배열
        int[] distance = new int[N];        // 매개변수로 받은 V 정점으로부터 각 정점 거리

        visited[V] = true; // 방문 처리
        Q.offer(V);        // 큐에 넣기

        while (!Q.isEmpty()) {

            V = Q.poll(); // 정점 번호

            ArrayList<Node> adjList = G.get(V); // 뽑은 정점의 인접리스트
            int size = adjList.size();          // Depth Check를 위한 큐의 크기

            for (int i = 0; i < size; i++) { // Depth별로 BFS 수행

                int t = adjList.get(i).to;     // 새로 접근할 정점 번호
                if (visited[t]) continue;      // 이미 방문했다면 다음으로
                int w = adjList.get(i).weight; // 새로 접근할 정점까지의 거리

                distance[t] = distance[V] + w; // 거리값 갱신
                visited[t] = true; // 방문 처리
                Q.offer(t);        // 큐에 넣기

            }

        }

        int idx = 0, max = 0; // Leaf Node, Diameter 구할 준비

        for (int i = 1; i < N; i++)  // 각 정점까지의 거리중 가장 먼 정점 고르기
            if (max < distance[i]) {
                max = distance[i];
                idx = i;
            }

        return new Node(idx, max);

    }

    /**
     * Input Process - Only Positive Integer
     * @see https://blog.naver.com/jihogrammer/222281999239
     */
    private static int read() throws Exception {

        int c, N = System.in.read() - 48;
        while ((c = System.in.read()) > 32) N = 10 * N + c - 48;

        return N;

    }

}