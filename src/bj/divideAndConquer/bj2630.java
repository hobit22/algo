package bj.divideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj2630 {
    static int white = 0;
    static int blue = 0;
    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        partition(0, 0, N);

        System.out.println(white);
        System.out.println(blue);
    }

    private static void partition(int row, int col, int size) {
        if (colorCheck(row, col, size)) {
            if (map[row][col] == 0) {
                white++;
            } else {
                blue++;
            }
            return;
        }
        int nextSize = size / 2;
        partition(row, col, nextSize);
        partition(row, col + nextSize, nextSize);
        partition(row + nextSize, col, nextSize);
        partition(row + nextSize, col + nextSize, nextSize);

    }

    private static boolean colorCheck(int row, int col, int size) {
        int color = map[row][col];
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (map[i][j] != color) {
                    return false;
                }
            }
        }
        return true;
    }
}
