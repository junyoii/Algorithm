package 백준;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 마법사상어와파이어스톰 {

    static StringTokenizer st;
    static int N, Q;
    static int sum, count;

    static int[][] map;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(in.readLine());
        N = (int) Math.pow(2, Integer.parseInt(st.nextToken())); // 2^N
        Q = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(in.readLine());
        while (Q-- > 0) {
            int l = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
            fireStorm(N, l, 0, 0);
            melt();
        }
        getResult();

        System.out.println(sum);
        System.out.println(count);
    }

    public static void getResult() {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] isVisited = new boolean[N][N];

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                sum += map[i][j];
                if (isVisited[i][j] || map[i][j]==0) continue;

                queue.offer(new Point(i, j));
                isVisited[i][j] = true;
                int iceCnt = 0;

                while (!queue.isEmpty()) {
                    Point current = queue.poll();
                    iceCnt += 1;

                    for (int d=0; d<4; d++) {
                        int nx = current.x + dx[d];
                        int ny = current.y + dy[d];
                        if (Range(nx, ny) || isVisited[nx][ny] || map[nx][ny]==0) continue;
                        queue.offer(new Point(nx, ny));
                        isVisited[nx][ny] = true;
                    }
                }
                count = Math.max(count, iceCnt);
            }
        }
    }

    public static boolean Range(int x, int y) {
        return x<0 || y<0 || x>=N || y>=N;
    }

    public static void melt() {
        List<Point> list = new ArrayList<>();
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (map[i][j] == 0) continue;
                int nearIce = 0;

                for (int d=0; d<4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (Range(nx, ny) || map[nx][ny] <= 0) continue;
                    nearIce += 1;
                }
                if (nearIce < 3) list.add(new Point(i, j));
            }
        }

        for (Point p : list) {
            if (map[p.x][p.y] > 0) {
                map[p.x][p.y] -= 1;
            }
        }
    }

    public static void fireStorm(int n, int l, int r, int c) {
        // n은 현재 길이, l은 목표로 하는 길이
        if (n == l) {
            int[][] tmp = new int[n][n];
            int x = 0;

            for (int i=r; i<r+n; i++) {
                int y = 0;
                for (int j=c; j<c+n; j++) {
                    tmp[x][y] = map[i][j];
                    y += 1;
                }
                x += 1;
            }

            x = 0;
            for (int i=r; i<r+n; i++) {
                int y = 0;
                for (int j=c; j<c+n; j++) {
                    map[i][j] = tmp[n-y-1][x];
                    y += 1;
                }
                x += 1;
            }

            return;
        }
        fireStorm(n/2, l, r, c);
        fireStorm(n/2, l, r, c+n/2);
        fireStorm(n/2, l, r+n/2, c);
        fireStorm(n/2, l, r+n/2, c+n/2);
    }
}
