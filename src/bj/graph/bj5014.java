package bj.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 스타트링크
 * 그래프 이론
 * 그래프 탐색
 * 너비 우선 탐색
 */
public class bj5014 {
    static int floor, start, goal, up, down;
    static int[] d;
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        floor = sc.nextInt();
        start = sc.nextInt();
        goal = sc.nextInt();
        up = sc.nextInt();
        down = sc.nextInt();

        d = new int[floor + 1];

        System.out.println(bfs(start));
    }

    private static String bfs(int start) {
        q.add(start);
        d[start] = 1;

        while (!q.isEmpty()) {
            int now = q.poll();
            if (now == goal) {
                return String.valueOf(d[now] - 1);
            }

            int nextUp = now + up;
            int nextDown = now - down;

            if (nextUp <= floor) {
                if (d[nextUp] == 0) {
                    d[nextUp] = d[now] + 1;
                    q.add(nextUp);
                }
            }
            if (nextDown > 0) {
                if (d[nextDown] == 0) {
                    d[nextDown] = d[now] + 1;
                    q.add(nextDown);
                }
            }
        }
        return "use the stairs";
    }
}
