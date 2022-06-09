package bj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 미로탐색
 * 그래프 이론
 * 그래프 탐색
 * 너비 우선 탐색
 */
public class bj2178 {
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static Queue<Point> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1];
        visited[1][1] = true;

        for (int i = 1; i <= n; i++) {
            String s = br.readLine();
            for (int j = 1; j <= m; j++) {
                map[i][j] = s.charAt(j-1) - '0';
            }
        }
        bfs();
        System.out.println(map[n][m]);

    }

    private static void bfs() {

        q.add(new Point(1, 1));

        while (!q.isEmpty()) {
            Point now = q.poll();
            int nowx = now.x;
            int nowy = now.y;

            for (int i = 0; i < 4; i++) {
                int nextx = nowx + dx[i];
                int nexty = nowy + dy[i];

                // 범위 체크
                if (nextx > 0 && nextx < n + 1 && nexty > 0 && nexty < m + 1) {
                    // 방문 여부 체크 && 벽인지 체크
                    if (!visited[nextx][nexty] && map[nextx][nexty] != 0) {

                        q.add(new Point(nextx, nexty));
                        map[nextx][nexty] = map[nowx][nowy] + 1;
                        visited[nextx][nexty] = true;
                    }
                }
            }
        }
    }

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
