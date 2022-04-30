package bj;

import java.time.LocalTime;
import java.util.Scanner;

public class bj1929 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
//        LocalTime start = LocalTime.now();
//        System.out.println("start = " + start);
        for (int i = n; i <= m; i++) {
            int primeCnt = 0;
            if (i == 1) continue;
            for (int j = 1; j * j <= i; j++) {
                if (i % j == 0) primeCnt++;
                if (primeCnt >= 2) break;
            }
            if (primeCnt == 1) System.out.println(i);
        }
        LocalTime end = LocalTime.now();
        System.out.println("end = " + end);

    }
}
