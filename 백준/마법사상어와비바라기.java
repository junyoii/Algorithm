package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 마법사상어와비바라기 {

    static StringTokenizer st;
    static int N, M;
    static int answer;
    static int[][] arr;
    static boolean[][] visited;
    static List<Cloud> clouds = new ArrayList<>();

    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1}; // 1~8
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1][N+1];

        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j=1; j<=N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clouds.add(new Cloud(N, 1));
        clouds.add(new Cloud(N, 2));
        clouds.add(new Cloud(N-1, 1));
        clouds.add(new Cloud(N-1, 2));

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(in.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            visited = new boolean[N+1][N+1];

            for (Cloud cloud: clouds) {
                move(cloud, d, s);
                rain(cloud);
            }
            clouds.clear();
            magic();
        }
        getSum();
        System.out.println(answer);
    }

    public static void magic() {
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {

                if (visited[i][j]) {
                    int count = 0;
                    for (int d=2; d<=8; d+=2) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (nx<1 || nx>N || ny<1 || ny>N || arr[nx][ny]==0) continue;
                        count += 1;
                    }
                    arr[i][j] += count;
                }
            }
        }

        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                if (!visited[i][j] && arr[i][j]>=2) {
                    clouds.add(new Cloud(i,j));
                    arr[i][j] -= 2;
                }
            }
        }
    }

    public static void rain(Cloud cloud) {
        int x = cloud.x;
        int y = cloud.y;
        arr[x][y] += 1;
        visited[x][y] = true;
    }

    public static void move(Cloud cloud, int d, int s) {
        for (int i=0; i<s; i++) {
            cloud.x = cloud.x + dx[d];
            if (cloud.x == 0) {
                cloud.x = N;
            }
            if (cloud.x == N + 1) {
                cloud.x = 1;
            }
        }

        for (int i=0; i<s; i++) {
            cloud.y = cloud.y + dy[d];
            if (cloud.y == 0) {
                cloud.y = N;
            }
            if (cloud.y == N + 1) {
                cloud.y = 1;
            }
        }
    }

    public static void getSum() {
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                answer += arr[i][j];
            }
        }
    }

    public static class Cloud {
        int x, y;

        public Cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
