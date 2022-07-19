package bj.dp;

import java.util.Scanner;

public class bj2293 {
    static int N, K;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        arr = new int[N + 1];
        dp = new int[K + 1];


        for (int i = 1; i < N + 1; i++) {
            arr[i] = sc.nextInt();
        }

        dp[0] = 1;
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < K + 1; j++) {
                if (j >= arr[i]) {
                    dp[j] += dp[j - arr[i]];
                }
            }
        }

        System.out.println(dp[K]);

    }
}
