//2017 카카오코드 본선
//https://programmers.co.kr/learn/courses/30/lessons/1835
package 프로그래머스.Level2;

import java.util.Arrays;
import java.util.List;

public class 단체사진찍기 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(2, new String[]{"N~F=0", "R~T>2"}));
    }

    static class Solution {
        public int solution(int n, String[] data) {
            int answer = 0;
            char[] kakaoFriends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};

            do {
                boolean flag = true; // 만약 모든 조건이 만족한다면 true로 남아있음.
                for (int i=0; i<data.length; i++) {
                    String str = String.valueOf(kakaoFriends);

                    char from = data[i].charAt(0);
                    char to = data[i].charAt(2);
                    char compare = data[i].charAt(3);
                    int num = data[i].charAt(4) - '0';

                    int diff = Math.abs(str.indexOf(from) - str.indexOf(to));

                    if (compare == '=') {
                        if (diff - 1 != num) {
                            flag = false;
                            break;
                        }
                    }
                    else if (compare == '>') {
                        if (!(diff - 1 > num)) {
                            flag = false;
                            break;
                        }
                    }
                    else if (compare == '<') {
                        if (!(diff - 1 < num)) {
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag) answer += 1;
            } while(np(kakaoFriends));

            return answer;
        }

        public boolean np(char[] p) {
            int N = p.length;

            int i = N-1;
            while (i>0 && p[i-1] > p[i]) i--;

            if (i == 0) return false;

            int j = N-1;
            while(p[i-1] >= p[j]) j--;

            swap(p, i-1, j);

            int k = N-1;
            while(i < k) {
                swap(p, i++, k--);
            }
            return true;
        }

        public void swap(char[] p, int i, int j) {
            char temp = p[i];
            p[i] = p[j];
            p[j] = temp;
        }
    }
}
