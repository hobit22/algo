package programmers.kakao.tech2022;

import java.util.*;

public class 행렬과_연산 {
    public static void main(String[] args) {

        /**
         * rc	operations	result
         * [[1, 2, 3], [4, 5, 6], [7, 8, 9]]	["Rotate", "ShiftRow"]	[[8, 9, 6], [4, 1, 2], [7, 5, 3]]
         * [[8, 6, 3], [3, 3, 7], [8, 4, 9]]	["Rotate", "ShiftRow", "ShiftRow"]	[[8, 3, 3], [4, 9, 7], [3, 8, 6]]
         * [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]]	["ShiftRow", "Rotate", "ShiftRow", "Rotate"]	[[1, 6, 7 ,8], [5, 9, 10, 4], [2, 3, 12, 11]]
         */

        Solution solution = new Solution();

        int[][] solution1 = solution.solution(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, new String[]{"Rotate", "ShiftRow"});
        int[][] solution2 = solution.solution(new int[][]{{8, 6, 3}, {3, 3, 6}, {8, 4, 9}}, new String[]{"Rotate", "ShiftRow", "ShiftRow"});
        int[][] solution3 = solution.solution(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}, new String[]{"ShiftRow", "Rotate", "ShiftRow", "Rotate"});


        System.out.println("solution1 = " + Arrays.deepToString(solution1));
        System.out.println("solution2 = " + Arrays.deepToString(solution2));
        System.out.println("solution3 = " + Arrays.deepToString(solution3));
    }

    static class Solution {
        int r, c;
        ArrayDeque<Integer> col1, col2;
        LinkedList<ArrayDeque<Integer>> rows;


        public int[][] solution(int[][] rc, String[] operations) {
            init(rc);

            for (String operation : operations) {
                switch (operation) {
                    case "ShiftRow":
                        shiftRow();
                        break;
                    case "Rotate":
                        rotate();
                        break;
                }
            }

            int[][] ans = new int[r][c];
            for (int rowIdx = 0; rowIdx < r; rowIdx++) {
                ans[rowIdx][0] = col1.pollFirst();
                ans[rowIdx][c - 1] = col2.pollFirst();
            }

            int rowIdx=0;
            for (ArrayDeque<Integer> row : rows) {
                for (int colIdx = 1; colIdx < c - 1; colIdx++) {
                    ans[rowIdx][colIdx] = row.pollFirst();
                }
                rowIdx++;
            }

            return ans;
        }

        private void init(int[][] rc) {
            r = rc.length;
            c = rc[0].length;

            col1 = new ArrayDeque<>();
            col2 = new ArrayDeque<>();

            for (int i = 0; i < r; i++) {
                col1.add(rc[i][0]);
                col2.add(rc[i][c - 1]);
            }

            rows = new LinkedList<>();

            for (int i = 0; i < r; i++) {
                ArrayDeque<Integer> tmp = new ArrayDeque<>();
                for (int j = 1; j < c - 1; j++) {
                    tmp.add(rc[i][j]);
                }
                rows.add(tmp);
            }

        }

        private void shiftRow() {
            rows.addFirst(rows.pollLast());
            col1.addFirst(col1.pollLast());
            col2.addFirst(col2.pollLast());
        }
        private void rotate() {
            if (c == 2) {
                col2.addFirst(col1.pollFirst());
                col1.addLast(col2.pollLast());
                return;
            }
            rows.peekFirst().addFirst(col1.pollFirst());
            col2.addFirst(rows.peekFirst().pollLast());
            rows.peekLast().addLast(col2.pollLast());
            col1.addLast(rows.peekLast().pollFirst());
        }

    }
}
