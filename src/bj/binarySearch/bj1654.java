package bj.binarySearch;

import java.util.Scanner;

/**
 * 랜선 자르기
 * 이분 탐색
 * 매개 변수 탐색
 */
public class bj1654 {
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt();
        int n = sc.nextInt();
        arr = new int[k];

        long max = -1;

        for (int i = 0; i < k; i++) {
            arr[i] = sc.nextInt();
            max = Math.max(max, arr[i]);
        }

        long left = 0L;
        long right = max + 1;

        while (left < right) {
            long mid = (left + right) / 2;

            int sum = 0;
            for (int i = 0; i < k; i++) {
                sum += arr[i] / mid;
            }

            if (sum < n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left - 1);

    }
}
