import java.util.*;

/**
 * 프로그래머스 72412 순위 검색
 * 
 * @author jihogrammer@gmail.com
 * 
 * @see https://blog.naver.com/jihogrammer/222340107316
 * 
 * @description Bitmask 기법을 사용하여 각 항목을 분류하고 비트연산자를 통해 성능을 개선했습니다. Collection의
 *              다양한 메소드를 배울 수 있어 좋았던 문제였습니다. 문제는 솔직히 당돌하게 어려웠습니다.
 */
class Solution {

    private static final int BIT = 3;
    private static final int MASK = ~(-1 << BIT);

    public int[] solution(String[] info, String[] query) {

        // 핵심 자료구조
        Map<Integer, List<Integer>> map = new HashMap<>();

        // info를 split해서 비트열 key로 만들고,
        // key를 가지고 부분집합을 생성하여 map에 put한다.
        int size = info.length;
        for (int i = 0; i < size; i++) {

            // 문자열 쪼개기
            String[] split = info[i].split(" ");

            // 점수 준비
            int score = Integer.parseInt(split[4]);

            // 비트열 준비
            int key = 0;

            // 4가지 분야별로 비트열에 매핑하기
            for (int j = 0; j < 4; j++) {

                key <<= BIT;
                int temp = 0; // 비트열에 마스킹할 임시 변수

                // 쪼개진 문자열 앞 한 글자만 따서 구분하기
                switch (split[j].charAt(0)) {
                    case 'j':
                    case 'b': // java, backend, junior
                        temp = 1;
                        break;
                    case 'c':
                    case 'f':
                    case 's': // chicken, frontend, senior
                        temp = 2;
                        break;
                    case 'p': // pizza
                        temp = 4;
                        break;
                }

                key |= temp; // 마스킹

            }

            // 부분집합 생성하고 map에 put하는 작업 수행하기
            putScore(0, score, key, map);

        }

        // map 전체 탐색해서 각 ArrayList를 오름차순으로 정렬하기
        for (Integer k : map.keySet())
            Collections.sort(map.get(k));

        // 정답 준비
        size = query.length;
        int[] answer = new int[size];

        // map을 동적으로 생성해서 put하기 때문에
        // query에 해당하는 List가 없을 수도 있다.
        // 따라서 빈 List를 미리 준비해두고 필요하면 가져다 쓴다.
        List<Integer> empty = new ArrayList<>();

        // query 탐색
        for (int i = 0; i < size; i++) {

            // query 쪼개기
            String[] split = query[i].split(" and ");

            // 기준 점수 할당
            int score = Integer.parseInt(split[3].split(" ")[1]);

            // query 비트열 준비
            int key = 0;

            // 비트열 생성
            for (int j = 0; j < 4; j++) {

                key <<= BIT;
                int temp = 0;

                switch (split[j].charAt(0)) {
                    case 'j':
                    case 'b':
                        temp = 1;
                        break;
                    case 'c':
                    case 'f':
                    case 's':
                        temp = 2;
                        break;
                    case 'p':
                        temp = 4;
                        break;
                    case '-':
                        temp = MASK;
                        break;
                }

                key |= temp;

            }

            // 생성된 비트열, 즉 key를 이용해서 List를 받아온다.
            // 상단에 말한 것처럼 key에 해당하는 List가 없을 경우
            // Default로 empty list를 받아온다.
            List<Integer> list = map.getOrDefault(key, empty);

            // LowerBound × BinarySearch 준비
            int len = list.size(), l = 0, r = len, m;

            // lower bound 개념이 적용된 binary search 수행
            while (l < r)
                if (list.get(m = l + r >> 1) < score)
                    l = m + 1;
                else
                    r = m;

            // 정답 배열에 값 채우기
            answer[i] = len - l;

        }

        return answer;
    }

    // 부분집합 생성 및 map에 값 할당
    private static final int[] DASH_MASK = { MASK << 3 << 3 << 3, MASK << 3 << 3, MASK << 3, MASK };

    private void putScore(int select, int score, int key, Map<Integer, List<Integer>> map) {

        // 4가지 분야별로 부분집합이 생성되었으면
        if (select == 4) {
            // computeIfAbsent() : key에 해당하는 값이 없을 경우
            // 람다식으로 생성된 List에 점수를 add하고,
            // 이미 List가 존재할 경우 그냥 add한다.
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(score);
            return;
        }

        // 이렇게 비트마스킹으로 부분집합을 수행할 경우
        // 백트래킹을 수행할 필요가 없어 코드가 단순해진다. 물론 까다롭다.
        putScore(select + 1, score, key | DASH_MASK[select], map);
        putScore(select + 1, score, key, map);

    }

}