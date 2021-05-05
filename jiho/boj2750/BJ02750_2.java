import java.io.BufferedReader;
import java.io.InputStreamReader;

/** <h4>수 정렬하기</h4>
 * boolean array index 활용 */
public class BJ02750_2 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static boolean[] arr = new boolean[2001];

    public static void main(String[] args) throws Exception {

        for (int N = Integer.parseInt(br.readLine()); N > 0; N--)
            arr[Integer.parseInt(br.readLine()) + 1000] = true;
        
        for (int i = 0; i < 2001; i++)
            if (arr[i]) sb.append(i - 1000).append('\n');
        
        System.out.println(sb);
        
    }
    
}
