/**
 * BOJ 2667 단지번호붙이기 - DFS
 * @author 김지호 jihogrammer@gmail.com
 * @see https://blog.naver.com/jihogrammer/222305565939
 */
class DFS {

    // 방향벡터
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    // 단지 크기, 단지 넓이
    private static int N, area;

    public static void main(String[] args) throws Exception {

        // 단지 크기 입력 받기
        int c;
        while ((c = System.in.read()) > 13) N = (N << 3) + (N << 1) + (c & 15);

        int[] complex = new int[N];             // 단지 배열 - Bitmask
        int[] complexSize = new int[N * N + 1]; // 단지 크기별 개수 저장 배열

        for (int x = 0; x < N; x++) {       // 단지 맵 채우기
            for (int y = 0; y < N; y++)
                if (System.in.read() == 48) // 편의상 빈칸을 마킹
                    complex[x] |= 1 << y;

            // 개행 문자 처리
            c = System.in.read(); // White Space
            if (c == 13) System.in.read();
        }

        int complexNum = 0; // 단지 수
        for (int x = 0; x < N; x++)
        for (int y = 0; y < N; y++)
            if ((complex[x] & (1 << y)) == 0) { // 단지를 만나면 DFS 수행하고 빈칸으로 변경됨
                complexNum++;         // 단지 수 증가
                area = 0;             // 단지 넓이 초기화
                dfs(x, y, complex);   // DFS 수행
                complexSize[area]++;  // 넓이에 해당하는 인덱스 값 증가
            }

        StringBuilder sb = new StringBuilder();
        sb.append(complexNum).append('\n');

        int size = complexSize.length;
        for (int i = 0; i < size; i++)
            for (int j = complexSize[i]; j > 0; j--)
                sb.append(i).append('\n');

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