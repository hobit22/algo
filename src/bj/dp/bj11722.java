package bj.dp;

import java.util.Scanner;

public class bj11722 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] seq = new int[1001];
        int[] dp = new int[1001];

        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            seq[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (seq[i] < seq[j] && dp[i] <= dp[j]) {
                    dp[i] = dp[j] + 1;
                } else if (seq[i] == seq[j]) {
                    dp[i] = dp[j];
                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(dp[i], max);
        }
        System.out.println(max);
    }
}
