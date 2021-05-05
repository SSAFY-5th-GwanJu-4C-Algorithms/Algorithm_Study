import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BJ10814_1 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static PriorityQueue<Human> pq = new PriorityQueue<>();

    class Human implements Comparable<Human> {
        int age;
        String name;
        int set;

        Human (int age, String name, int set) {
            this.age = age;
            this.name = name;
            this.set = set;
        }

        @Override
        public int compareTo(Human o) {
            if (this.age == o.age) return this.set - o.set;
            return this.age - o.age;
        }

        @Override
        public String toString() {
            return age + " " + name + "\n";
        }
    }

    public static void main(String[] args) throws Exception {

        BJ10814_1 mc = new BJ10814_1();
        final int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            args = br.readLine().split(" ");
            int age = Integer.parseInt(args[0]);
            String name = args[1];
            pq.add(mc.new Human(age, name, i));
        }

        while (!pq.isEmpty()) sb.append(pq.poll());

        System.out.println(sb);
        
    }
    
}
