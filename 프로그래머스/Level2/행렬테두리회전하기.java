package 프로그래머스.Level2;

import java.util.Arrays;

public class 행렬테두리회전하기 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(6,6,new int[][]{{2,2,5,4},{3,3,6,6},{5,1,6,3}})));
    }

    static class Solution {

        static int[][] arr;

        public int[] solution(int rows, int columns, int[][] queries) {
            int[] answers = new int[queries.length];

            arr = new int[rows+1][columns+1];
            for (int i=1; i<=rows; i++) {
                for (int j=1; j<=columns; j++) {
                    arr[i][j] = ((i-1) * columns + j);
                }
            }

            for (int i=0; i< queries.length; i++) {
                int x1 = queries[i][0];
                int y1 = queries[i][1];
                int x2 = queries[i][2];
                int y2 = queries[i][3];
                int minValue = rotate(x1, y1, x2, y2);
                answers[i] = minValue;
            }
            return answers;
        }

        public static int rotate(int x1, int y1, int x2, int y2) {
            int minValue = arr[x1][y2];

            int temp = arr[x1][y2];
            for (int i=y2; i > y1; i--) {
                arr[x1][i] = arr[x1][i-1];
                minValue = Math.min(minValue, arr[x1][i]);
            }
            for (int i=x1; i < x2; i++) {
                arr[i][y1] = arr[i+1][y1];
                minValue = Math.min(minValue, arr[i][y1]);
            }
            for (int i=y1; i < y2; i++) {
                arr[x2][i] = arr[x2][i+1];
                minValue = Math.min(minValue, arr[x2][i]);
            }
            for (int i=x2; i > x1 + 1; i--) {
                arr[i][y2] = arr[i-1][y2];
                minValue = Math.min(minValue, arr[i][y2]);
            }
            arr[x1+1][y2] = temp;
            return minValue;
        }
    }
}
