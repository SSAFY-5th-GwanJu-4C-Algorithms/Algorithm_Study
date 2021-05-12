import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

/**
 * <h4>소트인사이드 - 3 Way Quick Sort</h4>
 * <a href="https://johngrib.github.io/wiki/quick-sort/#%EB%B6%84%ED%95%A0-%EA%B5%90%ED%99%98-%EC%A0%95%EB%A0%AC-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98">
 *   참고
 * </a>
 */
public class BJ01427_3 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();

    public static void sort(int[] array) {
        Collections.shuffle(Arrays.asList(array));
        sort(array, 0, array.length -1);
    }
    
    public static void sort(int[] array, int lo, int hi) {
        if (hi <= lo) return;
        
        int lt = lo, i = lo + 1, gt = hi;
        int v = array[lo];

        while (i <= gt) {
            int cmp = array[i] == v ? 0 : (array[i] > v ? 1 : -1);

            if      (cmp < 0) swap(array, lt++, i++);
            else if (cmp > 0) swap(array, i, gt--);
            else i++;
        }

        sort(array, lo, lt - 1);
        sort(array, gt + 1, hi);
    }
    
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) throws Exception {

        String input = br.readLine();
        int N = input.length();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) arr[i] = input.charAt(i) - '0';
        
        sort(arr);
    
        for (int i = N - 1; i >= 0; i--) sb.append(arr[i]);

        System.out.println(sb);

    }
    
}
