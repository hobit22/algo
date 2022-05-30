package bj.binarySearch;

import java.util.Arrays;
import java.util.Scanner;

/*
공유기 설치
백준 2110
이분 탐색
매개 변수 탐색
 */
public class bj2110 {
    public static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 2 <= n <= 200,000
        int c = sc.nextInt(); // 2 <= c <= n

        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // 이분탐색을 하기 위해 집의 좌표 정렬
        Arrays.sort(arr);

        // 공유기 사이의 거리를 기준으로 이분탐색
        int left = 1; // 최솟값
        int right = arr[n - 1] - arr[0]; // 최댓값

        while (left < right) {
            int mid = (left + right) / 2;

            //거리가 커서 설치한 공유기가 c 보다 작으면 dist 줄임
            if (check(mid) < c) {
                right = mid;
            }
            //거리가 작아서 설치한 공유기가 c 보다 크면 dist 늘림
            else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }

    // dist 를 기준으로 몇 개 설치할 수 있는지
    private static int check(int dist) {
        int cnt = 1;
        int last = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i]; // 현재 집의 위치

            // 현재 집의 위치 - 마지막으로 공유기를 설치한 집의 위치 > 거리
            // 공유기 추가 설치, cnt++, 마지막 위치 갱신
           if (tmp - last > dist) {
                cnt++;
                last = arr[i];
            }
        }
        return cnt;
    }
}
