package bj;

import java.util.Scanner;

public class bj2579 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        int[] numArr = new int[301];
        int[] sumArr = new int[301];
        for (int i = 0; i < tc; i++) {
            int tmp = sc.nextInt();
            numArr[i] = tmp;
        }

        // 마지막 계단 밟아야함
        // x, x+1, x+2, x+3
        // dp[3] = dp[1] + arr[3], dp[0] + arr[2] + arr[3]

        sumArr[0] = numArr[0];
        sumArr[1] = Math.max(numArr[0] + numArr[1], numArr[1]);
        sumArr[2] = Math.max(numArr[0] + numArr[2], numArr[1] + numArr[2]);

        for (int i = 3; i < tc; i++) {
            sumArr[i] = Math.max(sumArr[i - 2] + numArr[i], sumArr[i - 3] + numArr[i - 1] + numArr[i]);
        }

        System.out.println(sumArr[tc - 1]);
    }
}
