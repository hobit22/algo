package bj.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj14226 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        int answer = 0;

        boolean[][] visited = new boolean[2001][2001];

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(1, 0, 0));

        while (!q.isEmpty()) {
            Point now = q.poll();

            if (now.x == s) {
                answer = now.time;
                break;
            }

            if (now.x > 0 && now.clip < 2000) {
                if (!visited[now.x][now.x]) {
                    visited[now.x][now.x] = true;
                    q.add(new Point(now.x, now.time + 1, now.x));
                }
                if (!visited[now.x - 1][now.clip]) {
                    visited[now.x - 1][now.clip] = true;
                    q.add(new Point(now.x - 1, now.time + 1, now.clip));
                }
            }

            if (now.clip > 0 && now.x + now.clip < 2000) {
                if (!visited[now.x + now.clip][now.clip]) {
                    visited[now.x + now.clip][now.clip] = true;
                    q.add(new Point(now.x + now.clip, now.time + 1, now.clip));
                }
            }

        }
        System.out.println(answer);
    }

    private static class Point {
        int x;
        int time;
        int clip;

        public Point(int x, int time, int clip) {
            this.x = x;
            this.time = time;
            this.clip = clip;
        }
    }
}
