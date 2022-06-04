package bj.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 촌수 계산
 * 그래프 이론
 * 그래프 탐색
 * 너비 우선 탐색
 * 깊이 우선 탐색
 */
public class bj2644 {
    static int n;
    static int[][] graph;
    static int[] depth;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        int target1 = sc.nextInt();
        int target2 = sc.nextInt();

        int num = sc.nextInt();
        graph = new int[n + 1][n + 1];
        depth = new int[n + 1];

        for (int i = 0; i < num; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph[y][x] = 1;
            graph[x][y] = 1;
        }

        bfs(target1, target2);

        if(depth[target2] == 0){
            System.out.println(-1);
        }else{
            System.out.println(depth[target2]);
        }

    }

    private static void bfs(int start, int end) {
        Queue<Integer> q = new LinkedList<>();

        q.add(start);

        while (!q.isEmpty()) {
            int v = q.poll();
            if (v == end) {
                break;
            }
            for (int i = 1; i <= n; i++) {
                if(graph[v][i] == 1 && depth[i] == 0){
                    depth[i] = depth[v] + 1;
                    q.add(i);
                }
            }
        }
    }
}
