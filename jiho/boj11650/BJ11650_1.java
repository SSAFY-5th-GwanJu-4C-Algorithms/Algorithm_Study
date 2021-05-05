import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BJ11650_1 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static PriorityQueue<XY> pq = new PriorityQueue<>();

    class XY implements Comparable<XY> {
        int x;
        int y;

        XY(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(XY o) {
            if (this.x == o.x) return this.y - o.y;
            else return this.x - o.x;
        }

        @Override
        public String toString() {
            return this.x + " " + this.y;
        }
    }

    public static void main(String[] args) throws Exception {

        BJ11650_1 mc = new BJ11650_1();
        final int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            args = br.readLine().split(" ");
            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);
            pq.add(mc.new XY(x, y));
        }

        while (!pq.isEmpty()) sb.append(pq.poll()).append('\n');

        System.out.println(sb);
        
    }
    
}
