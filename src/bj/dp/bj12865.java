package bj.dp;

import java.util.Scanner;

public class bj12865 {
    static int n, k;
    static int[][] dp;  // dp 배열
    static int[] w;     // 무게
    static int[] v;     // 가치배열


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();   // 물건 개수
        k = sc.nextInt();   // 무게 제한

        dp = new int[n + 1][k + 1];

        w = new int[n + 1];
        v = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            w[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i - 1][j]; // 기본적으로 이전 아이템의 가치를 저장한다.
                if (j - w[i] >= 0) { // 무게에서 자신의 무게를 뺐을 때 남는 무게가 존재하면,
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]); // 이전 아이템에서 구한 가치와 남은 무게의 가치 + 자신의 가치 중 큰 값을 취한다.
                }
            }
        }

        System.out.println(dp[n][k]);
    }
}
