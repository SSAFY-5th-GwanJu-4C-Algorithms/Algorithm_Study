package March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SW_5658_보물상자 {

	public static void main(String[] args) throws NumberFormatException, IOException {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			int T = Integer.parseInt(bf.readLine());
			ArrayList<String> cipher = new ArrayList<String>();
			StringBuilder sb = new StringBuilder();
			for(int tc = 1; tc <= T; tc++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				int N =  Integer.parseInt(st.nextToken());
				int K =  Integer.parseInt(st.nextToken());
				char code[] = new char[N];//문자입력받을 배열
				
				code = bf.readLine().toCharArray();
				
				int rotate = N/4; //회전 수 - 총 4분면
				boolean flag = false; //중복인지 확인하기위한 flag변수
				
				for(int i = 0; i < rotate; i++) {
					for(int j = 0; j < N; j++) {
						sb.append(code[j]);
						if(j%rotate == rotate-1) {
							flag = false; //flag false로 초기화
							String e = sb.toString();
							if(cipher.size() > 0) {//중복검사 시작
								for(String s : cipher) {
									if( e.equals(s) ) {
										sb.setLength(0);
										flag = true; //중복인 경우 true
										break;
									}
								}
							}//중복 검사 끝
							if(flag) continue;//중복된거면 continue
							
							cipher.add(e);//list에 추가
							sb.setLength(0);
						}
					}//for N개문자
					//1회전
					char c = code[0];
					for(int j = 1; j < N; j++) {
						code[j - 1] = code[j];
					}
					code[N - 1] = c;
				}//for문 전체 회전수 -완료
				
				//정렬
				Collections.sort(cipher, new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						//1B3 1B4 1C5 1C6
						for(int j = 0; j < rotate; j++) {
							if(o1.charAt(j) == o2.charAt(j)) continue;
							else return o2.charAt(j) - o1.charAt(j);
						}
						return 0;
					}
				});
				//정렬완료
				
				//K번째 계산
				String ans = cipher.get(K - 1);
				int result = 0;//최종 답
				for(int j = 0; j < ans.length(); j++) {
					char tmp = ans.charAt(j);
					int a = 0;//각 자리수에 16진수 만큼을 곱해주는 임시 변수
					if('A' <= tmp && 'F' >= tmp) {
						a = (int)(tmp - 'A') + 10;
					}
					else {
						a = (int)(tmp) - '0';
					}
					for(int k = 0; k < rotate - j - 1; k++) {
						a *= 16;
					}
					result += a;//최종 답에 +
				}
				System.out.println("#" + tc + " " + result);
				cipher.clear();
			}
	}
}