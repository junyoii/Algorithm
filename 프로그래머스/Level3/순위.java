package 프로그래머스.Level3;

public class 순위 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(5, new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}));
    }

    static class Solution {

        static int answer;
        static int[][] floyd;

        static int INF = 10000;

        public int solution(int n, int[][] results) {
            floyd = new int[n+1][n+1];

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    floyd[i][j] = INF;
                }
            }

            for (int i=0; i<results.length; i++) {
                int x = results[i][0];
                int y = results[i][1];
                floyd[x][y] = 1;
            }
            solve(n);
            return answer;
        }

        public void solve(int n) {
            for (int k=1; k<=n; k++) {
                for (int i=1; i<=n; i++) {
                    for (int j=1; j<=n; j++) {
                        if (floyd[i][j] > floyd[i][k] + floyd[k][j]) {
                            floyd[i][j] = floyd[i][k] + floyd[k][j];
                        }
                    }
                }
            }

            for (int i = 1; i <= n; i++) {
                int count = 0;

                for (int j = 1; j <= n; j++) {
                    if (floyd[i][j] < 10000 || floyd[j][i] < 10000) {
                        count++;
                    }
                }
                if (count == n - 1) answer++;
            }
        }
    }
}
