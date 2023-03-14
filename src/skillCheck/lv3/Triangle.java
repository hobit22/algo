package skillCheck.lv3;

public class Triangle {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}});
    }

    static class Solution {
        public int solution(int[][] triangle) {
            int answer = 0;

            int lastRow = triangle.length - 1;
            int lastCol = triangle[lastRow].length - 1;

            while (lastRow > 0 && lastCol > 0) {

                for (int col = lastCol; col > 0; col--) {
                    int targetRight = triangle[lastRow][col];
                    int targetLeft = triangle[lastRow][col - 1];

                    int target = triangle[lastRow - 1][col - 1];
                    triangle[lastRow - 1][col - 1] = Math.max(targetLeft, targetRight) + target;
                }

                for (int i = 0; i < triangle.length; i++) {
                    for (int j = 0; j < triangle[i].length; j++) {
                        System.out.printf("%d ", triangle[i][j]);
                    }
                    System.out.println();
                }
                System.out.println("lastRow = " + lastRow);
                System.out.println("lastCol = " + lastCol);

                lastRow--;
                lastCol--;

            }

            answer = triangle[0][0];

            return answer;
        }


    }

}
