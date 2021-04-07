BJ 2529 부등호
-----------------
1. max, min비교시 min을 int형으로 받고 출력하니 0으로 시작하는 값은 int형에서 0을 제외해버려서 String으로 받았다.
2. NumberFormatException이 발생해서 질문을 검색해보니 10자리수는 int형 범위를 벗어난다기에 String으로 compareto함수를 써봤는데
   tmp.compareTo(tmp1)일때 tmp가 더 크면 1 더 작으면 -1 이라길래 해봤더니 꼭 -1이 출력되는게 아니어서 String은 포기하고 long형으로 바꿨다.
3. Long타입으로 바꿔줬는데도 틀리길래 봤더니 min값 초기화를 Integer.MAX_VALUE로 해줬더니 int형 최댓값보다 큰 최솟값이 생기는 경우가 있었다.

BJ 1520

BJ 2667

BJ 19237
