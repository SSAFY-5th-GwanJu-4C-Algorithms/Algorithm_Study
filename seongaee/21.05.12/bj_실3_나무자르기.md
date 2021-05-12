**1. 이분 탐색을 위한 middle 값 구하기** 

```java
mid=(left+right)/2;
long cutLength = 0;
```

<br>

**2. 해당 middle로 나무 잘라보기**
```java
for (int i = 0; i < N; i++) {
  if(tree[i]>mid) cutLength += tree[i]-mid;
}
```

<br>

**3. 자른 나무의 길이에 따라 다음 left of right 정하기**
```java
if(cutLength>=M) { //조건 충족 -> 더 위로 잘라봐도됨
  left=mid+1;
  ans=mid;
}else if(cutLength<M) { //조건 충족x -> 더 아래로 잘라야됨
  right=mid-1;
}
```


<br>

**전체 코드**
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N,tree[],bigTree;
	static long M;
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken()); //나무 개수
		M=Integer.parseInt(st.nextToken()); //적어도 M미터 절단
		
		tree=new int[N];
		st=new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			tree[i]=Integer.parseInt(st.nextToken());
			if(bigTree<tree[i]) bigTree=tree[i];
		}
		
		long ans = BinarySearch();
		
		System.out.println(ans);
	}

	private static long BinarySearch() {
		
		long left=0, right=bigTree, mid;
		long ans=0;
		
		while(left<=right) {
			
			mid=(left+right)/2;
			long cutLength = 0;
		
			for (int i = 0; i < N; i++) {
				if(tree[i]>mid) cutLength += tree[i]-mid;
			}
			
			if(cutLength>=M) { //조건 충족 -> 더 위로 잘라봐도됨
				left=mid+1;
				ans=mid;
			}else if(cutLength<M) { //조건 충족x -> 더 아래로 잘라야됨
				right=mid-1;
			}
		}
		
		return ans;
	}

}
```
