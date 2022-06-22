package bj.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj14226 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        int clip = 0;
        int answer = 0;

        int[] visited = new int[1001];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(1, 0, clip));

        while (!q.isEmpty()) {
            Point now = q.poll();

            if(now.x == s){
                answer = now.time;
                break;
            }

            if(visited[now.x] > now.time){
                visited[now.x] = now.time;
            }

            if(now.x + now.clip < 1000 && visited[now.x + now.clip] == 0 && now.clip > 0){
                q.add(new Point(now.x + now.clip, now.time + 1, now.clip));
            }
            if(now.x - 1 > 0 && visited[now.x - 1] == 0){
                q.add(new Point(now.x - 1, now.time + 1, clip));
            }

            q.add(new Point(now.x, now.time + 1, now.x));

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
