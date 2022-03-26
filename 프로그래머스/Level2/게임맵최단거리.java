//https://programmers.co.kr/learn/courses/30/lessons/1844
package 프로그래머스.Level2;

import java.awt.*;
import java.util.Queue;
import java.util.LinkedList;

public class 게임맵최단거리 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[][]{{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}}));
    }

    static class Solution {

        static int N, M;
        static int[] dx = {0, 1, 0, -1};
        static int[] dy = {1, 0, -1, 0};

        static Queue<Point> queue = new LinkedList<>();
        static int[][] isVisited;

        public int solution(int[][] maps) {
            N = maps.length;
            M = maps[0].length;
            isVisited = new int[N][M];

            solve(maps);

            return (isVisited[N-1][M-1] == 0) ? -1 : isVisited[N-1][M-1];
        }

        public static void solve(int[][] maps) {
            queue.add(new Point(0,0));
            isVisited[0][0] = 1;

            while (!queue.isEmpty()) {
                Point current = queue.poll();

                for (int d=0; d<4; d++) {
                    int nx = current.x + dx[d];
                    int ny = current.y + dy[d];

                    if (isRange(nx,ny) || isVisited[nx][ny] != 0 || maps[nx][ny] == 0) continue;
                    queue.offer(new Point(nx, ny));
                    isVisited[nx][ny] = isVisited[current.x][current.y] + 1;
                }
            }
        }

        public static boolean isRange(int x, int y) {
            return x<0 || x>=N || y<0 || y>=M;
        }
    }
}
