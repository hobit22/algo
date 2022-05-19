package bj.bruteForce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class bj2798 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<Integer> card = new ArrayList<>();

        while (n-- > 0) {
            card.add(sc.nextInt());
        }

        int max_sum = 0;
        int size = card.size();

        for (int i = 0; i < size - 2; i++) {
            for (int j = i + 1; j < size - 1; j++) {
                for (int k = j + 1; k < size; k++) {
                    int sum = card.get(i) + card.get(j) + card.get(k);
                    if (sum <= m) {
                        max_sum = (max_sum > sum) ? max_sum : sum;
                    }
                    if (max_sum == m) {
                        System.out.println(max_sum);
                        return;
                    }
                }
            }
        }
        System.out.println(max_sum);

    }
}
