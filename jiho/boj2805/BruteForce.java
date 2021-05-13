/**
 * 백준 2805 나무 자르기
 * 
 * @author jihogrammer@gmail.com
 * 
 *         코드 풀이에 대한 내용은 다음을 확인하세요.
 * @see https://blog.naver.com/jihogrammer/222343935333
 * 
 * @description 입력 시 나무의 최대 높이를 구해 두고, UpperBound 기반의 Binary Search 수행 후,
 *              완전탐색(BruteForce)으로 정답을 구한다.
 * 
 */
class Main {

    public static void main(String[] args) throws Exception {

        int N = read(), M = read(), l = 0, r = 0;
        int[] trees = new int[N];
        boolean isEqual = false;

        for (int i = 0; i < N; i++)
            if (r < (trees[i] = read()))
                r = trees[i];

        while (l <= r) {

            int k, m = l + r >> 1;

            long sum = 0;
            for (int tree : trees)
                if ((k = tree - m) > 0)
                    sum += k;

            if (sum > M)
                l = m + 1;
            else {
                r = m - 1;
                if (sum == M)
                    if (isEqual)
                        break;
                    else
                        isEqual = true;
            }

        }

        System.out.print(isEqual ? l : l - 1);

    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

}