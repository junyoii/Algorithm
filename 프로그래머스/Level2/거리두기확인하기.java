//https://programmers.co.kr/learn/courses/30/lessons/81302
package 프로그래머스.Level2;

import java.util.Arrays;

public class 거리두기확인하기 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(new String[][]{{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}})));
    }

    static class Solution {

        static int fx[] = {1, 0, -1, 0};
        static int fy[] = {0, 1, 0, -1};

        static int dx[] = {-2, 0, 2, 0};
        static int dy[] = {0, -2, 0, 2};
        static int sx[] = {1, -1, 1, -1};
        static int sy[] = {1, 1, -1, -1};

        public int[] solution(String[][] places) {
            int[] answers = new int[5];

            for (int t=0; t<5; t++) {
                String[] seat = places[t];
                boolean flag = true;
                loop:
                for (int i=0; i<5; i++) {
                    for (int j=0; j<5; j++) {
                        if (seat[i].charAt(j) == 'P' && !solve(seat, i, j)) {
                            flag = false;
                            break loop;
                        }
                    }
                }
                answers[t] = (!flag) ? 0 : 1;
            }
            return answers;
        }

        public static boolean solve(String[] seat, int x, int y) {
            // 거리1) 검사
            for (int d=0; d<4; d++) {
                int nx = x + fx[d];
                int ny = y + fy[d];
                if (outRange(nx,ny)) continue;

                if (seat[nx].charAt(ny) == 'P') return false;
            }


            // 거리2) 직선방향 검사
            for (int d=0; d<4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (outRange(nx,ny) || seat[nx].charAt(ny) != 'P') continue;

                if (seat[x+dx[d]/2].charAt(y+dy[d]/2) != 'X') return false;
            }

            // 거리2) 대각선방향 검사
            for (int d=0; d<4; d++) {
                int nx = x + sx[d];
                int ny = y + sy[d];
                if (outRange(nx,ny) || seat[nx].charAt(ny) != 'P') continue;

                if (seat[x+sx[d]].charAt(y) != 'X') return false;
                if (seat[x].charAt(y+sy[d]) != 'X') return false;
            }
            return true;
        }

        public static boolean outRange(int x, int y) {
            return x<0 || x>=5 || y<0 || y>=5;
        }
    }
}
