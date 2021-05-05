package study.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PM_level1_신규아이디추천 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String input = bf.readLine();
		input = input.toLowerCase();//1단계 소문자화
		int size = input.length();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < size; i++) {//2단계
			char c = input.charAt(i);
			if((c <= 'z' && c >= 'a') || (c >= '0' && c <= '9') || c =='-' || c == '.' || c == '_') {//다른문자 제거
				sb.append(c);
			}
		}
		input = sb.toString();
		sb.setLength(0);
		size = input.length();
		//마침표여러개 하나로 치환
		for(int i = 0; i < size; i++) {
			char c = input.charAt(i);
			if(i == size-1 && c == '.') continue;
			else if(c == '.' && input.charAt(i+1) == '.') continue;
			sb.append(c);
		}
		
		String answer = sb.toString();
		//마침표 처음 끝 제거
		size = answer.length();
		if(answer.indexOf(".") == 0) {
			answer = answer.substring(1, size);
		}
		size = answer.length();
		if(size != 0 && answer.lastIndexOf(".") == size - 1) {
		    answer = answer.substring(0,size - 1);
		}
		size = answer.length();
		//null이면 a대입
		if(answer.length() == 0) {
			System.out.println("aaa");//null이면 a대입
			return;
		}
		if(size >= 15) {
			answer = answer.substring(0, 15);
			if(answer.lastIndexOf(".") == 14) answer = answer.substring(0,14);
			System.out.println(answer);
			return;
		}
		if(size < 3) {
			sb.setLength(0);
			if(size == 1) {
				sb.append(answer);
				char c = answer.charAt(0);
				sb.append(c);
				sb.append(c);
			}
			else if(size == 2) {
				sb.append(answer);
				char c = answer.charAt(1);
				sb.append(c);
			}
			answer = sb.toString();
		}
		System.out.println(answer);
	}

}
