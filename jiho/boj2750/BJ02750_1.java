import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/** <h4>수 정렬하기</h4>
 * PriorityQueue<Integer> 활용 */
public class BJ02750_1 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();
    private static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws Exception {

        for (int N = Integer.parseInt(br.readLine()); N > 0; N--)
            pq.add(Integer.parseInt(br.readLine()));

        while (!pq.isEmpty()) sb.append(pq.poll()).append('\n');

        System.out.println(sb);
        
    }
    
}
