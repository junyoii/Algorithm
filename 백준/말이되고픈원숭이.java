package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

public class 말이되고픈원숭이 {

    static StringTokenizer st;
    static int K, W, H;
    static int answer = -1;
    static int[][] map;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] sx = {-1, 1, -2, 2, -2, 2, -1, 1};
    static int[] sy = {2, 2, 1, 1, -1, -1, -2, -2};

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(in.readLine());
        st = new StringTokenizer(in.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H+1][W+1];

        for (int i=1; i<=H; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j=1; j<=W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); //0:평지, 1:장애물
            }
        }
        bfs(1, 1, K);
        System.out.println(answer);
    }

    public static void bfs(int x, int y, int k) {
        Queue<Monkey> queue = new LinkedList<>();
        boolean[][][] isVisited = new boolean[H+1][W+1][K+1];
        queue.add(new Monkey(x, y, k, 0));
        isVisited[x][y][k] = true;

        while (!queue.isEmpty()) {
            Monkey m = queue.poll();
            if (m.x == H && m.y == W) {
                answer = m.moveCnt;
                return;
            }

            for (int d=0; d<4; d++) {
                int nx = m.x + dx[d];
                int ny = m.y + dy[d];
                if (outOfRange(nx,ny) || isVisited[nx][ny][m.k] || map[nx][ny] == 1) continue;
                queue.offer(new Monkey(nx, ny, m.k, m.moveCnt+1));
                isVisited[nx][ny][m.k] = true;
            }
            if (m.k > 0) {
                for (int d=0; d<8; d++) {
                    int nx = m.x + sx[d];
                    int ny = m.y + sy[d];
                    if (outOfRange(nx,ny) || isVisited[nx][ny][m.k-1] || map[nx][ny] == 1) continue;
                    queue.offer(new Monkey(nx, ny, m.k-1, m.moveCnt+1));
                    isVisited[nx][ny][m.k-1] = true;
                }
            }
        }
    }

    public static boolean outOfRange(int x, int y) {
        return x<=0 || x>H || y<=0 || y>W;
    }

    public static class Monkey {
        int x, y, k;
        int moveCnt;

        public Monkey(int x, int y, int k, int moveCnt) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.moveCnt = moveCnt;
        }
    }
}
