//완전탐색
//https://programmers.co.kr/learn/courses/30/lessons/42842
package 프로그래머스.Level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 카펫 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(24,24)));
    }

    static class Solution {

        static int width, height;
        static List<Integer> divisor = new ArrayList<>();

        public int[] solution(int brown, int yellow) {
            int[] answer = new int[2];
            int area = brown + yellow;
            getDivisor(area);

            for (int i=0; i<divisor.size(); i++) {
                width = divisor.get(i); // 가로 길이
                height = area / width; // 세로 길이

                if (2*width + 2*height - 4 == brown) {
                    if ((width-2) * (height-2) == yellow) {
                        answer[0] = width;
                        answer[1] = height;
                        break;
                    }
                }
            }
            return answer;
        }

        public void getDivisor(int n) {
            for (int i=1; i<=Math.sqrt(n); i++) {
                if (n % i == 0) {
                    if (i == Math.sqrt(n)) divisor.add(i);
                    if (i != n/i) divisor.add(n/i);
                }
            }
            Collections.sort(divisor);
        }
    }
}
