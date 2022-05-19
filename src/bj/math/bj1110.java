package bj.math;

import java.util.Scanner;

public class bj1110 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int cnt = 0;

        int answer = cycle(n, n, cnt);
        System.out.println("answer = " + answer);
    }

    public static int cycle(int goal, int n, int cnt) {
        int x = n / 10;
        int y = n % 10;
        int new_n = y * 10 + (y + x) % 10;

        cnt++;
        if (new_n == goal) return cnt;
        else return cycle(goal, new_n, cnt);
    }
}
