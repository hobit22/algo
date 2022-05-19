package bj.dp;

import java.util.Scanner;

public class bj1149 {
    static int[][] cost = new int[1001][3];
    static int[][] min_cost = new int[1001][3];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total_house = sc.nextInt();

        for (int i = 0; i < total_house; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            cost[i][0] = a;
            cost[i][1] = b;
            cost[i][2] = c;
        }

        min_cost[0][0] = cost[0][0];
        min_cost[0][1] = cost[0][1];
        min_cost[0][2] = cost[0][2];

        for (int i = 1; i < total_house; i++) {
            min_cost[i][0] = Math.min(cost[i][0] + min_cost[i-1][1], cost[i][0] + min_cost[i-1][2]);
            min_cost[i][1] = Math.min(cost[i][1] + min_cost[i-1][0], cost[i][1] + min_cost[i-1][2]);
            min_cost[i][2] = Math.min(cost[i][2] + min_cost[i-1][0], cost[i][2] + min_cost[i-1][1]);
        }

        int final_min = min(min_cost[total_house-1][0],min_cost[total_house-1][1],min_cost[total_house-1][2]);
        System.out.println(final_min);
    }

    private static int min(int i, int i1, int i2) {
        int x = Math.min(i, i1);
        int y = Math.min(i1, i2);
        return Math.min(x, y);
    }
}
