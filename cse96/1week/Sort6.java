package com.baek.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sort6 {
		public static int[][] arr;
		public static int[][] result;
		
		public static void merge(int left,int mid,int right) {
			int i,j,k,l;
			i=left;
			j=mid+1;
			k=left;
			
			while(i<=mid && j<=right) {
				if(arr[i][0]<arr[j][0]) {
					result[k][0]=arr[i][0];
					result[k++][1]=arr[i++][1];
				}
				else if(arr[i][0]==arr[j][0]){
					if(arr[i][1]<arr[j][1]) {
						result[k][1]=arr[i][1];
						result[k++][0]=arr[i++][0];
					}
					else {
						result[k][1]=arr[j][1];
						result[k++][0]=arr[j++][0];
					}
				}
				else {
					result[k][1]=arr[j][1];
					result[k++][0]=arr[j++][0];

				}
			}
				//남아있는 값들을 일괄 복사
				
				if(i>mid) {
					for(l=j;l<right+1;l++) {
						result[k][1]=arr[l][1];
						result[k++][0]=arr[l][0];
					}
				}
				else {
					for(l=i;l<mid+1;l++) {
						result[k][1]=arr[l][1];
						result[k++][0]=arr[l][0];
					}
				}
				
				for(l=left;l<=right;l++) {
						arr[l][0]=result[l][0];
						arr[l][1]=result[l][1];
				}			
		}
		public static void partition(int left,int right) {
			int mid;
			if(left<right) {
				mid=(left+right)/2;
				partition(left,mid);
				partition(mid+1,right);
				merge(left,mid,right);
				
			}
		}
		
		public static void main(String[] args) throws IOException {
			// TODO Auto-generated method stub
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

			int n=Integer.parseInt(br.readLine());
			arr=new int[n][2];
			for(int i=0;i<n;i++) {
					String Input=br.readLine();
					String[] Split=Input.split(" ");
					arr[i][0]=Integer.parseInt(Split[0]);
					arr[i][1]=Integer.parseInt(Split[1]);
			}
			result=new int[arr.length][2];
			partition(0,n-1);
			for(int i=0; i<n; i++){
				    System.out.println(arr[i][0]+" "+arr[i][1]);
			  }
		}

}
