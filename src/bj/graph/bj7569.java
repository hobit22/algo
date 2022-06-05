package bj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 토마토
 * 그래프 이론
 * 그래프 탐색
 * 너비 우선 탐색
 */
public class bj7569 {
    // 전체 토마토 배열
    static int[][][] map;

    // 위치 이동 배열
    static int[] row = {-1, 0, 1, 0, 0, 0};
    static int[] col = {0, 1, 0, -1, 0, 0};
    static int[] height = {0, 0, 0, 0, 1, -1};

    // 입력받은 위치
    static int m, n, h;
    static Queue<Position> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h + 1][n + 1][m + 1];

        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 1; k <= m; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    // 1일 경우 queue 에 추가
                    if (map[i][j][k] == 1) {
                        queue.add(new Position(i, j, k));
                    }
                }
            }
        }

        int result = bfs();
        System.out.println(result);
    }

    private static int bfs() {
        // queue가 빌때까지
        while (!queue.isEmpty()) {
            Position position = queue.poll();

            int h = position.height;
            int r = position.row;
            int c = position.col;

            // 다음 position 위치 방향배열로 찾음
            for (int i = 0; i < 6; i++) {
                int dest_h = h + height[i];
                int dest_r = r + row[i];
                int dest_c = c + col[i];
                // 범위 체크
                if (checkBoundary(dest_h, dest_r, dest_c)) {
                    queue.add(new Position(dest_h, dest_r, dest_c));
                    //기존 map 보다 + 1 증가한 값 넣기
                    map[dest_h][dest_r][dest_c] = map[h][r][c] + 1;
                }
            }
        }

        // map 에서 최댓값 찾아서 return
        return findMax();
    }

    private static boolean checkBoundary(int height, int row, int col) {
        // 범위를 벗어나면 false
        if (height < 1 || height > h || row < 1 || row > n || col < 1 || col > m) {
            return false;
        }

        // 범위를 벗어나진 않지만
        // 0 이 아니면 한번 방문 한것이기 때문에
        return map[height][row][col] == 0;
    }

    private static int findMax() {
        int result = Integer.MIN_VALUE;

        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= m; k++) {
                    if (map[i][j][k] == 0) return -1;
                    result = Math.max(result, map[i][j][k]);
                }
            }
        }

        // 기본적으로 모두 1로 차있다면 result 가 1이기 때문에 1 빼줌
        return result - 1;
    }

    private static class Position {
        int height;
        int row;
        int col;

        public Position(int height, int row, int col) {
            this.height = height;
            this.row = row;
            this.col = col;
        }
    }
}
