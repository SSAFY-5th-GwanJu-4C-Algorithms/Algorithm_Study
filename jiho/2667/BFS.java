/**
 * BOJ 2667 단지번호붙이기 - BFS
 * @author 김지호 jihogrammer@gmail.com
 * @see https://blog.naver.com/jihogrammer/222305565939
 */
class BFS {

    // 방향벡터
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    // Using Bitmask
    private static final int BIT = 5, MASK = ~(-1 << BIT);

    public static void main(String[] args) throws Exception {

        // 단지 크기 읽기
        int c, N = System.in.read() & 15;
        while ((c = System.in.read()) > 13) N = (N << 3) + (N << 1) + (c & 15);

        int[] complex = new int[N];             // 단지 배열 - Bitmask
        int[] complexArea = new int[N * N + 1]; // 단지 크기별 개수 저장 배열

        for (int x = 0; x < N; x++) {        // 단지 맵 채우기
            for (int y = 0; y < N; y++)
                if (System.in.read() == 48)  // 편의상 빈칸을 마킹
                    complex[x] |= 1 << y;
            
            // 개행 문자 처리
            c = System.in.read(); // White Space
            if (c == 13) System.in.read();
        }

        int complexNum = 0;            // 단지 수
        int[] queue = new int[N << 1]; // BFS 준비 - 배열로 Queue 구현

        for (int x = 0; x < N; x++)
        for (int y = 0; y < N; y++)
            if ((complex[x] & (1 << y)) == 0) { // 단지를 만나면 BFS 수행하고 빈칸으로 변경됨
                complexNum++;                   // 따라서 바로 단지 수를 올려주고
                complexArea[bfs(x, y, N, complex, queue)]++; // BFS 수행한 결과의 단지크기 인덱스의 값을 증가
            }

        StringBuilder sb = new StringBuilder(); // 결과 출력 준비
        sb.append(complexNum).append('\n');     // 단지 수 출력

        c = complexArea.length;
        for (int i = 0; i < c; i++) // 존재하는 단지 크기 오름차순으로 출력
            for (int j = complexArea[i]; j > 0; j--)
                sb.append(i).append('\n');

        System.out.print(sb); // 최종 결과 출력

    }

    private static int bfs(int x, int y, int N, int[] complex, int[] queue) {

        int area = 0;
        int offer = 0;
        int poll = -1;
        int queueEnd = N << 1;

        // == queue.offer();
        complex[x] |= 1 << y;
        queue[offer] = (x << BIT) | y;

        while (offer != poll) { // == !queue.isEmpty()

            // 한 칸 poll 했으니 단지 크기 올려줌
            area++;

            // == queue.offer();
            if (++poll == queueEnd) poll = 0;
            x = queue[poll];
            y = x & MASK;
            x >>= BIT;

            for (int d = 0; d < 4; d++) { // 4방 탐색

                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx == N || ny == N) continue;
                if ((complex[nx] & (1 << ny)) != 0) continue;

                // == queue.offer();
                complex[nx] |= 1 << ny;
                if (++offer == queueEnd) offer = 0;
                queue[offer] = (nx << BIT) | ny;

            }

        }

        return area; // 단지 크기 반환

    }

}