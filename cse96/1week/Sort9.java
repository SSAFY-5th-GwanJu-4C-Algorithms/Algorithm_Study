package com.baek.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sort9 {
		public static int[] arr;
		public static int[] result;
		public static String[] str;
		public static String[] str2;
		
		public static void merge(int left,int mid,int right) {
			int i,j,k,l;
			i=left;
			j=mid+1;
			k=left;
			
			while(i<=mid && j<=right) {
				if(arr[i]<arr[j]) {
					result[k]=arr[i];
					str2[k++]=str[i++];
				}
				else if(arr[i]==arr[j]){
					result[k]=arr[i];
					str2[k++]=str[i++];
				}
				else {
					result[k]=arr[j];
					str2[k++]=str[j++];

				}
			}
				//남아있는 값들을 일괄 복사
				
				if(i>mid) {
					for(l=j;l<right+1;l++) {
						result[k]=arr[l];
						str2[k++]=str[l];
					}
				}
				else {
					for(l=i;l<mid+1;l++) {
						result[k]=arr[l];
						str2[k++]=str[l];
					}
				}
				
				for(l=left;l<=right;l++) {
						arr[l]=result[l];
						str[l]=str2[l];
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
			arr=new int[n];
			str= new String[n];
			for(int i=0;i<n;i++) {
					String Input=br.readLine();
					String[] Split=Input.split(" ");
					arr[i]=Integer.parseInt(Split[0]);
					str[i]=Split[1];
			}
			System.out.println(str[1]);
			str2 = new String[n];
			result=new int[arr.length];
			partition(0,n-1);
			for(int i=0; i<n; i++){
				    System.out.println(arr[i]+" "+str[i]);
			  }
		}

}
