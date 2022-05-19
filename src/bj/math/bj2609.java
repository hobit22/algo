package bj.math;

import java.util.Scanner;

public class bj2609 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int x = gcd(n, m);
        int y = n * m / x;
        System.out.println(x);
        System.out.println(y);
    }

    public static int gcd(int a, int b) {
        int x = a % b;
        while (x > 0) {
            a = b;
            b = x;
            x = a % b;
        }
        return b;
    }
}
