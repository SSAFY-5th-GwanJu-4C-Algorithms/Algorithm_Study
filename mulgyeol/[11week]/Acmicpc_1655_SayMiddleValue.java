import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Acmicpc_1655_SayMiddleValue {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.valueOf(br.readLine());

        PriorityQueue <Integer> pqSmall = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue <Integer> pqBig = new PriorityQueue<>();
        int sSize = 0;
        int bSize = 0;

        pqSmall.offer(Integer.valueOf(br.readLine()));
        sb.append(pqSmall.peek()).append("\n");
        pqBig.offer(10001);

        for(int i=0; i<N-1; i++){
            int num = Integer.valueOf(br.readLine());
            if(sSize < bSize){
                if(num <= pqSmall.peek()){
                    pqSmall.offer(num);
                    sSize++;
                }else{
                    if(num <= pqBig.peek()){
                        pqSmall.offer(num);
                        sSize++;
                    }else{
                        pqBig.offer(num);
                        pqSmall.offer(pqBig.poll());
                        sSize++;
                    }
                }
            }else{
                if(num <= pqSmall.peek()){
                    pqBig.offer(pqSmall.poll());
                    pqSmall.offer(num);
                    bSize++;
                }else{
                    pqBig.offer(num);
                    bSize++;
                }
            }
            sb.append(pqSmall.peek()).append("\n");
        }
        
        System.out.println(sb);
    }
}
