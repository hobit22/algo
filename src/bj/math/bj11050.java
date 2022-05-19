package bj.math;

import java.util.Scanner;

public class bj11050 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int answer = 1;

        for (int i = 0; i <k ; i++) {
            answer = answer * (n - i);
        }
        for (int i = 1; i <= k; i++) {
            answer = answer / i;
        }
        System.out.println(answer);
    }
}
