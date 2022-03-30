package 백준;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

public class 치즈 {

    static StringTokenizer st;
    static int N, M;
    static int cnt, time, cheese;
    static int[][] map;
    static boolean[][] isVisited;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) cheese += 1;
            }
        }
        while (cheese != 0) {
            time += 1;
            cnt = cheese;
            melt();
        }
        System.out.println(time);
        System.out.println(cnt);
    }

    public static void melt() {
        Queue<Point> queue = new LinkedList<>();
        isVisited = new boolean[N][M];
        queue.offer(new Point(0,0));
        isVisited[0][0] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            for (int d=0; d<4; d++) {
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];
                if (nx<0 || nx>=N || ny<0 || ny>=M || isVisited[nx][ny]) continue;
                if (map[nx][ny] == 1) {
                    cheese -= 1;
                    map[nx][ny] = 0;
                } else if (map[nx][ny] == 0){
                    queue.offer(new Point(nx,ny));
                }
                isVisited[nx][ny] = true;
            }
        }
    }
}
