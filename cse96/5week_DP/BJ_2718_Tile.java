import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	
		public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int tile[] = new int[1001];
		tile[0] = 1;
		tile[1] = 1;
		tile[2] = 5;
		tile[3] = 11;
		int tmp = 3;
		for(int i = 0; i < N; i++) {
			int T = Integer.parseInt(bf.readLine());
			if(T <= tmp) {
				System.out.println(tile[T]);
				continue;
			}
			else {
				for(int j = tmp + 1; j <= T; j++) {
					for(int k = j - 1; k >=0; k--) {
						if(k == j - 1) {      //한칸전
							tile[j] += tile[k];
						}
						else if(k == j - 2) { //2칸전
							tile[j] += tile[k]*4;
						}
						else if( (j-k) % 2 == 0) { //짝수칸 떨어진 경우
							tile[j] += tile[k]*3;
						}
						else if( (j-k) % 2 == 1) { //홀수칸 떨어진 경우
							tile[j] += tile[k]*2;
						}
					}
				}
			}
			System.out.println(tile[T]);
			tmp = T;
		}
	}
}
