package bj.dp;

import java.util.Scanner;

public class bj11053 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        int[] seq = new int[1001];
        int[] dp = new int[1001];
        for (int i = 0; i < tc; i++) {
            int tmp = sc.nextInt();
            seq[i] = tmp;
        }

        for (int i = 0; i < tc; i++) {
            // 항상 시작은 1
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                // j 는 항상 i 보다 작다
                // 수열에 담긴 값 비교 && dp 에 담긴 값 비교
                // j번째 값이 i보다 작고 && 연속된 수열 값이 작을 때 최신화
                if (seq[j] < seq[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < tc; i++) {
            max = Math.max(dp[i], max);
        }
        System.out.println(max);

    }
}
