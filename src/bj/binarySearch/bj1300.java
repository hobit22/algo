package bj.binarySearch;

import java.util.Scanner;

/**
 * K번째 수
 * 이분 탐색
 * 매개 변수 탐색
 */
public class bj1300 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        long left = 1;
        long right = k;

        while (left < right) {
            long mid = (left + right) / 2;
            long cnt = 0;

            for (int i = 1; i <= n; i++) {
                cnt += Math.min(mid / i, n);
            }

            if (k <= cnt) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);

    }
}
