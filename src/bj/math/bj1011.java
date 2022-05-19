package bj.math;

import java.util.Scanner;

public class bj1011 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int tc = in.nextInt();
        for(int i = 0; i < tc; i++){
            int start = in.nextInt();
            int end = in.nextInt();

            int length = end - start;

            // 1  1  1
            // 2  2  1+1
            // 3  3  1+1+1
            // 4  3  1+2+1
            // 5  4  1+2+1+1
            // 6  4  1+2+2+1// 1~2까지 합 * 2
            // 7  5  1+2+2+1+1
            // 8  5  1+2+2+2+1
            // 9  5  1+2+3+2+1 // 3 + 3
            // 10 6  1+2+3+2+1+1
            // 11 6  1+2+3+2+2+1
            // 12 6  1+2+3+3+2+1 // 1~3까지 합 * 3
            // 13 7  1+2+3+3+2+1+1
            // 14 7  1 2 3 3 2 2 1
            // 15 7  1 2 3 3 3 2 1
            // 16 7  1 2 3 4 3 2 1 // 4 + 6
            // 17 8  1 2 3 4 3 2 1 1
            // 18 8  1 2 3 4 3 2 2 1
            // 19 8  1 2 3 4 3 3 2 1
            // 20 8  1 2 3 4 4 3 2 1  // 10
            // 21 9  1 2 3 4 4 3 2 1 1
            // 22 9  1 2 3 4 4 3 2 2 1
            // 23 9  1 2 3 4 4 3 3 2 1
            // 24 9  1 2 3 4 4 4 3 2 1
            // 25 9  1 2 3 4 5 4 3 2 1 // 5 + 10
            // 26 10 1 2 3 4 5 4 3 2 1 1

            int rootNum = (int) Math.sqrt(length);

            if(rootNum == Math.sqrt(length)) {
                System.out.println(rootNum * 2 - 1);
            }
            else if(length <= rootNum * rootNum + rootNum) {
                System.out.println(rootNum * 2);
            }
            else {
                System.out.println(rootNum * 2 + 1);
            }
        }

        in.close();
    }
}