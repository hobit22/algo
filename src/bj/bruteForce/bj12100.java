package bj.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2048(Easy)
 * 구현
 * 브루트포스 알고리즘
 * 시뮬레이션
 * 백트래킹
 */
public class bj12100 {
    static int n, answer = Integer.MIN_VALUE;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, map);
        System.out.println(answer);
    }

    private static void dfs(int depth, int[][] map) {
        if (depth == 5) {
            answer = Math.max(answer, findMax(map));
            return;
        }
        for (int i = 0; i < 4; i++) {
            dfs(depth + 1, move(i, map));
        }
    }

    private static int[][] move(int dir, int[][] arr) {
        Queue<Integer> q = new LinkedList<>();
        int[][] copy = new int[n][n];
        switch (dir) {
            case (0):
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (arr[j][i] != 0) q.offer(arr[j][i]);
                    }
                    move(copy, q, i, dir);
                }
                break;
            case (1):
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (arr[n - j - 1][i] != 0) q.offer(arr[n - j - 1][i]);
                    }
                    move(copy, q, i, dir);
                }
                break;
            case (2):
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (arr[i][j] != 0) q.offer(arr[i][j]);
                    }
                    move(copy, q, i, dir);
                }
                break;
            case (3):
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (arr[i][n - j - 1] != 0) q.offer(arr[i][n - j - 1]);
                    }
                    move(copy, q, i, dir);
                }
                break;
        }
        return copy;
    }

    private static void move(int[][] arr, Queue<Integer> q, int i, int dir) {
        int[] temp = new int[n];
        int num = 0, index = 0;
        while (!q.isEmpty()) {
            if (num == 0) num = q.poll();
            if (!q.isEmpty() && num == q.peek()) {
                q.poll();
                temp[index++] = num * 2;
            } else temp[index++] = num;
            num = 0;
        }
        //상
        if (dir == 0) for (int k = 0; k < n; k++) arr[k][i] = temp[k];
            //하
        else if (dir == 1) for (int k = 0; k < n; k++) arr[n - k - 1][i] = temp[k];
            //좌
        else if (dir == 2) for (int k = 0; k < n; k++) arr[i][k] = temp[k];
            //우
        else for (int k = 0; k < n; k++) arr[i][n - k - 1] = temp[k];
    }

    private static int findMax(int[][] map) {
        int tmp = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmp = Math.max(tmp, map[i][j]);
            }
        }
        return tmp;
    }

}
