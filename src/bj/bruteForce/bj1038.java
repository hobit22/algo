package bj.bruteForce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class bj1038 {
    /**
     * 0 1 2 3 4 5 6 7 8 9  10 20 21 30 31 32 40 41 42
     * 0 1 2 3 4 5 6 7 8 9  10 11 12 13 14 15 16 17 18 19 20
     */

    static List<Long> list;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        list = new ArrayList<>();

        if (N <= 10) {
            System.out.println(N);
        } else if (N > 1022) {
            System.out.println("-1");
        } else {
            for (int i = 0; i < 10; i++) {
                bp(i, 1);
            }
            Collections.sort(list);

            System.out.println(list.get(N));
        }

    }

    private static void bp(long num, int idx) {
        if (idx > 10) return;

        list.add(num);

        for (int i = 0; i < num % 10; i++) {
            bp((num * 10) + i, idx + 1);
        }
    }
}
