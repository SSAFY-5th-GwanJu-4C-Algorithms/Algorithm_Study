package pgms72413;

import java.util.ArrayList;
import java.util.PriorityQueue;

class Solution {

    public static void main(String[] args) {

        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
        System.out.println(new Solution().solution(6, 4, 6, 2, fares));

    }

    private static final int INF = Integer.MAX_VALUE;

    public int solution(int n, int s, int a, int b, int[][] fares) {

        ArrayList<ArrayList<int[]>> graph = new ArrayList<>(n);
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int[] fare : fares) {
            graph.get(fare[0]).add(new int[] {fare[1], fare[2]});
            graph.get(fare[1]).add(new int[] {fare[0], fare[2]});
        }
 
        PriorityQueue<int[]> pq = new PriorityQueue<>((p, q) -> p[1] - q[1]);
        int[][] distance = new int[3][n + 1];

        for (int i = 0; i <= n; i++)
            distance[0][i] = distance[1][i] = distance[2][i] = INF;

        distance[0][s] = distance[1][a] = distance[2][b] = 0;
        dijkstra(graph, pq, s, distance[0], new boolean[n + 1]);
        dijkstra(graph, pq, a, distance[1], new boolean[n + 1]);
        dijkstra(graph, pq, b, distance[2], new boolean[n + 1]);

        int answer = INF;
        for (int i = 0; i <= n; i++) {
            int dist = distance[0][i] + distance[1][i] + distance[2][i];
            if (answer > dist) answer = dist;
        }

        return answer;

    }

    private void dijkstra(ArrayList<ArrayList<int[]>> G, PriorityQueue<int[]> Q, int v, int[] D, boolean[] visited) {

        Q.offer(new int[] {v, 0});

        while (!Q.isEmpty()) {

            v = Q.poll()[0];

            if (visited[v]) continue;
            visited[v] = true;

            ArrayList<int[]> adjList = G.get(v);
            int size = adjList.size();

            for (int i = 0; i < size; i++) {

                int w = adjList.get(i)[0];
                int d = D[v] + adjList.get(i)[1];

                if (D[w] > d) {
                    D[w] = d;
                    Q.offer(new int[] {w, D[w]});
                }

            }

        }

    }

}