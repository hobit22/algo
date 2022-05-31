package bj.binarySearch;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 나무 자르기
 */
public class bj2805 {
    public static int[] trees;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        trees = new int[n];

        for (int i = 0; i < n; i++) {
            trees[i] = sc.nextInt();
        }

        Arrays.sort(trees);

        int left = 0;
        int right = trees[n - 1];
        while (left < right) {
            int mid = (right + left) / 2;
//            System.out.println("mid = " + mid);
            long sum = 0;
            for (int i = 0; i < n; i++) {
                if(trees[i] > mid){
                    sum += trees[i] - mid;
                }
            }
//            System.out.println("sum = " + sum);
            if (sum < m) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left - 1);

    }
}
