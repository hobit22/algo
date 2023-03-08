package programmers.level2;

public class WayToSchool {
    public static void main(String[] args) {
        int[][] x = new int[2][2];
//        x = {{2, 2}};
//        System.out.println(solution(4, 3, new int[2][2]));
    }

    public static int solution(int m, int n, int[][] puddles) {
        int[][] root = new int[n + 1][m + 1];

        for (int i = 0; i < puddles.length; i++) {
            int x = puddles[i][0];
            int y = puddles[i][1];
            root[y][x] = -1;
        }
        // for (int i = 1; i <= n; i++) {
        //     for (int j = 1 ;j <= m; j++) {
        //         System.out.print(root[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        root[1][1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (root[i][j] == -1) {
                    continue;
                }
                if (root[i][j - 1] >= 0 && root[i][j] >= 0) {
                    root[i][j] += root[i][j - 1] % 1000000007;
                }
                if (root[i - 1][j] >= 0 && root[i][j] >= 0) {
                    root[i][j] += root[i - 1][j] % 1000000007;
                }

                // System.out.printf("root[%d][%d] = %d \n", i, j, root[i][j]);
            }
        }

        // for (int i = 1; i <= n; i++) {
        //     for (int j = 1 ;j <= m; j++) {
        //         System.out.print(root[i][j]+" ");
        //     }
        //     System.out.println();
        // }

        return root[n][m] % 1000000007;
    }
}
