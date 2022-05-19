package bj.math;

import java.util.Scanner;

public class bj4948 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        while (true) {
            int a = in.nextInt();
            if (a == 0) break;
            else if( a == 1 ){
                System.out.println(1);
                continue;
            }
            // a+1 ~ 2a 까지 for 문
            int answer = 0;
            for (int i = a+1; i <= 2 * a; i++) {
                int primeCnt = 0;
                for (int j = 1; j * j <= i; j++) {
                    if (i % j == 0) {
                        primeCnt++;
                    }
                    if(primeCnt >=2 )break;
                }
                if (primeCnt == 1) {
                    answer++;
                }
            }
            System.out.println(answer);

        }

        in.close();
    }
}
