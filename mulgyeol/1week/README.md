# Sort

#### List
-  [[2750] 수 정렬하기](https://www.acmicpc.net/problem/2750) - [code](./acmicpc_Sort_2750.java)
-  [[1427] 소트인사이드](https://www.acmicpc.net/problem/1427) - [code](./acmicpc_Sort_1427.java)
-  [[11650] 좌표 정렬하기](https://www.acmicpc.net/problem/11650) - [code](./acmicpc_Sort_11650.java)
-  [[11651] 좌표 정렬하기2](https://www.acmicpc.net/problem/11651) - [code](./acmicpc_Sort_11651.java)
-  [[10814] 수 정렬하기](https://www.acmicpc.net/problem/10814) - [code](./acmicpc_Sort_10814.java)

#### Arrays.sort()
> <br>
> 
> __조건이 복잡한 정렬 작업을 할 때, 유용한 방법 중 하나인 Arrays.sort()에 대해 정리한다.__
><br>

##### 1. Arrays.sort()
![arraySrotImg](./img/Arrays_sort.PNG)
- API documnets에서 Arrays의 sort를 찾아보니 다음과 같은 설명이 있었다.
- sort메서드 사용시 파라미터로, 정렬할 배열 하나와, Comparator 인터페이스를 넘겨줄 수 있었다.
- 설명 : Comparator에서 유도한 순서에 따라 지정된 개체 배열을 정렬한다.

##### 2. Comparator Interface
![Comparator](./img/Comparator.PNG)
    - Method Summary를 살펴보면 compare() method를 담고 있고, 이걸로 비교할 값을 설정할 수 있다.


##### 3. compare method
![Comparator_method_compare](./img/Comparator_method_compare.PNG)
    - 파라미터로 넘겨준 값들을 비교한다.


##### 4. 구체적인 사용 예시
 ```Java
    Arrays.sort(arr,new Comparator<int[]>(){
        @Override
        public int compare(int[] point1, int[] point2){
            if(point1[0]==point2[0])
            return Integer.compare(point1[1],point2[1]);
            
            return Integer.compare(point1[0], point2[0]);
        }
    });
 ```

-  arr는 int타입의 x값과 y값을 담는 배열을 담고 있는 2차원 배열이며, 각 배열은 0번 인덱스에 x값, 1번 인덱스에 y값을 가지고 있다.
- x값을 기준으로 오름차순으로 정렬하되, x값이 같다면 y값을 기준으로 오름차순으로 정렬한다.
- Arrays.sort()에 인자로 arr,와 int[]타입 제네릭을 사용한 Comparator을 넘겨준다.
- sort 내부에서 compare method를 오버라이드한다.
- overide의 파라미터로는 비교할 대상 두개를 넘겨준다.(비교할 대상은 무엇인가?)
- return 값으로 사용할 자료형 클래스의 compare메소드에 비교할 값 두 개를 담아 넘긴다.(어떤 기준으로 대상을 비교할 것인가?
