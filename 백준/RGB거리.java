package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGB거리 {

    static StringTokenizer st;
    static int N, answer;
    static int[][] d;
    static int R, G, B;

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        d = new int[N+1][3]; // R G B

        st = new StringTokenizer(in.readLine());
        R = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        d[1][0] = R;
        d[1][1] = G;
        d[1][2] = B;

        for (int i=2; i<=N; i++) {
            st = new StringTokenizer(in.readLine());
            R = Integer.parseInt(st.nextToken());
            G = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            d[i][0] = Math.min(d[i-1][1], d[i-1][2]) + R;
            d[i][1] = Math.min(d[i-1][0], d[i-1][2]) + G;
            d[i][2] = Math.min(d[i-1][0], d[i-1][1]) + B;
        }
        answer = Math.min(Math.min(d[N][0], d[N][1]), d[N][2]);
        System.out.println(answer);
    }
}
