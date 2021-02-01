import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ10814_3 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static StringBuilder[] people = new StringBuilder[201];

    public static void main(String test[]) throws Exception {

        for (int N = Integer.parseInt(br.readLine()); N > 0; N--) {
            test = br.readLine().split(" ");
            int age = Integer.parseInt(test[0]);
            if (people[age] == null) people[age] = new StringBuilder();
            people[age].append(age).append(' ').append(test[1]).append('\n');
        }

        for (StringBuilder person : people)
            if (person != null) sb.append(person);

        System.out.print(sb);

    }

}
