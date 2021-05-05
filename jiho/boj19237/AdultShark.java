class Main {

    private static int N, M, K;

    // 사용할 상수들
    private static final int INFO = 0, DIR = 1;

    // 바다 정보
    private static final int NBIT = (1 << 4);
    private static final int NMASK = ~(-1 << NBIT);

    // 상어 정보
    private static final int IBIT = (1 << 3);
    private static final int IMASK = ~(-1 << IBIT);

    // 상어 방향 우선순위 정보
    private static final int DBIT = (1 << 1);
    private static final int DMASK = ~(-1 << DBIT);

    // 방향벡터
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {

        N = read(); M = read(); K = read();
        int[][] sea = new int[N][N];
        int[][] shark = new int[M + 1][2];

        // 바다 상태, 상어 위치 읽기
        for (int x = 0; x < N; x++)
        for (int y = 0; y < N; y++) {

            int n = read();
            if (n == 0) continue;

            sea[x][y] = (n << NBIT) | K;
            shark[n][INFO] = (x << IBIT) | y;

        }

        // 상어 현재 방향 읽기
        for (int i = 1; i <= M; i++)
            shark[i][INFO] |= (read() - 1) << NBIT;

        // 상어 방향별 우선순위 방향 읽기
        for (int i = 1; i <= M; i++)
            for (int j = 0; j < NBIT; j++)
                shark[i][DIR] |= (read() - 1) << (DBIT * j);

        // 결과 출력
        System.out.print(count(sea, shark));

    }

    // 초 세기
    private static int count(int[][] sea, int[][] shark) {

        int second = 0;

        while (true) {

            // 번호가 큰 애들부터 이동시켜야 우선순위 높은 애들이 작은 애들 쫓아냄
            for (int i = M; i > 0; i--)
                if (shark[i] != null) // 살아있는 상어만 움직이기
                    move(sea, shark, i);

            // 초 세기
            if (++second > 1000) return -1;

            // 남은 상어 수 세기
            if (getRemainShark(shark) == 1) break;

            // 냄새 영역 줄이기
            fade(sea);

        }

        return second;

    }

    // 상어 움직이기
    private static void move(int[][] sea, int[][] shark, int i) {

        int x = (shark[i][INFO] >> IBIT) & IMASK;        // 현재 상어 좌표
        int y = shark[i][INFO] & IMASK;
        int d = (shark[i][INFO] >> NBIT) & IMASK;        // 현재 상어 방향
        int td = (shark[i][DIR] >> (IBIT * d)) & IMASK;  // 이동 우선순위

        // 빈칸으로 움직이면 true, 자기 영역으로 돌아가면 false
        boolean flag = canMove(sea, x, y);

        for (int j = 0; j < 4; j++) {

            int cd = td & DMASK; // 움직이려는 방향
            td >>= DBIT;
            int nx = x + dx[cd]; // 새로운 좌표
            int ny = y + dy[cd];

            // 유효성 판별
            if (nx < 0 || ny < 0 || nx == N || ny == N) continue;

            if (flag) { // 빈 칸으로 이동하는 경우

                // 냄새가 나면 다른 칸으로
                if ((sea[nx][ny] & NMASK) > 0) continue;

                // 새로 이동한 자리에 우선순위가 낮은 상어가 있을 경우 그 상어 쫓아내기
                int prev = sea[nx][ny] >> NBIT;
                if (prev > 0) shark[prev] = null;

                // 상어 이동
                sea[nx][ny] = (i << NBIT);

            } else { // 자기 영역으로 이동하는 경우

                // 자기 영역이 아니면 다른 칸으로
                if ((sea[nx][ny] >> NBIT) != i) continue;

                // 상어 이동
                sea[nx][ny] = (i << NBIT) | 1001;

            }

            // 이전 위치에 냄새 풍기기
            sea[x][y] = (i << NBIT) | K;

            // 상어 정보 갱신
            shark[i][INFO] = (cd << NBIT) | (nx << IBIT) | ny;

            return;

        }

    }

    // 움직일 수 있는지 확인
    private static boolean canMove(int[][] sea, int x, int y) {

        for (int j = 0; j < 4; j++) {

            int nx = x + dx[j];
            int ny = y + dy[j];

            if (nx < 0 || ny < 0 || nx == N || ny == N) continue; // 맵 벗어나는지
            if ((sea[nx][ny] & NMASK) > 0) continue;              // 냄새나는지

            return true; // 빈칸으로 이동 가능

        }

        return false; // 빈칸으로는 못 가고 자기 영역으로 되돌아가기

    }

    // 남은 상어 수 세기
    private static int getRemainShark(int[][] shark) {

        int cnt = 0;

        for (int i = 1; i <= M; i++)
            if (shark[i] != null) cnt++;

        return cnt;

    }

    // 냄새 빼기
    private static void fade(int[][] sea) {

        for (int j = 0; j < N; j++)
        for (int k = 0; k < N; k++) {

            // 접근한 칸 냄새
            int smell = sea[j][k] & NMASK;

            // 냄새가 나면
            if (smell > 0) {

                sea[j][k] -= 1; // 냄새 빼기

                // 냄새가 다 빠지면 흔적도 지우기
                if (smell == 1) sea[j][k] = 0;

            }

            // 현재 상어가 있는 자리에 냄새 뿌리기
            if (sea[j][k] > 0 && smell == 0) {

                int n = sea[j][k] & (NMASK << NBIT);
                sea[j][k] = n | K;

            }

        }

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