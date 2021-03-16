package March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_4013_특이한자석 {
	static int magnet[] = new int[32];//0~7 1번 8~15 2번 16~23 3번 24~31 4번
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(bf.readLine()); //총 테스트케이스
		for(int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(bf.readLine());
			
			init();
			for(int i = 0; i < K; i++) { //회전 수 만큼
				st = new StringTokenizer(bf.readLine()," ");
				int n = Integer.parseInt(st.nextToken()); //회전할자석
				int dir = Integer.parseInt(st.nextToken());//방향
				pick(n,dir);
			}
			int ans = 0;
			if(magnet[0] == 1) ans+=1;
			if(magnet[8] == 1) ans+=2;
			if(magnet[16] == 1) ans+=4;
			if(magnet[24] == 1) ans+=8;
			System.out.println("#"+tc+" "+ans);
			
		}//for-tc
	}

	private static void pick(int n, int dir) {
		//0~7 1번(2) 8~15 (14)2번(10) 16~23 (22)3번(18) 24~31(30)4번
		int dir2 = 0;
		if(dir == 1) {
			dir2 = -1;
		}
		else {
			dir2 = 1;
		}
		
		if(n == 1) {//1번 자석 회전할 때
			if(magnet[2] == magnet[14]) {
				rotate1(dir);
				return; //1번 2번 맞닿은 날이 같은 자성 회전x 
			}
			else {//회전 1~2번
				if(magnet[10] == magnet[22]) { //2번 3번 맞닿은 날이 같은 자성
					rotate2(dir2);
				}//1~2번 자석 회전
				else {
					if(magnet[18] ==  magnet[30]) {//3번 4번 맞닿은 날이 같은 자성
						//1~3번 자석 회전
						rotate2(dir2);
						rotate3(dir);
					}
					else {
						rotate2(dir2);
						rotate3(dir);
						rotate4(dir2);
					}
				}
			}
			rotate1(dir);
		}
		else if(n == 2) {//2번 자석 회전할 때
			if(magnet[2] == magnet[14]) ; //1번 2번 맞닿은 날이 같은 자성 회전x 
			else {
				rotate1(dir2);
			}
			if(magnet[10] == magnet[22]) {//2번 3번 같은 자성 회전 x
			}
			else {
				if(magnet[18] == magnet[30]) {//2번 3번 다른 자성, 3번4번 같은 자성
					rotate3(dir2);
				}
				else {
					rotate3(dir2);
					rotate4(dir);
				}
			}
			rotate2(dir);
		}
		else if(n == 3) {//3번 자석 회전할 때
			if(magnet[18] == magnet[30]);//오른쪽 확인
			else {
				rotate4(dir2);
			}
			if(magnet[10] == magnet[22]) { //2번 3번 같은 자성
				
			}
			else {//2번 3번 다른자성
				if(magnet[2] == magnet[14]) { // 1번 2번 같은 자성
					rotate2(dir2);
				}
				else {
					rotate2(dir2);
					rotate1(dir);
				}
			}
			rotate3(dir);
		}
		else if(n == 4) {//4번 자석 회전할 때
			if(magnet[18] == magnet[30]) {
				rotate4(dir);
				return;//3,4번 같은 자성
			}
			else {
				if(magnet[10] == magnet[22]) {//3,4번 다른 자성 2,3번 같은 자성
					rotate3(dir2);
				}
				else {
					if(magnet[2] == magnet[14]) {//2,3,4번 다른 자성 1,2번 같은 자성
						rotate2(dir);
						rotate3(dir2);
					}
					else {
						rotate1(dir2);
						rotate2(dir);
						rotate3(dir2);
					}
				}
			}
			rotate4(dir);
		}
	}
	//4번 자석회전 24~31
	private static void rotate4(int dir) {
		if(dir == 1) {//시계방향
			int temp = magnet[31];
			for(int i = 31; i > 24; i--) {
				magnet[i] = magnet[i-1];
			}
			magnet[24] = temp;
		}
		else if(dir == -1) {
			int temp = magnet[24];
			for(int i = 24; i < 31; i++) {
				magnet[i] = magnet[i+1];
			}
			magnet[31] = temp;
		}
	}
	//3번 자석회전 16~23
	private static void rotate3(int dir) {
		if(dir == 1) {//시계방향
			int temp = magnet[23];
			for(int i = 23; i > 16; i--) {
				magnet[i] = magnet[i-1];
			}
			magnet[16] = temp;
		}
		else if(dir == -1) {
			int temp = magnet[16];
			for(int i = 16; i < 23; i++) {
				magnet[i] = magnet[i+1];
			}
			magnet[23] = temp;
		}
	}
	//2번 자석회전 8~15
	private static void rotate2(int dir) {
		if(dir == 1) {//시계방향
			int temp = magnet[15];
			for(int i = 15; i > 8; i--) {
				magnet[i] = magnet[i-1];
			}
			magnet[8] = temp;
		}
		else if(dir == -1) {
			int temp = magnet[8];
			for(int i = 8; i < 15; i++) {
				magnet[i] = magnet[i+1];
			}
			magnet[15] = temp;
		}
	}
	//1번 자석회전 0~7
	private static void rotate1(int dir) {
		if(dir == 1) {//시계방향
			int temp = magnet[7];
			for(int i = 7; i > 0; i--) {
				magnet[i] = magnet[i-1];
			}
			magnet[0] = temp;
		}
		else if(dir == -1) {//반시계방향
			int temp = magnet[0];
			for(int i = 0; i < 7; i++) {
				magnet[i] = magnet[i+1];
			}
			magnet[7] = temp;
		}
	}

	private static void init() throws IOException {
		st = new StringTokenizer(bf.readLine()," ");
		for(int i = 0; i < 32; i++) {
			if(i!=0 && i%8==0) {
				st = new StringTokenizer(bf.readLine()," ");
			}
			magnet[i] = Integer.parseInt(st.nextToken());
		}
	}//입력완료
}