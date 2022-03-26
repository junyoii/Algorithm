package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 마법사상어와토네이도 {

    static StringTokenizer st;
    static int N;
    static int answer;
    static int map[][];

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[] dc = {1, 1, 2, 2};

    static int[][] sx = {{-1,1,-2,-1,1,2,-1,1,0}, {-1,-1,0,0,0,0,1,1,2},
            {1,-1,2,1,-1,-2,1,-1,0}, {1,1,0,0,0,0,-1,-1,-2}};
    static int[][] sy = {{1,1,0,0,0,0,-1,-1,-2},{-1,1,-2,-1,1,2,-1,1,0},
            {-1,-1,0,0,0,0,1,1,2},{1,-1,2,1,-1,-2,1,-1,0}};

    static int[] p = {1,1,2,7,7,2,10,10,5};

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        map = new int[N][N];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        move(N/2, N/2);
        System.out.println(answer);
    }

    public static void move(int x, int y) {

        int curX = x;
        int curY = y;

        while (true) {
            for (int dir=0; dir<4; dir++) { // 4방향을 계속 빙빙 돈다.
                for (int moveCount=0; moveCount<dc[dir]; moveCount++) {

                    int nx = curX + dx[dir];
                    int ny = curY + dy[dir];
                    if (nx<0 || nx>=N || ny<0 || ny>=N) return;

                    int totalSand = map[nx][ny];
                    int spreadTotal = 0;
                    map[nx][ny] = 0;

                    for (int spread=0; spread<9; spread++) {
                        int sandX = nx + sx[dir][spread];
                        int sandY = ny + sy[dir][spread];
                        int spreadAmount = (totalSand * p[spread]) / 100;

                        if (sandX<0 || sandX>=N || sandY<0 || sandY>=N) {
                            answer += spreadAmount;
                        }
                        else {
                            map[sandX][sandY] += spreadAmount;
                        }
                        spreadTotal += spreadAmount;
                    }

                    int ax = nx + dx[dir];
                    int ay = ny + dy[dir];
                    int aAmount = totalSand - spreadTotal;
                    if (ax<0 || ax>=N || ay<0 || ay>=N) {
                        answer += aAmount;
                    }
                    else {
                        map[ax][ay] += aAmount;
                    }

                    curX = nx;
                    curY = ny;
                }
            }
            for (int i=0; i<4; i++) {
                dc[i] += 2;
            }
        }
    }
}
