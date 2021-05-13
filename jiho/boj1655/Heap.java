/**
 * 백준 1655 가운데를 말해요
 * 
 * @author jihogrammer@gmail.com
 * 
 * @see - 블로그 게시글 작성하게 되면 업데이트 하겠습니다.
 * 
 * @description 보통의 방식과 동일하게 구현했습니다. 다만 직접 PriorityQueue를 Heap 배열로 직접 구현하여, 필요한
 *              메소드만 구현한 뒤 성능을 개선했습니다. 자세한 풀이는 블로그 게시글을 작성하게 되면 첨부하겠습니다.
 * 
 */
public class Main {

    private static final boolean ASC = true, DESC = false;
    private static final int TOP = 1;

    public static void main(String[] args) throws Exception {

        int N = read();
        StringBuilder sb = new StringBuilder();

        int lSize = 0, rSize = 0;
        int[] leftHeap = new int[(N >> 1) + 2];
        int[] rightHeap = new int[(N >> 1) + 2];

        while (N-- > 0) {

            if (lSize > rSize) {
                rightHeap[++rSize] = read();
                heapify(rightHeap, rSize, ASC);
            } else {
                leftHeap[++lSize] = read();
                heapify(leftHeap, lSize, DESC);
            }

            if (rSize > 0 && leftHeap[TOP] > rightHeap[TOP]) {

                int l = leftHeap[TOP];
                int r = rightHeap[TOP];

                remove(leftHeap, lSize, DESC);
                remove(rightHeap, rSize, ASC);

                leftHeap[lSize] = r;
                rightHeap[rSize] = l;

                heapify(leftHeap, lSize, DESC);
                heapify(rightHeap, rSize, ASC);

            }

            sb.append(leftHeap[TOP]).append('\n');

        }

        System.out.print(sb);

    }

    private static void heapify(int[] heap, int size, boolean flag) {

        while (size > 1) {

            if (!swap(heap, size, flag))
                break;

            size >>= 1;

        }

    }

    private static void remove(int[] heap, int size, boolean flag) {

        heap[TOP] = heap[size--];

        int i = 1;

        while ((i <<= 1) <= size) {

            if (i < size + 1 && check(flag, heap[i], heap[i + 1]))
                i += 1;

            if (!swap(heap, i, flag))
                break;

        }

    }

    private static boolean swap(int[] heap, int n, boolean flag) {

        int p = heap[n >> 1];
        int c = heap[n];

        if (!check(flag, p, c))
            return false;

        heap[n >> 1] = c;
        heap[n] = p;

        return true;

    }

    private static boolean check(boolean flag, int a, int b) {

        if (flag && a > b)
            return true;
        if (!flag && a < b)
            return true;

        return false;

    }

    private static int read() throws Exception {

        int c, n = System.in.read() & 15;

        boolean isNegative = n == 13;
        if (isNegative)
            n = System.in.read() & 15;

        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);

        return isNegative ? ~n + 1 : n;

    }

}