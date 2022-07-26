package bj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj1926 {
    static int N, M;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int cnt = 0;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(arr[i][j] == 0 || visited[i][j]) continue;

                cnt++;
                answer = Math.max(bfs(new Pos(i, j)), answer);
            }
        }
        System.out.println(cnt);
        System.out.println(answer);
    }

    private static int bfs(Pos pos) {
        Queue<Pos> q = new LinkedList<>();
        q.add(pos);
        visited[pos.x][pos.y] = true;

        int area = 1;

        while (!q.isEmpty()) {
            Pos now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nX = now.x + dx[i];
                int nY = now.y + dy[i];

                if (nX < 0 || nX >= N || nY < 0 || nY >= M) continue;
                if (visited[nX][nY]) continue;

                if (arr[nX][nY] == 1) {
                    area++;
                    visited[nX][nY]= true;
                    q.add(new Pos(nX, nY));
                }
            }
        }
        return area;
    }

    private static class Pos {
        int x;
        int y;

        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
