package bj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj1261 {
    static int m, n;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String s = bf.readLine();
            for (int j = 1; j <= m; j++) {
                map[i][j] = s.charAt(j - 1) - '0';
            }
        }

        int ans = bfs(1, 1);
        System.out.println(ans);
    }

    private static int bfs(int x, int y) {
        PriorityQueue<Point> q = new PriorityQueue<>();
        q.add(new Point(x, y, 0));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Point now = q.poll();

            if (now.x == n && now.y == m) {
                return now.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 1 || nx > n || ny < 1 || ny > m) {
                    continue;
                }

                if (!visited[nx][ny] && map[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    q.offer(new Point(nx, ny, now.cnt));
                }
                if (!visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    q.offer(new Point(nx, ny, now.cnt + 1));
                }
            }
        }
        return 0;
    }

    //우선순위 큐의 타입이 되기 위해 Comparable 상속받음
    private static class Point implements Comparable<Point> {
        int x;
        int y;
        int cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point a) {
            return cnt - a.cnt;
        }
    }
}
