package thirdweek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
 * 참외 갯수를 구하는 문제
 * 밭의 넓이를 구하는 것이 주된 문제이고 나는 큰 사각형에서 작은 사각형을 빼는 방법으로 접근
 * 규칙을 보았을 때 
 * {{3,1,3,1,4,2}, {1,4,1,4,2,3}, {4,2,4,2,3,1}, {2,3,2,3,1,4}}
 * 두번씩 숫자와 한번씩 나오는 숫자들이 있는데 한번씩 나오는 숫자의 곱이 큰 사각형의 넓이를 가르키고
 * 그 한번씩 나오는 숫자들의 다다음에 나오는 두 숫자가 작은 사각형의 넓이를 가르킨다.

 */
public class baekjoon_2477 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[6][2];
		int[] design = new int[5];
		
		for(int i = 0; i < 6; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			arr[i][0] = Integer.parseInt(st.nextToken());
			design[arr[i][0]]++;
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 4가지의 모양으로 들어오는데 이런 모습들로 들어올 수 있다는 것을 확인
		int[][] turn = {{3,1,3,1,4,2}, {1,4,1,4,2,3}, {4,2,4,2,3,1}, {2,3,2,3,1,4}};
		//ㄱ, r , ㄴ , J
		
		//System.out.println(Arrays.toString(design));
		
		int area = 1; // 큰 사각형 넓이
		
		int w[] = new int[2]; // 한개만 있는 변의 인덱스 자리
		int idx = 0;
		
		for(int i = 0; i < 5; i++) {
			if(design[i] == 1) { // 큰 사각형은 한번씩만 나오는 그 부분
				
				//System.out.println(i);
			
				for(int j = 0; j < 6; j++) {
					if(i == arr[j][0]) {
						//System.out.println(arr[j][0]);
						area *= arr[j][1]; // 큰 사각형의 넓이를 구하였다. 
						w[idx++] = j;
						break;
					}
					
				}
			}
		}

		//System.out.println(Arrays.toString(w));
		
		// 작은 사각형을 구하는 과정 
		// 작은 사각형은 큰 사각형의 넓이를 구했던 그 수들의 다다음 숫자들이다.
		int max = w[0] > w[1] ? w[0] : w[1];

		int num1 = max + 2 > 5 ? max + 2 - 6: max + 2;
		int num2 = max + 3 > 5 ? max + 3 - 6: max + 3;
		
		if((w[0] == 0 && w[1] == 5) || (w[0] == 5 && w[1] == 0)) {
			num1 = 2;
			num2 = 3;
		}
		
		// 작은 사각형 넓이
		int smallArea = arr[num1][1] * arr[num2][1];
		
		//System.out.println(smallArea);
		//System.out.println(area);
		
		
		int resultArea = area - smallArea;
		
		int watermelon = resultArea * K;
		System.out.println(watermelon);
		
	}
}

