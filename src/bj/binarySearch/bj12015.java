package bj.binarySearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
백준 12015
가장 긴 증가하는 부분 수열 2
이분 탐색
 */
public class bj12015 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1 <= n <= 1_000_000
        int n = sc.nextInt();

        // n이 크기 때문에 sort로는 힘들어 보인다.
        int[] arr = new int[n];
        List<Integer> list = new ArrayList<>();
        list.add(0);

        for (int i = 0; i < n; i++) {
            // x 를 입력받고 적당한 위치를 찾아 넣는다.
            int x = arr[i] = sc.nextInt();

            // 이분탐색
            int left = 0;
            int right = list.size() - 1;
            if (x > list.get(right)) {
                list.add(x);
            } else {
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (list.get(mid) >= x) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                list.set(right, x);
            }
        }

        System.out.println(list.size() - 1);

    }
}
