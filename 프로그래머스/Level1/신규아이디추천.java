//문자열
//2021 KAKAO BLIND RECRUITMENT
//https://programmers.co.kr/learn/courses/30/lessons/72410
package 프로그래머스.Level1;

public class 신규아이디추천 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution("=.="));
    }

    static class Solution {
        public String solution(String new_id) {
            // 1단계
            new_id = new_id.toLowerCase();

            String id = "";
            // 2단계
            for (int i=0; i<new_id.length(); i++) {
                char ch = new_id.charAt(i);

                if (ch>='a' && ch<='z') id += String.valueOf(ch);
                if (ch>='0' && ch<='9') id += String.valueOf(ch);
                if (ch=='-' || ch=='_' || ch=='.') id += String.valueOf(ch);
            }

            // 3단계
            while (id.contains("..")) {
                id = id.replace("..", ".");
            }

            // 4단계
            if (id.startsWith(".")) {
                id = id.substring(1, id.length());
            }
            if (id.endsWith(".")) {
                id = id.substring(0, id.length()-1);
            }

            // 5단계
            if (id.length() == 0) {
                id += "a";
            }

            // 6단계
            if (id.length() >= 16) {
                id = id.substring(0, 15);
                if (id.endsWith(".")) {
                    id = id.substring(0, 14);
                }
            }

            // 7단계
            if (id.length() <= 2) {
                int diff = 3 - id.length();
                char lastCh = id.charAt(id.length()-1);

                for (int i=0; i<diff; i++) {
                    id += String.valueOf(lastCh);
                }
            }
            return id;
        }
    }
}
