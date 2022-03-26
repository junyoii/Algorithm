package 백준;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 마법사상어와파이어볼 {

    static StringTokenizer st;
    static int N, M, K;
    static int massSum;
    static List<FireBall> fireBall = new ArrayList<>();

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireBall.add(new FireBall(x, y, m, s, d));
        }

        for (int i=0; i<K; i++) { // k번 명령
            move();
        }
        getMassSum();
        System.out.println(massSum);
    }

    public static void getMassSum() {
        for (int i=0; i<fireBall.size(); i++) {
            FireBall f = fireBall.get(i);
            massSum += f.m;
        }
    }

    public static Point moveCoord(int x, int y, int d, int s) {
        int nx=x, ny=y;
        while (s-- > 0) {
            nx = nx + dx[d];
            ny = ny + dy[d];

            if (nx < 1) nx = N;
            if (ny < 1) ny = N;
            if (nx > N) nx = 1;
            if (ny > N) ny = 1;
        }
        return new Point(nx, ny);
    }

    public static void move() {
        ArrayList<FireBall>[][] tmp = new ArrayList[N+1][N+1];
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                tmp[i][j] = new ArrayList<FireBall>();
            }
        }

        for (int i=0; i<fireBall.size(); i++) { // 모든 파이어볼이 자신의 방향 d로 속력 s칸 만큼 이동
            FireBall f = fireBall.get(i);

            Point point = moveCoord(f.x, f.y, f.d, f.s);
            f.x = point.x;
            f.y = point.y;
            tmp[point.x][point.y].add(f);
        }

        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                if (tmp[i][j].size() > 1) { // 2개 이상의 파이어볼이 있는 칸

                    int mSum = 0, sSum = 0;
                    boolean even = false, odd = false; // 전부 짝수거나 전부 홀수인 경우를 구분하기 위한 플래그

                    for (int k=0; k<tmp[i][j].size(); k++) {
                        FireBall f = tmp[i][j].get(k);
                        mSum += f.m;
                        sSum += f.s;
                        if (f.d % 2 == 0) even = true;
                        else odd = true;
                        fireBall.remove(f);
                    }
                    int m = mSum / 5;
                    int s = sSum / tmp[i][j].size();
                    if (m > 0) {
                        for (int k=0; k<4; k++) {
                            int d = (even && odd) ? 2*k+1 : 2*k;
                            fireBall.add(new FireBall(i, j, m, s, d));
                        }
                    }
                }
            }
        }
    }

    static class FireBall {
        int x, y, m, s, d;
        boolean isAlive = true;

        public FireBall(int x, int y, int m, int s, int d) {
            this.x = x;
            this.y = y;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}
