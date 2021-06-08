import java.util.ArrayList;
import java.util.List;

/**
 * @author 김지호 jihogrammer@gmail.com
 */
public class UpperBound {

    public static void main(String[] args) throws Exception {
        run();
    }

    /**
     * upperBound()를 수행하기 위한 준비 후, for문을 통해 0부터 10까지 upperBound()의 결과를 출력한다.
     */
    private static void run() {

        List<int[]> list = new ArrayList<>();
        list.add(Data.getArr1());
        list.add(Data.getArr2());
        list.add(Data.getArr3());

        int[] arr;
        int length;

        System.out.println("\n<<<<<<< UPPER BOUND TEST >>>>>>>\n0부터 10까지 upperBound() 수행 결과\n");

        for (int i = 0; i < 3; i++) {

            arr = list.get(i);
            length = arr.length;

            printArr(arr);
            for (int k = 0; k <= 10; k++) {
                printHeader(k);
                int resultIndex = upperBound(k, 0, length, arr);
                printBody(resultIndex);
            }

            System.out.println();
            System.out.println();

        }

    }

    /**
     * <h3>Upper Bound</h3>
     * 
     * 정렬된 배열에서 KEY보다 큰 값이 위치한 제일 작은 인덱스를 반환한다.
     * 
     * @param k - key : 배열에서 찾으려고 하는 값
     * @param l - left : 배열에서 찾고자 하는 범위의 왼쪽 값
     * @param r - right : 배열에서 찾고자 하는 범위의 오른쪽 값
     * @param a - array : 배열
     * @return KEY보다 최초로 큰 값이 위치한 인덱스
     */
    private static int upperBound(int k, int l, int r, int[] a) {

        // 예외처리 - 원소가 하나일 경우
        // Example https://en.wikipedia.org/wiki/Upper_and_lower_bounds
        // The set S = {42} has 42 as both an upper bound and a lower bound;
        // all other numbers are either an upper bound or a lower bound for that S.
        // 원소가 하나일 경우 존재하는 그 원소를 반환한다.
        // 그냥 무시해도 된다.
        if (a.length == 1)
            return 0;

        int m; // 중앙값

        while (l < r) {

            m = (l + r) >> 1; // (l + r) / 2

            if (a[m] <= k)
                l = m + 1;
            else
                r = m;

        }

        return l;

    }

    //////////////////////////////////////////////////////////////////////////
    private static final String box = "----+";

    private static void printArr(int[] arr) {

        StringBuilder sb = new StringBuilder("+");
        int len = arr.length;

        for (int i = 0; i < len; i++)
            sb.append(box);

        sb.append('\n').append('|');
        for (int i = 0; i < len; i++)
            sb.append(String.format("%3d", arr[i])).append(' ').append('|');

        sb.append('\n').append('+');
        for (int i = 0; i < len; i++)
            sb.append(box);

        System.out.println(sb);

    }

    private static void printHeader(int k) {
        System.out.print(new StringBuilder("Key ").append(String.format("%2d", k)).append(" -> "));
    }

    private static void printBody(int idx) {
        System.out.println(new StringBuilder(String.format("%2d", idx)).append(" 번 인덱스"));
    }

    private static class Data {

        private static int[] arr1 = { 1, 1, 1, 2, 2, 2, 3, 3, 4, 5, 6, 7 };
        private static int[] arr2 = { 3, 4, 5, 6, 7, 8, 9 };
        private static int[] arr3 = { 5 };

        public static int[] getArr1() {
            return arr1;
        }

        public static int[] getArr2() {
            return arr2;
        }

        public static int[] getArr3() {
            return arr3;
        }

    }

}
