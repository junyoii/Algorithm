package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 청소년상어 {

    static StringTokenizer st;
    static int maxSum = 0;

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[4][4];
        List<Fish> fishes = new ArrayList<>();

        for (int i=0; i<4; i++) {
            st = new StringTokenizer(in.readLine());

            for (int j=0; j<4; j++) {
                Fish fish = new Fish();
                fish.num = Integer.parseInt(st.nextToken());
                fish.dir = Integer.parseInt(st.nextToken())-1;
                fish.x = i;
                fish.y = j;

                fishes.add(fish);
                map[i][j] = fish.num;
            }
        }
        Collections.sort(fishes);

        Fish fish = fishes.get(map[0][0] - 1); //(0,0) 위치의 물고기부터 먹기 시작한다.
        Shark shark = new Shark(0, 0, fish.dir, fish.num);
        fish.isAlive = false;
        map[0][0] = -1; // 현재 상어가 위치하는 칸.

        solve(map, fishes, shark);
        System.out.println(maxSum);
    }

    static void solve(int[][] map, List<Fish> fishes, Shark shark) {
        if(maxSum < shark.max) {
            maxSum = shark.max;
        }

        for (Fish fish : fishes) {
            moveFish(fish, map, fishes);
        }

        for (int i=1; i<4; i++) { // 상어가 갈 수 있는 위치는 총 3가지 (4x4 공간이기 때문)
            int nx = shark.x + dx[shark.dir] * i;
            int ny = shark.y + dy[shark.dir] * i;
            if (nx<0 || nx>=4 || ny<0 || ny>=4 || map[nx][ny] == 0) continue;

            // 원래는 3가지 경우를 백트래킹하기 위해서 데이터를 원상복구 시켜야 한다. 하지만 변경되는 데이터가 너무 복잡해서 그냥 복사해서 사용한다.
            int[][] copyMap = copyArr(map);
            List<Fish> copyFishes = copyList(fishes);

            copyMap[shark.x][shark.y] = 0; // 이동할 예정이므로 미리 -1 -> 0으로 값을 바꿔줌.
            Fish fish = copyFishes.get(map[nx][ny] - 1);
            Shark newShark = new Shark(fish.x, fish.y, fish.dir, shark.max + fish.num);
            fish.isAlive = false;
            copyMap[fish.x][fish.y] = -1; // 상어 위치 표시

            solve(copyMap, copyFishes, newShark);
        }
    }

    static void moveFish(Fish fish, int[][] map, List<Fish> fishes) {
        if (fish.isAlive == false) return; // 이미 잡아먹혔기 때문에 이동할 수 없다.

        for (int i=0; i<8; i++) { // 모든 방향을 탐색
            int nDir = (fish.dir + i) % 8;
            int nx = fish.x + dx[nDir];
            int ny = fish.y + dy[nDir];

            if (nx<0 || nx>=4 || ny<0 || ny>=4 || map[nx][ny] == -1) continue; // 범위 밖, 상어가 있는 칸

            //(nx, ny)는 이동할 수 있는 칸
            map[fish.x][fish.y] = 0;

            if (map[nx][ny] == 0) {
                fish.x = nx;
                fish.y = ny;
            }
            else {
                Fish tmp = fishes.get(map[nx][ny]-1);
                tmp.x = fish.x;
                tmp.y = fish.y;
                map[fish.x][fish.y] = tmp.num;

                fish.x = nx;
                fish.y = ny;
            }
            map[nx][ny] = fish.num;
            fish.dir = nDir;
            return;
        }
    }

    static List<Fish> copyList(List<Fish> fishes) {
        List<Fish> tmp = new ArrayList<>();
        for (Fish fish : fishes) {
            tmp.add(new Fish(fish.x, fish.y, fish.num, fish.dir, fish.isAlive));
        }
        return tmp;
    }

    static int[][] copyArr(int[][] map) {
        int[][] tmp = new int[4][4];
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                tmp[i][j] = map[i][j];
            }
        }
        return tmp;
    }

    static class Fish implements Comparable<Fish> {
        int x, y, num, dir;
        boolean isAlive = true;

        public Fish() {
        }

        public Fish(int x, int y, int num, int dir, boolean isAlive) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = dir;
            this.isAlive = isAlive;
        }

        @Override
        public int compareTo(Fish o) {
            return num - o.num;
        }
    }

    static class Shark {
        int x, y, dir, max;

        public Shark() {
        }

        public Shark(int x, int y, int dir, int max) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.max = max;
        }
    }
}
