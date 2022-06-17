package bj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * RGB 거리 2
 * 다이나믹 프로그래밍
 * 1번 집의 색은 2번, N번 집의 색과 같지 않아야 한다.
 * N번 집의 색은 N-1번, 1번 집의 색과 같지 않아야 한다.
 * i(2 ≤ i ≤ N-1)번 집의 색은 i-1, i+1번 집의 색과 같지 않아야 한다.
 */
public class bj17404 {
    static int[][] d;
    static int[][] map;
    static final int INF = 1_000_000;
    static int answer = INF;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        map = new int[n][3];
        d = new int[n][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 첫번째 집이 빨강, 초록, 파랑일 때 순서대로
        for(int j = 0 ; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (i == j) d[0][i] = map[0][i];
                else d[0][i] = INF;
            }

            //N번 집의 색은 N-1번, 1번 집의 색과 같지 않아야 한다.
            for (int i = 1; i < n; i++) {
                d[i][0] = Math.min(d[i - 1][1], d[i - 1][2]) + map[i][0];
                d[i][1] = Math.min(d[i - 1][0], d[i - 1][2]) + map[i][1];
                d[i][2] = Math.min(d[i - 1][0], d[i - 1][1]) + map[i][2];
            }

            //1번 집의 색은 2번, N번 집의 색과 같지 않아야 한다.
            for (int i = 0; i < 3; i++) {
                if(i != j) answer = Math.min(answer, d[n-1][i]);
            }
        }

        System.out.println(answer);
    }
}
