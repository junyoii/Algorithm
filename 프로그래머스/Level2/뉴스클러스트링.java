//https://programmers.co.kr/learn/courses/30/lessons/17677
package 프로그래머스.Level2;

import java.util.ArrayList;
import java.util.List;

public class 뉴스클러스트링 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution("E=M*C^2", "e=m*c^2"));
    }

    static class Solution {

        static List<String> list1 = new ArrayList<>();
        static List<String> list2 = new ArrayList<>();

        static List<String> interSet = new ArrayList<>();
        static List<String> sumSet = new ArrayList<>();

        public int solution(String str1, String str2) {
            split(str1, list1);
            split(str2, list2);

            solve();
            double answer = (double) interSet.size() / sumSet.size();
            if (interSet.size() == 0 && sumSet.size() == 0) answer = 1;
            return (int) (answer * 65536);
        }

        public static void solve() {
            for (int i=0; i<list1.size(); i++) {
                String s = list1.get(i);
                int idx = list2.indexOf(s);
                if (idx != -1) { // 교집합에 해당되는 경우
                    interSet.add(s);
                    list2.remove(idx);
                }
                sumSet.add(s);
            }

            //교집합에 해당되지 않는 집합B의 원소들 -> 합집합으로
            sumSet.addAll(list2);
        }

        public static void split(String str, List<String> list) {
            for (int i=0; i<str.length()-1; i++) {
                String subStr = str.substring(i, i+2);
                subStr = subStr.toUpperCase();

                if (!(subStr.charAt(0) >= 'A') || !(subStr.charAt(0) <= 'Z')) continue;
                if (!(subStr.charAt(1) >= 'A') || !(subStr.charAt(1) <= 'Z')) continue;
                list.add(subStr);
            }
        }
    }
}
