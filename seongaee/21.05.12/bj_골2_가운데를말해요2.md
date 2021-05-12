**1. 변수 선언**
```java
small=new PriorityQueue<Integer>((o1, o2)->{return o2-o1;}); //중간값보다 작은 수(내림차순) 
big=new PriorityQueue<Integer>(); //중간값보다 큰 수(오름차순)
```

<br>

**2. 일단 넣기**
```java
if(middle>cur) small.add(cur);
else big.add(cur);
```

<br>

**3. 짝수번째일때 중간에 있는 두 수 중 작은 수 middle로 정하기**
```java
if(i%2==1 && ssize-bsize==1) {
  int stmp = small.poll();
  if(stmp<middle) {
    big.add(middle);
    middle = stmp;
  }else {
    small.add(stmp);
  }
}
```

<br>

**4. 사이즈 차이가 2이상이면 중간값 변경**
```java
if(Math.abs(ssize-bsize)>=2) {
  if(ssize > bsize) {
    big.add(middle);
    middle=small.poll();
  }else {
    small.add(middle);
    middle=big.poll();
  }
}
```

<br>

**전체 코드**
```java
public class Main {
	
	static int N, middle;
	static PriorityQueue<Integer> small;
	static PriorityQueue<Integer> big;
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringBuffer sb=new StringBuffer();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N=Integer.parseInt(br.readLine());
		small=new PriorityQueue<Integer>((o1, o2)->{return o2-o1;}); 
		big=new PriorityQueue<Integer>(); 
		
		middle = Integer.parseInt(br.readLine());
		sb.append(middle).append("\n");
		
		for (int i = 1; i < N; i++) {
			int cur = Integer.parseInt(br.readLine());
			
			if(middle>cur) small.add(cur);
			else big.add(cur);
			
			int ssize = small.size();
			int bsize = big.size();
			
			if(i%2==1 && ssize-bsize==1) {
				int stmp = small.poll();
				if(stmp<middle) {
					big.add(middle);
					middle = stmp;
				}else {
					small.add(stmp);
				}
			}

			if(Math.abs(ssize-bsize)>=2) {
				if(ssize > bsize) {
					big.add(middle);
					middle=small.poll();
				}else {
					small.add(middle);
					middle=big.poll();
				}
			}

			sb.append(middle).append("\n");
		}

		System.out.println(sb.toString());
		
	}

}

```
