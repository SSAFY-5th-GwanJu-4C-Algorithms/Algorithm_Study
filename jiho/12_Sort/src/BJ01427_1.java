import java.util.Collections;
import java.util.PriorityQueue;

/** <h4>소트인사이드</h4>
 * PriorityQueue<Integer> 활용 */
public class BJ01427_1 {

    private static StringBuilder sb = new StringBuilder();

    /** PriorityQueue reverse */
    private static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws Exception {

        int n;
        while ((n = System.in.read()) > 13) pq.add(n - '0');
        while (!pq.isEmpty()) sb.append(pq.poll());
        System.out.println(sb);
        
    }
    
}
