package bj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 벽 부수고 이동하기
 * 그래프 이론
 * 그래프 탐색
 * 너비 우선 탐색
 */
public class bj2206 {
    static int n, m;
    static int[][] map;
    static int[][] visited;
    static int[] dx = {-1, 1, 0, 0,};
    static int[] dy = {0, 0, -1, 1,};

    //Todo 시간초과 해결해야함
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n+1][m+1];
        visited = new int[n+1][m+1];

        // map 입력받기
        for (int i = 0; i < n; i++) {
            String s = bf.readLine();
            for (int j = 0; j < m; j++) {
                map[i+1][j+1] = s.charAt(j) - '0';
                visited[i+1][j+1] = Integer.MAX_VALUE;
            }
        }

        int ans = bfs();

        System.out.println(ans);

    }

    private static int bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(1,1, 1, 0));
        visited[1][1] = 0;

        while (!q.isEmpty()) {
            Point now = q.remove();

            if(now.x == n && now.y == m) return now.distance;

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                // 범위 체크
                if(nextX < 1 || nextX > n || nextY < 1 || nextY > m) continue;

                if(visited[nextX][nextY] <= now.drill) continue;

                if (map[nextX][nextY] == 0) {
                    q.add(new Point(nextX, nextY, now.distance + 1, now.drill));
                    visited[nextX][nextY] = now.drill;
                } else {
                    if (now.drill == 0) {
                        q.add(new Point(nextX, nextY, now.distance + 1, now.drill + 1));
                        visited[nextX][nextY] = now.drill + 1;
                    }
                }
            }
        }
        return -1;
    }

    static class Point{
        int x, y, distance, drill;

        public Point(int x, int y, int distance, int drill) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.drill = drill;
        }
    }

}
