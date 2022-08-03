package bj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj1939 {
    static int N,M;
    static ArrayList<ArrayList<Island>> bridgesList = new ArrayList<>();
    static boolean[] visited;
    static int Start, End;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int max = 0;

        for (int i = 0; i < N + 1; i++) {
            bridgesList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            bridgesList.get(start).add(new Island(end, cost));
            bridgesList.get(end).add(new Island(start, cost));
            max = Math.max(max, cost);
        }

        st = new StringTokenizer(br.readLine());

        Start = Integer.parseInt(st.nextToken());
        End = Integer.parseInt(st.nextToken());

        int left = 0;
        int right = max;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            visited = new boolean[N + 1];

            if(bfs(mid)){
                left = mid + 1;
                answer = mid;
            }else{
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean bfs(int mid) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(Start);
        visited[Start] = true;

        while (!q.isEmpty()) {
            int now = q.poll();

            if(now == End) return true;

            for(Island island: bridgesList.get(now)){
                int cost = island.cost;
                int dest = island.dest;

                if(cost < mid) continue;
                if(visited[dest]) continue;

                visited[dest] = true;
                q.offer(dest);
            }
        }
        return false;
    }

    private static class Island {
        int dest, cost;

        public Island(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }
}
