package bj.bruteForce;

import java.util.Scanner;

/**
 * IQ Test
 * 수학
 * 구현
 * 브루트포스 알고리즘
 * 많은 조건 분기
 */
public class bj1111 {
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // arr[i] * a + b = arr[i+1]
        // arr[i+1] * a + b = arr[i+2]
        // a(arr[i] - arr[i+1]) = arr[i+1] - arr[i+2]
        // a = (arr[i+1] - arr[i+2]) / (arr[i] - arr[i+1])

        if (n == 1 || (n == 2 && arr[0] != arr[1])) {
            System.out.println("A");
        } else if (n == 2) {
            System.out.println(arr[0]);
        } else {
            int a, b;
            if (arr[1] == arr[0]) {
                a = 1;
                b = 0;
            } else {
                a = (arr[1] - arr[2]) / (arr[0] - arr[1]);
                b = arr[1] - (arr[0] * a);
            }

            for (int i = 1; i < n; i++) {
                if (arr[i] != arr[i - 1] * a + b) {
                    System.out.println("B");
                    return;
                }
            }
            System.out.println(arr[n - 1] * a + b);
        }


    }
}
