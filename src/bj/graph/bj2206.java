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
    static int[][] score;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0,};
    static int[] dy = {0, 0, -1, 1,};
    static int result = Integer.MAX_VALUE;

    //Todo 시간초과 해결해야함
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n+1][m+1];

        // map 입력받기
        for (int i = 0; i < n; i++) {
            String s = bf.readLine();
            for (int j = 0; j < m; j++) {
                map[i+1][j+1] = s.charAt(j) - '0';
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] == 1) {
                    map[i][j] = 0;
                    bfs();
                    map[i][j] = 1;
                }else{
                    bfs();
                }
            }
        }
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);

    }

    private static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(1,1));

        score = new int[n+1][m+1];
        score[1][1] = 1;

        visited = new boolean[n+1][m+1];
        visited[1][1] = true;
        while (!q.isEmpty()) {
            Point now = q.remove();

            if(now.x == n && now.y == m) break;

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                // 범위 체크
                if(nextX < 1 || nextX > n || nextY < 1 || nextY > m) continue;
                // 벽체크
                if(map[nextX][nextY] == 1) continue;

                if(visited[nextX][nextY]) continue;

                q.add(new Point(nextX, nextY));
                visited[nextX][nextY] = true;
                score[nextX][nextY] = score[now.x][now.y] + 1;
            }
        }

        if(score[n][m] != 0){
            result = Math.min(result, score[n][m]);
        }
    }

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
