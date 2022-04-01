package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 달이차오른다가자 {

    static StringTokenizer st;
    static int N, M, answer;
    static char[][] map;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i=0; i<N; i++) {
            String s = in.readLine();
            for (int j=0; j<M; j++) {
                map[i][j] = s.charAt(j);
            }
        }

    }
}
