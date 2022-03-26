//2022 KAKAO BLIND RECRUITMENT
//https://programmers.co.kr/learn/courses/30/lessons/92334
package 프로그래머스.Level1;

import java.util.*;

public class 신고결과받기 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(new String[]{"muzi", "frodo", "apeach", "neo"}, new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"}, 2)));
    }

    static class Solution {

        static int[] result;

        public int[] solution(String[] id_list, String[] report, int k) {
            Map<String, HashSet<String>> map = new HashMap<>(); // 신고 당한 경우 (누가 나를 신고했는지, 중복 제거)
            Map<String, Integer> idxMap = new HashMap<>();

            for (int i=0; i<id_list.length; i++) {
                String name = id_list[i];
                map.put(name, new HashSet<>());
                idxMap.put(name, i);
            }

            for (int i=0; i<report.length; i++) {
                String[] s = report[i].split(" ");
                String from = s[0];
                String to = s[1];
                map.get(to).add(from);
            }

            int[] answer = new int[id_list.length];
            for (int i=0; i<id_list.length; i++) {
                HashSet<String> send = map.get(id_list[i]);
                if (send.size() >= k) {
                    for (String s : send) {
                        answer[idxMap.get(s)] += 1;
                    }
                }
            }
            return answer;
        }
    }
}
