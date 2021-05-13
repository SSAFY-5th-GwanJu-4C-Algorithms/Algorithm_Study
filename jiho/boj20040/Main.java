/**
 * 백준 20040 사이클 게임
 * 
 * @author jihogrammer@gmail.com
 * 
 *         코드 풀이에 대한 내용은 다음을 확인하세요. 본 코드와 내용이 살짝 다름.
 * @see https://blog.naver.com/jihogrammer/222345615295
 * 
 * @description Path Compression, RANK를 적용한 풀이 부모가 자기 자신일 경우 -1로 초기화하고 사용. 두 대표가
 *              UNION 될 경우 한 쪽이 (-1) + (-1) = -2로 RANK가 갱신되고, 나머지 한쪽은 실제 부모를
 *              갱신한다.
 * 
 */
class Main {

    public static void main(String[] args) throws Exception {

        int N = read(), M = read(), s[] = new int[N + 1], round;

        for (round = 0; round < M; round++) {
            int v = read() + 1, w = read() + 1;
            if (s[v] == 0)
                s[v] = -1;
            if (s[w] == 0)
                s[w] = -1;
            if (union(v, w, s))
                break;
        }

        System.out.print(round == M ? 0 : round + 1);

    }

    private static boolean union(int v, int w, int[] s) {

        v = find(v, s);
        w = find(w, s);

        if (v == w)
            return true;

        if (s[v] < s[w]) {
            s[v] += s[w];
            s[w] = v;
        } else {
            s[w] += s[v];
            s[v] = w;
        }

        return false;

    }

    private static int find(int v, int[] s) {
        return s[v] < 0 ? v : (s[v] = find(s[v], s));
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

}