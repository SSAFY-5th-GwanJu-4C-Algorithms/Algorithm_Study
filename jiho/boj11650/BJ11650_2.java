import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BJ11650_2 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
        @Override
        public int compare(int[] o1, int[] o2) {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            else return o1[0] - o2[0];
        }
    });

    private static String print(int[] arr) {
        return arr[0] + " " + arr[1] + "\n";
    }

    public static void main(String[] s) throws Exception {

        final int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int[] arr = new int[2];
            s = br.readLine().split(" ");
            arr[0] = Integer.parseInt(s[0]);
            arr[1] = Integer.parseInt(s[1]);
            pq.add(Arrays.copyOf(arr, 2));
        }

        while (!pq.isEmpty()) sb.append(print(pq.poll()));

        System.out.print(sb);
        
    }
    
}