package bj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * ABCDE
 * 그래프 이론
 * 그래프 탐색
 * 깊이 우선 탐색
 */
public class bj13023 {
    static int n, m;
//        static int[][] map;
    static List<Integer>[] list;
    static boolean[] check;
    static boolean status;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

//        map = new int[n][n];

        list = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

//            map[a][b] = 1;
//            map[b][a] = 1;
            list[a].add(b);
            list[b].add(a);
        }

        for (int i = 0; i < n; i++) {
            check = new boolean[n];
            dfs(i, 1);
            if (status) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }

    private static void dfs(int idx, int depth) {
        if (depth == 5) {
            status = true;
            return;
        }
        check[idx] = true;
//        for (int i = 0; i < n; i++) {
//            if (map[i][idx] == 1 && !check[i]) {
//                dfs(i, depth + 1);
//            }
//        }
//
        for (int next : list[idx]) {
            if (!check[next]) {
                dfs(next, depth + 1);
            }
        }

        check[idx] = false;
    }
}
