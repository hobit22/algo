package programmers.kakao.tech2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class 등산코스_정하기 {
    /**
     * n	paths	gates	summits	result
     * 6	[[1, 2, 3], [2, 3, 5], [2, 4, 2], [2, 5, 4], [3, 4, 4], [4, 5, 3], [4, 6, 1], [5, 6, 1]]	[1, 3]	[5]	[5, 3]
     * 7	[[1, 4, 4], [1, 6, 1], [1, 7, 3], [2, 5, 2], [3, 7, 4], [5, 6, 6]]	[1]	[2, 3, 4]	[3, 4]
     * 7	[[1, 2, 5], [1, 4, 1], [2, 3, 1], [2, 6, 7], [4, 5, 1], [5, 6, 1], [6, 7, 1]]	[3, 7]	[1, 5]	[5, 1]
     * 5	[[1, 3, 10], [1, 4, 20], [2, 3, 4], [2, 4, 6], [3, 5, 20], [4, 5, 6]]	[1, 2]	[5]	[5, 6]
     * n = 7
     * paths = [[1, 4, 4], [1, 6, 1], [1, 7, 3], [2, 5, 2], [3, 7, 4], [5, 6, 6]]
     * gates = [2]
     * summits = [3, 4]
     */
    public static void main(String[] args) {
        Solution test = new Solution();

        int[][] paths = new int[][]{
                {1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}
        };

        int[] solutionA = test.solution(6, paths, new int[]{1, 3}, new int[]{5});
        int[] solutionB = test.solution(4, new int[][]{{1, 3, 1}, {1, 4, 1}, {4, 2, 1}}, new int[]{1}, new int[]{2, 3, 4});
        int[] solutionC = test.solution(7, new int[][]{{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}}, new int[]{2}, new int[]{3, 4});

        System.out.println("solutionA = " + Arrays.toString(solutionA));
        System.out.println("solutionB = " + Arrays.toString(solutionB));
    }

    static class Solution {

        static int V, E;
        static int[] GATES, SUMMITS;
        static ArrayList<ArrayList<Node>> graph;

        public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
            int[] answer = new int[2];

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

                // 출입구일 경우 다른 곳으로만 갈 수 있는 단방향
                // 산봉우리일 경우 특정 한 곳에서 산봉우리로 가는 단방향
                if (isGate(s) || isSummit(e)) {
                    graph.get(s).add(new Node(e, cost));
                } else if (isGate(e) || isSummit(s)) {
                    graph.get(e).add(new Node(s, cost));
                } else {
                    // 일반 등산로일 경우 양방향
                    graph.get(s).add(new Node(e, cost));
                    graph.get(e).add(new Node(s, cost));
                }
            }

            return dijkstra();
        }

        private int[] dijkstra() {
            Queue<Node> q = new LinkedList<>();
            int[] intensityArr = new int[V + 1];

            // dijkstra init setting
            for (int i = 0; i < V + 1; i++) {
                intensityArr[i] = Integer.MAX_VALUE;
            }

            for (int gate : GATES) {
                q.add(new Node(gate, 0));
                intensityArr[gate] = 0;
            }

            while (!q.isEmpty()) {
                Node poll = q.poll();
                if (isSummit(poll.idx)) continue;
                if (poll.cost > intensityArr[poll.idx]) continue;

                for (int i = 0; i < graph.get(poll.idx).size(); i++) {
                    Node nextNode = graph.get(poll.idx).get(i);

                    int intensity = Math.max(intensityArr[poll.idx], nextNode.cost);

                    if (intensityArr[nextNode.idx] > intensity) {
                        intensityArr[nextNode.idx] = intensity;
                        q.offer(new Node(nextNode.idx, intensity));
                    }
                }
            }

            int ansSubmit = Integer.MAX_VALUE;
            int ansIntensity = Integer.MAX_VALUE;
            for (int summit : SUMMITS) {
                if (intensityArr[summit] < ansIntensity) {
                    ansSubmit = summit;

                    ansIntensity = intensityArr[summit];
                } else if(intensityArr[summit] == ansIntensity) {
                    ansSubmit = Math.min(ansSubmit, summit);

                }
            }

            return new int[]{ansSubmit, ansIntensity};
        }

        private boolean isSummit(int e) {
            for (int submit : SUMMITS) {
                if (e == submit) return true;
            }
            return false;
        }

        private boolean isGate(int s) {
            for (int gate : GATES) {
                if (s == gate) return true;
            }
            return false;
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
