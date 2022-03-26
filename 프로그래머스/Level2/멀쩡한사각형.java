//https://programmers.co.kr/learn/courses/30/lessons/62048?language=java
package 프로그래머스.Level2;

public class 멀쩡한사각형 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(8, 12));
    }

    static class Solution {
        public long solution(long w, long h) {
            long answer = 0;

            for (int i=0; i<w; i++) {
                answer += h * i / w;
            }
            return answer * 2;
        }
    }
}