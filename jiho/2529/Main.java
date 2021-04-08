/**
 * BOJ 2529 부등호 - DFS
 * @author 김지호 jihogrammer@gmail.com
 * @see https://blog.naver.com/jihogrammer/222303039854
 */
class Main {

    private static int K, inequality;

    public static void main(String[] args) throws Exception {

        K = read() - 47; // 부등호 개수 + 1
        inequality = 0;  // 부등호 배열 using bitmask

        // 비트가 0이면 '<', 1이면 '>'
        for (int i = 1; i < K; i++)
            if (read() == '>') inequality |= (1 << i);

        StringBuilder min = new StringBuilder();
        StringBuilder max = new StringBuilder();
        dfs(0, 0, new int[K], min, true);  // 작은 수 찾기
        dfs(0, 0, new int[K], max, false); // 큰 수 찾기

        max.append('\n').append(min);
        System.out.print(max);

    }

    private static void dfs(int cnt, int visited, int[] sequence, StringBuilder sb, boolean isMin) {

        if (sb.length() > 0) return;

        if (cnt > 1) {

            // is lower than; 이번에 사용될 부등호. true면 '<', false면 '>'
            boolean islt = (inequality & (1 << (cnt-1))) == 0;

            // 부등호 비교하기
            // '<'인데 좌변이 더 클 경우 return
            if (islt && sequence[cnt-2] > sequence[cnt-1]) return;
            // '>'인데 우변이 더 클 경우 return
            if (!islt && sequence[cnt-2] < sequence[cnt-1]) return;

        }

        // 부등호 + 1 개의 숫자를 골랐을 경우 - 기저조건
        if (cnt == K) {

            for (int i = 0; i < K; i++)
                sb.append(sequence[i]);

            return;

        }

        int d = isMin ? 1 : -1;  // delta
        int i = isMin ? -1 : 10; // iterator

        while (true) {

            i += d;
            if (isMin && i > 9) return;
            if (!isMin && i < 0) return;

            // 이미 사용한 수라면 다음 수로 넘어가기
            if ((visited & (1 << i)) > 0) continue;

            // 선택한 수 배열에 채우기
            sequence[cnt] = i;

            // 방문처리 및 고른 수 포함시켜 넘기기
            dfs(cnt + 1, visited | (1 << i), sequence, sb, isMin);

        }

    }

    /**
     * Input Process
     * @return ASCII CODE
     * @see https://blog.naver.com/jihogrammer/222281999239
     */
    private static int read() throws Exception {
        int n = System.in.read(); // Value
        System.in.read();         // White Space
        return n;
    }

}