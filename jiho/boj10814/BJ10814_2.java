import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BJ10814_2 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] test) throws Exception {

        HashMap<Integer,StringBuilder> map = new HashMap<>();

        final int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            test = br.readLine().split(" ");
            int age = Integer.parseInt(test[0]);
            if (map.get(age) == null)
                map.put(age, new StringBuilder().append(age + " " + test[1] + "\n"));
            else
                map.get(age).append(age + " " + test[1] + "\n");
        }

        for (int i = 0; i < 201; i++)
            if (map.get(i) != null) sb.append(map.get(i));
            
        System.out.println(sb);
        
    }
    
}
