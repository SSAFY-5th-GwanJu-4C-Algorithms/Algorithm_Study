import java.util.PriorityQueue;

/**
 * BOJ 2667 단지번호붙이기 - DFS
 * @author 김지호 jihogrammer@gmail.com
 * @see https://blog.naver.com/jihogrammer/222305565939
 */
class PriorityQueueDFS {

    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int N, area;

    public static void main(String[] args) throws Exception {

        int c;
        while ((c = System.in.read()) > 13) N = (N << 3) + (N << 1) + (c & 15);

        int[] complex = new int[N]; // Using bitmask
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++)
                if (System.in.read() == 48)
                    complex[x] |= 1 << y;
            System.in.read(); // White Space
        }

        int complexNum = 0;
        for (int x = 0; x < N; x++)
        for (int y = 0; y < N; y++)
            if ((complex[x] & (1 << y)) == 0) {
                complexNum++;
                area = 0;
                dfs(x, y, complex);
                pq.offer(area);
            }

        StringBuilder sb = new StringBuilder();
        sb.append(complexNum).append('\n');

        while (!pq.isEmpty()) sb.append(pq.poll()).append('\n');

        System.out.print(sb);

    }

    private static void dfs(int x, int y, int[] complex) {

        area++;
        complex[x] |= 1 << y;

        for (int d = 0; d < 4; d++) {

            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || ny < 0 || nx == N || ny == N) continue;
            if ((complex[nx] & (1 << ny)) != 0) continue;

            dfs(nx, ny, complex);

        }

    }

}