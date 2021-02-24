package thirdweek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon_10163 {
	
	static boolean[][] map;
	static int[][] arr;
	static int[] result;
	static int N;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());

		arr = new int[N][4]; // 데이터 입력한 배열
		map = new boolean[101][101]; // true이면 세기
		result = new int[N]; // 결과 담을 배열
		
		// 데이터 입력
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < 4; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//System.out.println(Arrays.deepToString(arr));
	
		// 들어오는 좌표 내가 아는 좌표로 옮겨
		for(int i = 0; i < N; i++) {
			int y = arr[i][0];
			int x = 100 - arr[i][1];
			//System.out.println("x " + x + " y " + y);
			
			// 들어오는 색종이들 넓이에 true 저장 
			for(int h = 0; h < arr[i][3]; h++) {
				for(int w = 0; w < arr[i][2]; w++) {
					map[x - h][y + w] = true;
				}
			}
			
		}
		
		getArea();
	
		//System.out.println(Arrays.deepToString(map));
		//System.out.println(Arrays.toString(result));
		
		for(int r : result)
			System.out.println(r);
	}
	
	public static void getArea() {
		
		// 내가 아는 좌표로 옮겨두고
		// 색종이 받았던 순서 역방향!! (위에 있는 색종이부터 차례로 넓이 구하기)
		for(int i = N-1; i >= 0; i--) {
			int y = arr[i][0];
			int x = 100 - arr[i][1];
			int sum = 0;
	
			
			for(int h = 0; h < arr[i][3]; h++) {
				for(int w = 0; w < arr[i][2]; w++) {
					
					// true 갯수 세고 센 곳은 false로 바꿔서 저장
					if(map[x - h][y + w]) {
						sum ++;
						map[x - h][y + w] = false;
					}
					
				}
			}
			//System.out.println(sum);
			result[i] = sum;
		}
	}

}
