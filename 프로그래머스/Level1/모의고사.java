//완전탐색
//https://programmers.co.kr/learn/courses/30/lessons/42840
package 프로그래머스.Level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 모의고사 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(new int[]{1,3,2,4,2})));
    }

    static class Solution {
        public int[] solution(int[] answers) {

            int[] one = {1,2,3,4,5};
            int[] two = {2,1,2,3,2,4,2,5};
            int[] three = {3,3,1,1,2,2,4,4,5,5};

            int[] count = new int[3];

            for (int i=0; i<answers.length; i++) {
                if (one[i % one.length] == answers[i]) count[0] += 1;
                if (two[i % two.length] == answers[i]) count[1] += 1;
                if (three[i % three.length] == answers[i]) count[2] += 1;
            }
            int maxVal = Math.max(Math.max(count[0], count[1]), count[2]);
            List<Integer> list = new ArrayList<>();

            for (int i=0; i<3; i++) {
                if (count[i] == maxVal) {
                    list.add(i+1);
                }
            }
            int[] answer = new int[list.size()];
            for (int i=0; i<answer.length; i++) {
                answer[i] = list.get(i);
            }
            return answer;
        }
    }
}
