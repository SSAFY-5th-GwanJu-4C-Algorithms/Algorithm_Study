package com.ssafy.IM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class J_1037_오류교정 {
	static boolean[][] parity;
    static int N,col_idx[],row_idx[];
    public static void main(String[] args) throws NumberFormatException, IOException {
       BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
       N = Integer.parseInt(bf.readLine());
         
       parity = new boolean[N][N];
       StringTokenizer st;
        int tmp = 0;
        for(int i = 0; i < N; i++) {
           st = new StringTokenizer(bf.readLine()," ");
            for(int j = 0; j < N; j++) {
                tmp = Integer.parseInt(st.nextToken());
                if(tmp == 0) {
                    parity[i][j] = false;
                }
                else
                    parity[i][j] = true;
            }
        }//입력 끝
        row_idx = new int[N];
        col_idx = new int[N];
        boolean flag;
          
        //열 확인
        flag = Col_check();
          
        //행 확인
        flag = Row_check();
        if(flag) System.out.println("OK");
        else {
            for(int i = 0; i < N; i++) {
                if(row_idx[i] != 0) {
                    for(int j = 0; j < N; j++) {
                    	//해당부분 탐색하면서 바꿔봄
                        if(parity[i][j]) parity[i][j] = false;
                        else parity[i][j] = true;
                        
                        if(Col_check() && Row_check()) {
                            System.out.println("Change bit (" + (i+1) + "," + (j+1) +")");
                            return;
                        }
                          
                        //원상복구
                        if(parity[i][j]) parity[i][j] = false;
                        else parity[i][j] = true;
                          
                    }
                }
            }
            System.out.println("Corrupt");
        }   
    }
      
    public static boolean Col_check() {
        int flag = 0;
        for (int i = 0; i < N; i++) {
            int parit_bit = 0;
            for (int j = 0; j < N; j++) {
                if(parity[i][j]) parit_bit++;
            }
            if(parit_bit % 2 ==0);
            else {
                row_idx[i] = i;
                flag++;
            }
        }
        if(flag > 0) return false;
        else return true;
    }
      
    public static boolean Row_check() {
        int flag = 0;
        for (int i = 0; i < N; i++) {
            int parit_bit = 0;
            for (int j = 0; j < N; j++) {
                if(parity[j][i]) parit_bit++;
            }
            if(parit_bit % 2 ==0);
            else {
                col_idx[i] = i;
                flag++;
            }
        }
        if(flag > 0) return false;
        else return true;
    }
}
