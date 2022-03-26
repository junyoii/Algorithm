//https://programmers.co.kr/learn/courses/30/lessons/81301?language=java
package 프로그래머스.Level1;

import java.util.HashMap;
import java.util.Map;

public class 숫자문자열과영단어 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution("23four5six7"));
    }

    static class Solution {

        static Map<String, Integer> map = new HashMap<>();

        public int solution(String s) {

            map.put("zero", 0);
            map.put("one", 1);
            map.put("two", 2);
            map.put("three", 3);
            map.put("four", 4);
            map.put("five", 5);
            map.put("six", 6);
            map.put("seven", 7);
            map.put("eight", 8);
            map.put("nine", 9);


            for (String num : map.keySet()) {
                while (s.contains(num)) {
                    int idx = s.indexOf(num);

                    String temp = "";
                    temp += s.substring(0, idx);
                    temp += String.valueOf(map.get(num));
                    temp += s.substring(idx+num.length());
                    s = temp;
                }
            }
            return Integer.valueOf(s);
        }
    }
}