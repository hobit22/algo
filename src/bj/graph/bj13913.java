package bj.graph;

import java.util.*;

/**
 * 숨바꼭질 4
 * 그래프 이론
 * 그래프 탐색
 * 너비 우선 탐색
 */

public class bj13913 {
    static int max = 100_000;
    static int n, k;
    static boolean[] visited;
    static int[] prev;
    static int answer = Integer.MAX_VALUE;
    static ArrayList<Integer> route;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        visited = new boolean[max + 1];
        prev = new int[max + 1];
        bfs();

        System.out.println(answer);

        Stack<Integer> stack = new Stack<>();
        int idx = k;
        while (idx != n) {
            stack.push(idx);
            idx = prev[idx];
        }
        stack.push(n);

        while(!stack.empty()){
            System.out.print(stack.pop() + " ");
        }


    }

    private static void bfs() {
        Queue<Loc> q = new LinkedList<>();
        q.add(new Loc(n, 0));

        visited[n] = true;
        while (!q.isEmpty()) {
            Loc now = q.poll();

            if (now.idx == k && now.time < answer) {
                answer = now.time;
                return;
            }

            if (now.idx * 2 <= max && !visited[now.idx * 2]) {
                visited[now.idx * 2] = true;
                q.add(new Loc(now.idx * 2, now.time + 1));
                prev[now.idx * 2] = now.idx;
            }
            if (now.idx + 1 <= max && !visited[now.idx + 1]) {
                visited[now.idx + 1] = true;
                q.add(new Loc(now.idx + 1, now.time + 1));
                prev[now.idx + 1] = now.idx;
            }
            if (now.idx - 1 >= 0 && !visited[now.idx - 1]) {
                visited[now.idx - 1] = true;
                q.add(new Loc(now.idx - 1, now.time + 1));
                prev[now.idx - 1] = now.idx;
            }
        }
    }

    private static class Loc {
        int idx;
        int time;

        public Loc(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }
}
