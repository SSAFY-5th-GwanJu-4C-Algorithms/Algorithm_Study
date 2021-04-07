/**
 * BOJ 2156 포도주 시식 - DP Bottom Up
 * @author 김지호 jihogrammer@gmail.com
 * @see https://blog.naver.com/jihogrammer/222295512457
 */
class Main {

    public static void main(String[] args) throws Exception {

        int N = read(); // N값 읽기

        if (N < 3) { // N 값이 작을 경우 예외처리
            int wine = read();
            if (N > 1) wine += read();
            System.out.print(wine);
            return;
        }

        int[] W = new int[N + 1]; // 포도주 양 저장 배열
        int[] D = new int[N + 1]; // DP 배열

        // 각 잔에 들어있는 포도주의 양 읽기
        for (int i = 1; i <= N; i++) W[i] = read();

        // DP 초기값 채우기
        D[2] = (D[1] = W[1]) + W[2];

        // 취하기
        for (int i = 3; i <= N; i++) {
            D[i] = max(D[i - 2], D[i - 3] + W[i - 1]) + W[i];
            D[i] = max(D[i], D[i - 1]);
        }

        // 결과 출력
        System.out.print(D[N]);

    }

    private static int max(int a, int b) {
        return a > b ? a : b;
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