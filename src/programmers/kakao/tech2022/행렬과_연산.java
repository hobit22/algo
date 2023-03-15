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
        static int[] dr = new int[]{0, 1, 0, -1};
        static int[] dc = new int[]{1, 0, -1, 0};
        int MAX_ROW = 0;
        int MAX_COL = 0;

        public int[][] solution(int[][] rc, String[] operations) {
            MAX_ROW = rc.length;
            MAX_COL = rc[0].length;

            int[][] answer = {};
            int[][] map = rc;

            for (String operation : operations) {
                System.out.println("operation = " + operation);
                printMap(map);

                System.out.println("===============================");
                if (operation.equals("ShiftRow")) {
                    map = shiftRow(map);
                } else if (operation.equals("Rotate")) {
                    map = rotate(map);
                }
                printMap(map);

            }


            return map;
        }

        private int[][] rotate(int[][] map) {
            int[][] newMap = new int[map.length][map[0].length];

            for (int i = 0; i < newMap.length; i++) {
                for (int j = 0; j < newMap[i].length; j++) {
                    newMap[i][j] = map[i][j];
                }
            }

            int r = 0;
            int c = 0;

//            for (int i = 0; i < 4; i++) {
//
//                int targetR = r + dr[i];
//                int targetC = c + dc[i];
//                while (targetR < map.length && targetC < map[0].length && targetR >= 0 && targetC >= 0) {
//                    newMap[targetR][targetC] = map[r][c];
//
//                    r = targetR;
//                    c = targetC;
//
//                    targetR = r + dr[i];
//                    targetC = c + dc[i];
//                }
//            }

            int[] firstR = map[0];
            int[] lastR = map[MAX_ROW - 1];
            int[] firstC = new int[MAX_ROW];
            int[] lastC = new int[MAX_ROW];

            for (int i = 0; i < MAX_ROW; i++) {
                firstC[i] = map[i][0];
                lastC[i] = map[i][MAX_COL - 1];
            }

            for (int i = 0; i < firstR.length - 1; i++) { // 첫행 끝원소 제외
                newMap[0][i + 1] = firstR[i];
            }

            for (int i = 0; i < lastC.length - 1; i++) { // 끝열 끝원소 제외
                newMap[i+1][MAX_COL - 1] = lastC[i];
            }

            for (int i = 1; i <= lastR.length - 1; i++) { // 끝행 첫원소 제외
                newMap[MAX_ROW - 1][i - 1] = lastR[i];
            }

            for (int i = 1; i <= firstC.length - 1; i++) { // 첫열 첫원소 제외
                newMap[i - 1][0] = firstC[i];
            }


            return newMap;
        }

        private void printMap(int[][] rc) {
            for (int[] ints : rc) {
                for (int anInt : ints) {
                    System.out.print(anInt + " ");
                }
                System.out.println();
            }
        }

        private int[][] shiftRow(int[][] map) {
            // find last row
            int lastRowIdx = map.length - 1;
            int[] lastRow = map[lastRowIdx];

            for (int i = lastRowIdx - 1; i >= 0; i--) {
                int[] targetRow = map[i];
                map[i + 1] = targetRow;
            }

            map[0] = lastRow;
            return map;
        }


    }

}
