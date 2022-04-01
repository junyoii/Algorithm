package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 녹색옷입은애가젤다지 {

    static StringTokenizer st;
    static int N, T;
    static int[][] adjMatrix;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            N = Integer.parseInt(in.readLine());
            if (N == 0) break; // N = 0인 입력이 주어지면 전체 입력이 종료

            adjMatrix = new int[N][N]; // 각 칸에 있는 도둑루피의 크기
            for (int i=0; i<N; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j=0; j<N; j++) {
                    adjMatrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int[][] distance = new int[N][N]; // [0][0]번 칸에서 해당 위치까지 가는 최소 비용
            boolean[][] visited = new boolean[N][N]; // 최소 비용을 계산했는지 여부
            PriorityQueue<Vertex> pQueue = new PriorityQueue<>();

            for (int i=0; i<N; i++) {
                Arrays.fill(distance[i], Integer.MAX_VALUE);
            }
            distance[0][0] = adjMatrix[0][0]; // start
            pQueue.offer(new Vertex(0, 0, distance[0][0]));

            while (!pQueue.isEmpty()) {
                Vertex current = pQueue.poll();
                if (visited[current.x][current.y]) continue;
                visited[current.x][current.y] = true;

                // 현재 좌표에서 4방 탐색하며 최소 이동 비용 갱신
                for (int d=0; d<4; d++) {
                    int nx = current.x + dx[d];
                    int ny = current.y + dy[d];
                    if (nx<0 || nx>=N || ny<0 || ny>=N) continue;
                    if (!visited[nx][ny] && distance[nx][ny] > distance[current.x][current.y] + adjMatrix[nx][ny]) {
                        // 현재 좌표를 경유해서 가는 게 더 비용이 적게 드는 경우
                        distance[nx][ny] = distance[current.x][current.y] + adjMatrix[nx][ny];
                        pQueue.offer(new Vertex(nx, ny, distance[nx][ny]));
                    }
                }
            }
            System.out.println("Problem " + ++T + ": " + distance[N-1][N-1]);
        }
    }

    public static class Vertex implements Comparable<Vertex> {
        int x, y, minDistance;

        public Vertex(int x, int y, int minDistance) {
            this.x = x;
            this.y = y;
            this.minDistance = minDistance;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.minDistance - o.minDistance;
        }
    }
}
