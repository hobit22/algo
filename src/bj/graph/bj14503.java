package bj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj14503 {
    static int n, m;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int answer;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clean(r, c, d);

        System.out.println(answer);
    }

    private static void clean(int r, int c, int d) {
        if (map[r][c] == 0) {
            map[r][c] = 2;
            answer++;
        }

        flag = false;
        int ori_d = d;
        // 4 방향 체크
        for (int i = 0; i < 4; i++) {
            // 왼쪽부터 체크
            int nd = (d + 3) % 4;
            int nr = r + dr[nd];
            int nc = c + dc[nd];

            // 범위체크
            if (nr > 0 && nc > 0 && nr < n && nc < m) {
                // 0인지 확인
                if (map[nr][nc] == 0) {
                    clean(nr, nc, nd);
                    flag = true;
                    break;
                }
            }
            d = (d + 3) % 4;
        }

        // 4방향 갈 곳이 없을 때
        if (!flag) {
            // 후진
            int nd = (ori_d + 2) % 4;
            int nr = r + dr[nd];
            int nc = c + dc[nd];

            if (nr > 0 && nc > 0 && nr < n && nc < m) {
                if (map[nr][nc] != 1) {
                    clean(nr, nc, ori_d);
                }
            }
        }
    }
}