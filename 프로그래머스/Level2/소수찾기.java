//완전탐색
//https://programmers.co.kr/learn/courses/30/lessons/42839
package 프로그래머스.Level2;

import java.util.HashSet;
import java.util.Set;

public class 소수찾기 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution("011"));
    }

    static class Solution {

        static int size;
        static int[] input, number;
        static boolean[] selected;
        static Set<Integer> set = new HashSet<>(); // 소수가 담길 Set

        public int solution(String numbers) {
            size = numbers.length();
            input = new int[size];
            number = new int[size];
            selected = new boolean[size];

            char[] chs = numbers.toCharArray();
            for (int i=0; i<size; i++) {
                input[i] = chs[i] - '0';
            }

            for (int i=1; i<=size; i++) {
                permutation(0, i);
            }
            return set.size();
        }

        public void permutation(int cnt, int r) {
            if (cnt == r) {
                String s = "";
                for (int i=0; i<r; i++) {
                    s += String.valueOf(number[i]);
                }
                for (int i=0; i<s.length(); i++) {
                    if (s.charAt(i) != '0') {
                        s = s.substring(i, s.length());
                        if (isPrimeNumber(Integer.parseInt(s))) {
                            set.add(Integer.parseInt(s));
                        }
                        break;
                    }
                }
            }

            for (int i=0; i<size; i++) {
                if (selected[i]) continue;
                number[cnt] = input[i];
                selected[i] = true;
                permutation(cnt+1, r);
                selected[i] = false;
            }
        }

        public boolean isPrimeNumber(int n) {
            if (n == 1) return false;
            for (int i=2; i<n; i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
