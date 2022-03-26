package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 어른상어 {

    static StringTokenizer st;
    static int N, M, k;
    static int time;

    static int[][] resttime; // 냄새가 없어지기까지 남은 시간
    static int[][] smell; // 냄새를 남긴 상어의 번호
    static Shark[] shark;

    static int[][][] priority;

    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        resttime = new int[N+1][N+1];
        smell = new int[N+1][N+1];
        shark = new Shark[M+1];
        priority = new int[M+1][5][4];

        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j=1; j<=N; j++) {
                int s = Integer.parseInt(st.nextToken());
                if (s > 0) {
                    shark[s] = new Shark(i, j, 0);
                    smell[i][j] = s;
                    resttime[i][j] = k;
                }
            }
        }
        st = new StringTokenizer(in.readLine());
        for (int i=1; i<=M; i++) {
            shark[i].dir = Integer.parseInt(st.nextToken());
        }

        for (int i=1; i<=M; i++) {
            for (int j=1; j<=4; j++) {
                st = new StringTokenizer(in.readLine());
                for (int k=0; k<4; k++) {
                    priority[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }
        solve();
        System.out.println(time);
    }

    public static void solve() {

        while (true) {
            int count = 0;
            for (int i=1; i<=M; i++) {
                if (shark[i] != null) {
                    count += 1;
                }
            }

            if (count == 1 && shark[1] != null) { // 1번 상어만 남은 경우
                return;
            }
            if (time >= 1000) { // 시간이 1000이 넘어가는 경우
                time = -1;
                return;
            }

            int[][] tmp = new int[N+1][N+1];
            for (int i=1; i<=M; i++) {
                if (shark[i] != null) {
                    moveShark(tmp, i);
                }
            }

            for (int i=1; i<=N; i++) {
                for (int j=1; j<=N; j++) {
                    if (resttime[i][j] > 0) {
                        resttime[i][j] -= 1;
                    }

                    if (resttime[i][j] == 0) {
                        smell[i][j] = 0;
                    }
                }
            }

            for (int i=1; i<=N; i++) {
                for (int j=1; j<=N; j++) {
                    if (tmp[i][j] > 0) {
                        resttime[i][j] = k;
                        smell[i][j] = tmp[i][j];
                    }
                }
            }
            time += 1;
        }
    }

    public static void moveShark(int[][] tmp, int m) {

        int nDir=0, nx=0, ny=0;
        boolean flag = false;

        for (int i=0; i<4; i++) {
            nDir = priority[m][shark[m].dir][i];
            nx = shark[m].x + dx[nDir];
            ny = shark[m].y + dy[nDir];

            if (nx>=1 && nx<=N && ny>=1 && ny<=N && smell[nx][ny]==0){
                flag = true;
                break;
            }
        }

        if (!flag) {
            for (int i=0; i<4; i++) {
                nDir = priority[m][shark[m].dir][i];
                nx = shark[m].x + dx[nDir];
                ny = shark[m].y + dy[nDir];

                if (nx>=1 && nx<=N && ny>=1 && ny<=N && smell[nx][ny]==m) break;
            }
        }

        if (tmp[nx][ny] == 0) {
            tmp[nx][ny] = m;
            shark[m].x = nx;
            shark[m].y = ny;
            shark[m].dir = nDir;
        }
        else {
            shark[m] = null; // 번호 순서대로 상어를 움직이기 때문에 이미 해당 위치에 상어가 있다면 무조건 잡아먹힌다.
        }
    }

    static class Shark {
        int x, y, dir;

        public Shark(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
