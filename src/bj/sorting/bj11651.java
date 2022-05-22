package bj.sorting;

import java.util.Arrays;
import java.util.Scanner;

public class bj11651 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] pos = new int[n][2];

        for (int i = 0; i < n; i++) {

            int x = sc.nextInt();
            int y = sc.nextInt();
            pos[i][0] = x;
            pos[i][1] = y;

        }

        Arrays.sort(pos, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }else{
                return o1[1] - o2[1];
            }
        });

        for (int i = 0; i < n; i++) {
            System.out.println(pos[i][0] + " " + pos[i][1]);
        }
    }
}
