package pgms72412;

import java.util.Arrays;
import java.util.Collections;

class Solution {

    public static void main(String[] args) {

        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
        System.out.println(Arrays.toString(new Solution().solution(info, query)));

    }

    private static final int BIT = 3;
    private static final int MASK = ~(-1 << BIT);

    public int[] solution(String[] info, String[] query) {

        int iLen = info.length;
        int qLen = query.length;
        int[] answer = new int[qLen];

        String[] split;
        Integer[] bitInfo = new Integer[iLen];

        for (int i = 0; i < iLen; i++) {

            split = info[i].split(" ");
            bitInfo[i] = Integer.parseInt(split[4]);

            for (int j = 3; j >= 0; j--) {

                bitInfo[i] <<= BIT;
                int temp = 0;

                switch(split[j].charAt(0)) {
                    case 'j': case 'b':
                        temp = 1; break;
                    case 'c': case 'f': case 's':
                        temp = 2; break;
                    case 'p':
                        temp = 4; break;
                }

                bitInfo[i] |= temp;

            }

        }

        Arrays.sort(bitInfo, Collections.reverseOrder());

        for (int i = 0; i < qLen; i++) {

            split = query[i].split(" and ");
            int bitQuery = Integer.parseInt(split[3].split(" ")[1]);

            for (int j = 3; j >= 0; j--) {

                bitQuery <<= BIT;
                int temp = 0;

                switch(split[j].charAt(0)) {
                    case 'j': case 'b':
                        temp = 1; break;
                    case 'c': case 'f': case 's':
                        temp = 2; break;
                    case 'p':
                        temp = 4; break;
                    case '-':
                        temp = MASK; break;
                }

                bitQuery |= temp;
            }

            for (int j = 0; j < iLen; j++) {

                boolean flag = true;
                int pivot = bitQuery;
                int temp = bitInfo[j];

                System.out.println((bitQuery >> (BIT << 2)) + " " + (temp >> (BIT << 2)));
                if ((bitQuery >> (BIT << 2)) > (temp >> (BIT << 2))) break;

                for (int k = 0; k < 4; k++) {
                    if ((temp & pivot & MASK) == 0) flag = false;
                    temp >>= BIT;
                    pivot >>= BIT;
                }

                if (flag && temp >= pivot)
                    answer[i]++;

            }

        }

        return answer;
    }

}













/*
테스트 1  〉 통과 ( 4.23ms, 51.9MB)
테스트 2  〉 통과 ( 1.33ms, 52.2MB)
테스트 3  〉 통과 ( 6.30ms, 53.2MB)
테스트 4  〉 통과 (34.87ms, 55.7MB)
테스트 5  〉 통과 (44.22ms, 55.6MB)
테스트 6  〉 통과 (40.37ms, 55.5MB)
테스트 7  〉 통과 (58.66ms, 58.7MB)
테스트 8  〉 통과 (55.90ms, 58.9MB)
테스트 9  〉 통과 (65.62ms, 61.2MB)
테스트 10 〉 통과 (80.69ms, 59MB)
테스트 11 〉 통과 (45.19ms, 55.3MB)
테스트 12 〉 통과 (40.44ms, 55.1MB)
테스트 13 〉 통과 (55.03ms, 58.9MB)
테스트 14 〉 통과 (54.84ms, 56.2MB)
테스트 15 〉 통과 (53.17ms, 56MB)
테스트 16 〉 통과 (42.68ms, 56.7MB)
테스트 17 〉 통과 (41.64ms, 55.9MB)
테스트 18 〉 통과 (53.71ms, 56.5MB)
 */