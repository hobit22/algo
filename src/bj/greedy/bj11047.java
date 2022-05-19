package bj.greedy;

import java.util.Scanner;

public class bj11047 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        int k = sc.nextInt();
        int[] coins = new int[tc];
        for (int i = 0; i < tc; i++) {
            coins[i] = sc.nextInt();
        }

        int total = 0;
        for (int j = tc - 1; j >= 0; j--) {
            if (k == 0) break;
            if (k / coins[j] > 0) {
                int division = k / coins[j];
                total += division;
                k = k - division * coins[j];
            }
        }
        System.out.println(total);
    }
}
