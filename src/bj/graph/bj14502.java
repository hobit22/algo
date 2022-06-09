package bj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 연구소
 * 구현
 * 그래프 이론
 * 브루트포스 알고리즘
 * 그래프 탐색
 * 너비 우선 탐색
 */
public class bj14502 {
    static int n, m;
    static int[][] map;
    static int[][] new_map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        new_map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        new_map = map;

        dfs(0);

        System.out.println(result);
    }

    private static void dfs(int d) {
        if(d == 3){
            bfs();
            return;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j] == 0){
                    map[i][j] = 1;
                    dfs(d + 1);
                    map[i][j] = 0;
                }
            }
        }

    }

    private static void bfs() {
        int[][] vmap = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        Queue<Point> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                vmap[i][j] = map[i][j];
                if(vmap[i][j] == 2){
                    q.add(new Point(i, j));
                }
            }
        }

        while (!q.isEmpty()) {
            Point now = q.remove();
            int nowX = now.x;
            int nowY = now.y;

            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];

                //범위 측정
                if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= m)
                    continue;

                //벽 인지
                if(vmap[nextX][nextY] == 1)
                    continue;

                if(!visited[nextX][nextY]){
                    q.add(new Point(nextX, nextY));
                    visited[nextX][nextY] = true;
                    vmap[nextX][nextY] = 2;
                }
            }
        }

        countSafeZone(vmap);
    }

    private static void countSafeZone(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j] == 0) cnt++;
            }
        }
        result = Math.max(result, cnt);
    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
