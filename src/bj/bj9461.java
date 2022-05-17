package bj;

import java.util.Scanner;

public class bj9461 {
    static long[] p = new long[101];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        p[0] = 1;
        p[1] = 1;
        p[2] = 1;
        p[3] = 2;
        p[4] = 2;
        while(tc-- > 0){
            int n = sc.nextInt();

            System.out.println(seq(n-1));
        }
    }

    public static long seq(int n){
        if(p[n] != 0){
            return p[n];
        }

        p[n] = seq(n-1) + seq(n-5);
        return p[n];
    }
}
