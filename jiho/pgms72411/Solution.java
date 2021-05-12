package pgms72411;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

class Solution {

    public static void main(String[] args) {

        String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course = {2, 3, 5};

        String[] answer = new Solution().solution(orders, course);

        for (String s : answer)
            System.out.println(s);

        System.out.println("\n\nAD vs ADE : " + "AD".compareTo("ADE"));
    }

    class Menu implements Comparable<Menu> {

        String menu;
        int count;

        Menu(String menu, int count) {
            this.menu = menu;
            this.count = count;
        }

        @Override
        public int compareTo(Menu o) {
            return o.count - this.count;
        }

    }

    public String[] solution(String[] orders, int[] course) {

        String[] answer = {};

        int len = orders.length;

        // Map<주문(Bitmask), 뽑힌 횟수> : 주문 당 뽑힌 횟수 저장
        HashMap<String, Integer> map = new HashMap<>();

        // 주문 1 : 1 비교 시 & 연산을 통해 비교하기 위해 문자열을 비트열로 변환
        int[] binaryOrders = makeBinaryOrders(orders, len);

        // 주문 1 : 1 비교(뽑는 방식은 이중 for문으로 조합 구현)
        for (int i = 0; i < len; i++)
            for (int j = i + 1; j < len; j++)
                compare(binaryOrders[i] & binaryOrders[j], course, map);

        // 많이 뽑힌 순으로 Queue에 담기
        PriorityQueue<Menu> pq = new PriorityQueue<>();
        for (String key : map.keySet())
            pq.offer(new Menu(key, map.get(key)));

        // 정답 구하기
        ArrayList<String> list = getList(pq, new int[course[course.length - 1] + 1]);
        list.sort((a, b) -> a.compareTo(b));

        // List를 배열로 변환
        answer =  list.toArray(new String[list.size()]);

        return answer;

    }

    private ArrayList<String> getList(PriorityQueue<Menu> pq, int[] count) {

        // 정답 리스트
        ArrayList<String> list = new ArrayList<>();

        while (!pq.isEmpty()) {

            // 뽑힌 메뉴
            Menu menu = pq.poll();

            // 뽑힌 횟수
            int num = menu.count;

            // 문자열의 길이
            int len = menu.menu.length();

            // 문자열 길이에 해당하는 뽑힌 횟수가 더 크다면 continue
            // 같을 때는 안 걸리므로 계속해서 List에 추가
            if (count[len] > num) continue;

            // 카운트 배열 값 갱신
            count[len] = num;

            // List에 정답 문자열 담기
            list.add(menu.menu);

        }

        return list;

    }

    private void compare(int binary, int[] course, HashMap<String, Integer> map) {

        // 겹치는 문자(ASCII Code)를 ArrayList에 저장하여 비교
        ArrayList<Integer> list = new ArrayList<>();

        // & 연산으로 넘어온 비트열을 보고 겹치는 메뉴를 List에 담기
        for (int i = 1; i < 27; i++)
            if ((binary & (1 << i)) != 0)
                list.add(i);

        // 겹치는 메뉴의 수
        int size = list.size();

        // 겹치는 메뉴의 수가 2개 미만이거나
        // course의 최솟값보다 작을 경우
        // map에 담을 필요가 없음
        if (size < 2 || size < course[0]) return;

        // course의 크기
        size = course.length;

        // course에 담긴 값만큼 메뉴 개수 뽑기(조합)
        for (int i = 0; i < size; i++)
            combination(0, 0, 0, course[i], list, map);

    }

    private void combination(int start, int cnt, int visited, int num, ArrayList<Integer> list, HashMap<String, Integer> map) {

        // 겹치는 메뉴의 수
        int size = list.size();

        // course의 값. 즉, 메뉴 수만큼 조합되었을 경우
        if (cnt == num) {

            // 리스트에 담긴 메뉴들을 map에 담을 준비
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++)
                if ((visited & (1 << i)) != 0)
                    sb.append((char)(list.get(i) + 64));

            // 문자열로 변환하여 저장
            String key = sb.toString();

            // map 채우기
            map.put(key, map.getOrDefault(key, 0) + 1);

            return;

        }

        // 뽑힌 문자열로 각 코스 메뉴 개수만큼 조합
        for (int i = start; i < size; i++)
            combination(i + 1, cnt + 1, visited | (1 << i), num, list, map);

    }

    private int[] makeBinaryOrders(String[] orders, int len) {

        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {

            char[] s = orders[i].toCharArray();
            int binary = 0;
            int size = s.length;

            for (int j = 0; j < size; j++)  // A = 65 = 1000001
                binary |= 1 << (s[j] & 31); // A & 31 = 1000001 & 00111111 = 1

            // 문자열 -> 비트열
            arr[i] = binary;

        }

        return arr;
    }

}
