import java.util.ArrayList;
import java.util.Collections;

/** <h4>소트인사이드 - Collections.sort(List<Integer>) */
public class BJ01427_4 {

    private static StringBuilder sb = new StringBuilder();
    private static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        int c;
        while((c = System.in.read()) > 13) list.add(c - '0');

        Collections.sort(list);
        Collections.reverse(list);

        for (int n : list) sb.append(n);
        System.out.println(sb);

    }
    
}
