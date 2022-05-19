package bj.math;

import java.util.Scanner;

public class bj1934 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        while(tc--> 0){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int max_divisor = gcd(a,b);
            int min_multiple = a * b / max_divisor;
            System.out.println(min_multiple);
        }
    }

    public static int gcd(int a, int b){
        int x = a % b;
        while(x > 0){
            a = b;
            b = x;
            x = a%b;
        }
        return b;
    }
}
