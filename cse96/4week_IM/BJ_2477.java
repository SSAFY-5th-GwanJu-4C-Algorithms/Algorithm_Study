import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(bf.readLine());//참외 개수
		int[] farm = new int[6]; //1동쪽 2 서쪽 3남쪽 4북쪽 값을 저장
		int[] dir = new int[6];
		StringTokenizer st;
		int seq = 0;
		int idx = 0;
		for(int i = 0; i < 6; i++) {
			st = new StringTokenizer(bf.readLine()," ");
			int tmp = Integer.parseInt(st.nextToken());
			
			if(seq == 4 && tmp != 2) { //각 경우의 수마다 반시계방향이 아닌 인덱스를 저장
				idx = i;
			}
			else if(seq == 3 && tmp != 1) {
				idx = i;
			}
			else if(seq ==2 && tmp != 3) {
				idx = i;
			}
			else if(seq == 1 && tmp != 4) {
				idx = i;
			}

			seq = tmp;
			dir[i] = seq;
			farm[i] = Integer.parseInt(st.nextToken());
			
		}//입력 끝
		int W = 0, H = 0;
		for(int i = 0; i < 6; i++) {
			if(dir[i] == 1 || dir[i] == 2) {
				W = Math.max(W,farm[i]);
			}
			else {
				H = Math.max(H, farm[i]);
			}
		}
		
		int max_area = W*H;
		int min_area;
		if(idx == 0) {
			min_area = farm[0]*farm[5];
		}
		else {
			min_area = farm[idx]*farm[idx-1];
		}
		int area = max_area-min_area;
		int ans = area * K;
		System.out.println(ans);
	}

}
