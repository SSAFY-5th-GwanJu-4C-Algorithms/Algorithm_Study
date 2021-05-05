/**
 * BOJ 1956 운동
 * @author jihogrammer@gmail.com
 * @see http://boj.kr/0ec1d5eee2ad475da2d77aeda16a7c54
 * @see https://blog.naver.com/jihogrammer/222299717098
 */
class Main {

    private static final int INF = ~0 >>> 2; // Integer.MAX_VALUE / 2

    public static void main(String[] args) throws Exception {

        int V = read() + 1; // 정점 수. 정점 번호 맞추기 위해 1을 더해 둔다.
        int E = read();     // 간선 수

        if (E < 2) { // 간선 수가 2개 미만이면 Cycle이 생길리가 없다. 바로 종료시킨다.
            System.out.print(-1);
            return;
        }

        int[][] G = new int[V][V]; // Graph
        for (int i = 1; i < V; i++) for (int j = 1; j < V; j++) G[i][j] = INF; // Initialize

        for (int i = 0; i < E; i++) { // 입력값 할당

            int from   = read(); // 일일이 이렇게 변수에 담아서 사용하는 게
            int to     = read(); // 좋은지는 모르겠지만,
            int weight = read(); // 성능은 나중에 테스트 해보자

            G[from][to] = weight; // 인접행렬에 값 채우기. 방향 그래프이므로 한 쪽씩 채운다.

        }

        // Floyd-Warshall
        for (int k = 1; k < V; k++) {           // 경; 경유지
            for (int i = 1; i < V; i++) {       // 출; 출발지
                if (i == k) continue; // 경유지와 출발지가 겹치는 일은 피해주자
                for (int j = 1; j < V; j++) {   // 도; 도착지
                    int compare = G[i][k] + G[k][j];           // 쉽게 말해서
                    if (G[i][j] > compare) G[i][j] = compare;  // "경유지 거쳐서 vs 직접"
                }
            }
        }

        int ans = INF; // 결과

        for (int i = 1; i < V; i++)           // G[1][1], G[2][2], ... 순으로 검사해서
            if (ans > G[i][i]) ans = G[i][i]; // 최소값 찾기. 이렇게 하면 V번만 검사함

        if (ans == INF) System.out.print(-1);  // 결과 출력
        else            System.out.print(ans);

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