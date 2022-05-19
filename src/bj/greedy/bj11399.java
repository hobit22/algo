package bj.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class bj11399 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        ArrayList<Integer> timeList = new ArrayList<>();
        for (int i = 0; i < tc; i++) {
            timeList.add(sc.nextInt());
        }
        Collections.sort(timeList);

        int total = 0;
        for(int i = 0 ; i < timeList.size(); i++) {
            total += timeList.get(i) *(tc - i);
        }
        System.out.println(total);

    }
}
