# Mulgyeol's directory for Algoriths_Study

## 목차
- 1주차 : 정렬


## Java for Algorithms
> <br>
>
> __Java를 이용해서 알고리즘 문제를 해결합니다.__
><br>

---

### 주의사항
> <br>
>
> __처음 자바로 알고리즘 문제를 풀면서 알게된, 주의 사항들을 정리합니다.__
><br>
#### 1. 백준 코드 제출 시 클래스 이름은 항상 `Main`으로 한다.
- 주로 IDE나 편집기를 이용해서 코드를 작성하고, 붙여넣기 하는 형태로 푸는데, 파일 이름을 보기 좋게 구분하는 경우가 많다.
- 이 경우, 컴파일 에러가 뜨기 때문에 클래스 이름을 항상 `Main`으로 변경해 준다.
- SWExpert는

#### 2. 패키지 이름을 지운다.
- 1번과 마찬가지로 다른 툴을 사용해서 코드를 작성해서 복사하면 패키지 이름까지 복사해서 제출하는 경우가 있다.
- 패키지 이름을 지우지 않으면, 런타임 오류가 발생하므로 지웠는지 확인하도록 하자.

#### 3. 입력은 BufferedReader를 이용하는 것이 좋다.
- Scanner는 편리하지만, 속도가 배우 느리다.
- 입력을 많이 받아야 하는 경우는 BufferedReader를 사용하는 것이 좋다.
- BufferedReader에서는 read와 readLine만 있기 때문에 정수는 파싱을 해야한다.

#### 4. 출력은 StringBuilder를 이용하는 것이 좋다.
- 출력할 내용이 많을 경우에 반복문 등을 통해 print문을 여러 번 사용하게 된다.
- 매번 print문을 사용하는것 보다 StringBuilder를 통해 문자열을 합쳐서 한번에 출력하는 것이 속도가 빠르다.

#### 5. `submit_java`라는 크롬익스텐션을 설치하면 좋다.
- 백준, SWEA등에서 IDE에서 작성한 코드를 복사 붙여넣기 할 때,
- 해당 사이트에서 요구하는 조건대로 수정후 제출해준다.
    - 패키지 삭제
    - 클래스 이름 변경


---

#### BufferdReader와 StringBuilder 사용 예
```Java

    //BufferedReader 사용법
    package com.xxx.xxx;
    import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;

    public class Main {
        // BufferedReader는 Exception이 처리를 따로 해줘야 하기 때문에 throws를 해주거나 
        // try ~ catch로 예외처리를 해준다.
        public static void main(String[] args) throws Exception {

            // BufferedReader 객체 생성를 생성한다.
            //InputStreamReader : node stream
            //BufferedReader : precess stream
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

            // StringTokenizer 객체를 선언한다.
            StringTokenizer st = null;

            // 입력값은 String이므로 Integer.parseInt를 이용하여 형변환을 해준다.
            int n = Integer.parseInt(br.readLine());//입력할 숫자 갯수

            // "1 3 5 7" 식으로 공란 포함 String Line일시 StringTokenizer 이용
            int[] arrays = new int[n];
        // st = new StringTokenizer(br.readLine()); //두 번째 파라메터가 없는  br.readLine()의 기본형은 공백을 제거한 것을 추출해줌
            st = new StringTokenizer(br.readLine(),"c");//input 예) 4와 1c2c3c4c을 input 했다면 arrays에는 1, 2, 3, 4가 저장됨
                                                        //이런식으로 StringTokenizer를 통해 내맘대로 input값을 조절?할 수 있음.

            for (int i = 0; i < n; i++) {
                // 배열에다 토큰을 하나씩 불러서 입력해줌
                arrays[i] = Integer.parseInt(st.nextToken());//숫자 n개만큼 입력 후 배열에 저장
            }


            StringBuilder sb = new StringBuilder();

    
            System.out.println(sb);
            //for each문으로 sb에 값과 줄바꿈을 저장
            for(int a:arrays){
                sb.append( a + "\n");
            }

            //한 번에 sb를 출력
            System.out.println(sb);
        }
    }


```



