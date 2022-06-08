package bj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 맥주 마시면서 걸어가기
 */
public class bj9205 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        ArrayList<Point> input;
        ArrayList<ArrayList<Integer>> graph;

        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());

            input = new ArrayList<>();

            for (int i = 0; i < n + 2; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                input.add(new Point(x, y));
            }

            graph = new ArrayList<>();

            for (int i = 0; i < n + 2; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < n + 2; i++) {
                for (int j = i + 1; j < n + 2; j++) {
                    if (Math.abs(input.get(i).x - input.get(j).x) + Math.abs(input.get(i).y - input.get(j).y) <= 1000) {
                        graph.get(i).add(j);
                        graph.get(j).add(i);
                    }
                }
            }
            System.out.println(bfs(graph, n));
        }
    }

    private static String bfs(ArrayList<ArrayList<Integer>> graph, int n) {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);

        boolean[] visited = new boolean[n + 2];
        visited[0] = true;
        while (!q.isEmpty()) {
            int now = q.poll();

            if (now == n + 1) {
                return "happy";
            }

            for (int next : graph.get(now)) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
        return "sad";
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