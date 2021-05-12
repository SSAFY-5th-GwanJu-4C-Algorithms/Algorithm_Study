import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 여학생 0 남학생 1
 * 학생수 N 방에 최대 배정할수 있는 K 입력후
 * 줄마다 성별 학년, 학생 수 입력
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		//[성별][학년]
		int[][] students = new int[6][2];
		
		int s,g;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine()," ");
			s = Integer.parseInt(st.nextToken());//성별
			g = Integer.parseInt(st.nextToken());//학년
			students[g - 1][s]++; //학년 성별 저장하는 배열 인덱스를 위해 학년부분-1
		}//입력 끝
		
		int room = 0;
		for(int i = 0; i < 6; i++) {
			if(students[i][0] == 0);//각 학년 여학생 수가 0명이면 방을 늘리지 않음
			else {//각 학년 여학생 수가 K 이상이면 방을 하나 늘린 후
				room++;
				
				while(students[i][0] > K) {
					students[i][0] -= K; //K만큼 인원수를 빼주면서 비교
					room++; //방크기를 반복문마다 한번씩 늘림
				}
			}
			if(students[i][1] == 0); //남학생도 동일한 반복문
			else {
				room++;
				while(students[i][1] > K) {
					students[i][1] -= K;
					room++;
				}
			}
		}
		
		System.out.println(room);
	}

}
