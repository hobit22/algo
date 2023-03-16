package programmers.kakao.tech2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.IntStream;


public class 등산코스_정하기 {
    /**
     * n	paths	gates	summits	result
     * 6	[[1, 2, 3], [2, 3, 5], [2, 4, 2], [2, 5, 4], [3, 4, 4], [4, 5, 3], [4, 6, 1], [5, 6, 1]]	[1, 3]	[5]	[5, 3]
     * 7	[[1, 4, 4], [1, 6, 1], [1, 7, 3], [2, 5, 2], [3, 7, 4], [5, 6, 6]]	[1]	[2, 3, 4]	[3, 4]
     * 7	[[1, 2, 5], [1, 4, 1], [2, 3, 1], [2, 6, 7], [4, 5, 1], [5, 6, 1], [6, 7, 1]]	[3, 7]	[1, 5]	[5, 1]
     * 5	[[1, 3, 10], [1, 4, 20], [2, 3, 4], [2, 4, 6], [3, 5, 20], [4, 5, 6]]	[1, 2]	[5]	[5, 6]
     */
    public static void main(String[] args) {
        Solution test = new Solution();
        String pathsStr = "[[1, 2, 3], [2, 3, 5], [2, 4, 2], [2, 5, 4], [3, 4, 4], [4, 5, 3], [4, 6, 1], [5, 6, 1]]";

        int[][] paths = new int[][]{
                {1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}
        };

        int[] solution = test.solution(6, paths, new int[]{1, 3}, new int[]{5});

        System.out.println("solution = " + Arrays.toString(solution));
    }

    static class Solution {

        static int V, E;
        static int[] GATES, SUMMITS;
        static ArrayList<ArrayList<Node>> graph;

        public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
            int[] answer = {};

            // setting
            V = n;
            GATES = gates;
            SUMMITS = summits;

            graph = new ArrayList<>();

            for (int i = 0; i < n + 1; i++) {
                graph.add(new ArrayList<>());
            }

            for (int[] path : paths) {
                int s = path[0];
                int e = path[1];
                int cost = path[2];

                graph.get(s).add(new Node(e, cost));
                graph.get(e).add(new Node(s, cost));
            }

            int min = Integer.MAX_VALUE;
            for (int start = 0; start < GATES.length; start++) {
                for (int end = 0; end < SUMMITS.length; end++) {

                    int upCost = dijkstra(GATES[start], SUMMITS[end]);
                    int downCout = dijkstra(SUMMITS[end], GATES[start]);

                    min = Math.min(upCost + downCout, min);
                }
            }

            System.out.println("min = " + min);


            return answer;
        }

        private int dijkstra(int start, int end) {
            // dijkstra init setting
            int[] dist = new int[V + 1];
            for (int i = 0; i < V + 1; i++) {
                dist[i] = Integer.MAX_VALUE;
            }

            // dijkstra
            PriorityQueue<Node> q = new PriorityQueue<>((o1,o2) ->
                Integer.compare(o1.cost, o2.cost)
            );

            q.offer(new Node(start, 0));
            dist[start] = 0;

            while (!q.isEmpty()) {
                Node poll = q.poll();

                if (poll.idx == end) {
                    System.out.println(dist[poll.idx]);
                    break;
                }

                if (dist[poll.idx] < poll.cost) {
                    continue;
                }

                for (int i = 0; i < graph.get(poll.idx).size(); i++) {
                    Node nextNode = graph.get(poll.idx).get(i);

                    if (dist[nextNode.idx] > poll.cost + nextNode.cost) {
                        dist[nextNode.idx] = poll.cost + nextNode.cost;
                        q.offer(new Node(nextNode.idx, dist[nextNode.idx]));
                    }
                }
            }

            return dist[end];
        }

        private static class Node {
            int idx, cost;

            Node(int idx, int cost) {
                this.idx = idx;
                this.cost = cost;
            }
        }
    }


}
