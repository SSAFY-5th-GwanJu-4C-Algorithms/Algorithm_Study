import java.util.PriorityQueue;

/**
 * BOJ 2667 단지번호붙이기 - BFS
 * @author 김지호 jihogrammer@gmail.com
 * @see https://blog.naver.com/jihogrammer
 */
class PriorityQueueBFS {

    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    private static final int BIT = 5, MASK = ~(-1 << BIT);

    public static void main(String[] args) throws Exception {

        int c, N = System.in.read() & 15;
        while ((c = System.in.read()) > 13) N = (N << 3) + (N << 1) + (c & 15);

        int[] complex = new int[N];
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++)
                if (System.in.read() == 48)
                    complex[x] |= 1 << y;
            System.in.read(); // White Space
        }

        int complexNum = 0;
        int[] queue = new int[N << 1];

        for (int x = 0; x < N; x++)
        for (int y = 0; y < N; y++)
            if ((complex[x] & (1 << y)) == 0) {
                complexNum++;
                pq.offer(bfs(x, y, N, complex, queue));
            }

        StringBuilder sb = new StringBuilder();
        sb.append(complexNum).append('\n');

        while (!pq.isEmpty()) sb.append(pq.poll()).append('\n');

        System.out.print(sb);

    }

    private static int bfs(int x, int y, int N, int[] complex, int[] queue) {

        int area = 0;
        int offer = 0;
        int poll = -1;
        int queueEnd = N << 1;

        complex[x] |= 1 << y;
        queue[offer] = (x << BIT) | y;

        while (offer != poll) {

            area++;
            if (++poll == queueEnd) poll = 0;
            x = queue[poll];
            y = x & MASK;
            x >>= BIT;

            for (int d = 0; d < 4; d++) {

                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx == N || ny == N) continue;
                if ((complex[nx] & (1 << ny)) != 0) continue;

                complex[nx] |= 1 << ny;
                if (++offer == queueEnd) offer = 0;
                queue[offer] = (nx << BIT) | ny;

            }

        }

        return area;

    }

}