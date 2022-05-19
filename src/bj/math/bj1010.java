package bj.math;

import java.util.Scanner;

public class bj1010 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        while (tc-- > 0) {
            int r = sc.nextInt();
            int n = sc.nextInt();

            //nCr 구하기

            int answer = 1;
            if(r!=n){
                for(int i = 1; i<=r;i++){
                    answer = answer *(n-i+1);
                    answer = answer/i;
                }
            }

            System.out.println(answer);
        }
    }
}
