import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <h4>소트인사이드</h4>
 * Integer Array Index 활용
 */
public class BJ01427_2 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();

    private static int[] arr = new int[10];

    public static void main(String[] args) throws Exception {

        int n, i;

        while ((n = br.read()) > 13) arr[n - '0']++;

        for (i = 9; i >= 0; i--)
            for (n = arr[i]; n > 0; n--)
                sb.append(i);

        System.out.println(sb);
        
    }
    
}
