package bj.graph;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 이분 그래프
 *
 * 그래프 이론
 * 그래프 탐색
 * 너비 우선 탐색
 * 깊이 우선 탐색
 */
public class bj1707 {

    static ArrayList<ArrayList<Integer>> list;
    static int[] team;
    static int v, e;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int k = Integer.parseInt(st.nextToken());

        while (k-- > 0) {
            st = new StringTokenizer(bf.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            team = new int[v];
            list = new ArrayList<>();

            for (int i = 0; i < v; i++) {
                list.add(new ArrayList<>());
            }

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;

                list.get(a).add(b);
                list.get(b).add(a);
            }

            String answer = bfs();

            System.out.println(answer);
        }
    }

    private static String bfs() {
        for (int i = 0; i < v; i++) {
            Queue<Integer> q = new LinkedList<>();
            if (team[i] == 0) { // 방문한적 없으면,
                q.add(i);
                team[i] = 1;
            }

            while (!q.isEmpty()) {
                int now = q.poll();

                for (Integer n : list.get(now)) {
                    if (team[n] == 0) {
                        team[n] = team[now] * -1;
                        q.add(n);
                    }
                    if (team[now] == team[n]) {
                        return "NO";
                    }
                }
            }
        }
        return "YES";

    }
}
