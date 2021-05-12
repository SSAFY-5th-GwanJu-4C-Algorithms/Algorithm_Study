**1. Make Method**
```
private static void make() {
  for (int i = 0; i < n; i++) {
    parent[i]=i;
  }
}
```

<br>

**2. Find Method**
```
private static int find(int a) {
  if(parent[a]==a) return a;
  else return parent[a] = find(parent[a]);
}
```

<br>

**3. Union Method**
```
private static boolean union(int a, int b) {
  int aRoot=find(a);
  int bRoot=find(b);

  if(aRoot==bRoot) return false;
  else {
    parent[bRoot]=aRoot;
    return true;
  }
}
```

<br>

**전체 코드**
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n,m,parent[];
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken()); //0~n-1점
		m=Integer.parseInt(st.nextToken()); //m번째차례까지 진행
		parent=new int[n];
		
		make();
		
		for (int i = 1; i <= m; i++) {
			
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken()); //선택한 점1
			int b=Integer.parseInt(st.nextToken()); //선택한 점2
			
			if(!union(a, b)) {
				System.out.println(i);
				return;
			}
			
		}
		
		System.out.println(0);
		
	}

	private static int find(int a) {
		if(parent[a]==a) return a;
		else return parent[a] = find(parent[a]);
	}

	private static boolean union(int a, int b) {
		int aRoot=find(a);
		int bRoot=find(b);
		
		if(aRoot==bRoot) return false;
		else {
			parent[bRoot]=aRoot;
			return true;
		}
	}

	private static void make() {
		for (int i = 0; i < n; i++) {
			parent[i]=i;
		}
	}
}

```
