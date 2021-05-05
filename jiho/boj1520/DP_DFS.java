/**
 * BOJ 1520 내리막 길 - DFS
 * @author 김지호 jihogrammer@gmail.com
 * @see https://blog.naver.com/jihogrammer
 */
class Main {

    private static int M, N;

    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {

        M = read(); N = read();
        int[][] map = new int[M + 2][N + 2]; // 원래보다 2 크게 만들어 유효성 판별 비교 수 절약
        int[][] DP = new int[M + 2][N + 2];

        for (int x = 1; x < M + 1; x++)
        for (int y = 1; y < N + 1; y++) {
            map[x][y] = read();
            DP[x][y] = -1;
        }

        System.out.print(dfs(1, 1, map, DP));

    }

    // DP에 값을 채워가면서 DFS 수행
    private static int dfs(int x, int y, int[][] map, int[][] DP) {

        if (x == M && y == N) return 1;
        if (DP[x][y] > -1) return DP[x][y]; // 16번 라인 : 여기서 걸러짐

        int dp = 0;

        for (int d = 0; d < 4; d++) {

            int nx = x + dx[d];
            int ny = y + dy[d];

            if (map[nx][ny] < map[x][y]) // 16번 라인 : 그래서 여기만 비교하면 된다.
                dp += dfs(nx, ny, map, DP);

        }

        return DP[x][y] = dp; // DP에 값 갱신하면서 return -> memoization

    }

    /**
     * Input Process
     * @see https://blog.naver.com/jihogrammer/222281999239
     */
    private static int read() throws Exception {

        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);

        return n;

    }

}