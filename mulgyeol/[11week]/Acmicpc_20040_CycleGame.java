import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Acmicpc_20040_CycleGame {

    static int n;
    static int[] parents;

    static void make(){
        for(int i=0; i<n; i++){
            parents[i] = i;
        }
    }

    static int find(int a){
        if(parents[a] == a)
            return a;
        return parents[a] = find(parents[a]);
    }


    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot)
            return false;

        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.valueOf(st.nextToken());
        int m = Integer.valueOf(st.nextToken());
        parents = new int[n];
        int answer = 0;

        make();
        for (int i=1; i<=m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            if(!union(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()))){
                answer = i;
                break;
            }
        }

        System.out.println(answer);


    }
}
