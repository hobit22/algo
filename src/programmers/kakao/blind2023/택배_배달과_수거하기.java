package programmers.kakao.blind2023;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * cap	n	deliveries	pickups	result
 * 4	5	[1, 0, 3, 1, 2]	[0, 3, 0, 4, 0]	16
 * 2	7	[1, 0, 2, 0, 1, 0, 2]	[0, 2, 0, 1, 0, 2, 0]	30
 */
public class 택배_배달과_수거하기 {

    public static void main(String[] args) {

        Solution solutionA = new Solution();
        Solution solutionB = new Solution();
        long resultA = solutionA.solution(4, 5, new int[]{1, 0, 3, 1, 2}, new int[]{0, 3, 0, 4, 0});
        long resultB = solutionB.solution(2, 7, new int[]{1, 0, 2, 0, 1, 0, 2}, new int[]{0, 2, 0, 1, 0, 2, 0});

        System.out.println("resultA = " + resultA);
        System.out.println("resultB = " + resultB);
    }

    public static class Solution {
        public long solution(int cap, int n, int[] deliveries, int[] pickups) {
            long answer = 0;
            if (isAllZero(deliveries) && isAllZero(pickups)) return 0;

            int deliveryLastIndex = getLastIndex(deliveries);
            int pickupLastIndex = getLastIndex(pickups);
            answer += 2L * (Math.max(deliveryLastIndex, pickupLastIndex) + 1);

            while (deliveryLastIndex >= 0 || pickupLastIndex >= 0) {
                deliveryLastIndex = startCar(deliveries, cap, deliveryLastIndex);
                pickupLastIndex = startCar(pickups, cap, pickupLastIndex);
                answer += 2L * (Math.max(deliveryLastIndex, pickupLastIndex) + 1);
            }

            return answer;
        }

        private boolean isAllZero(int[] arr) {
            return Arrays.stream(arr).allMatch(num -> num == 0);
        }

        private int startCar(int[] arr, int cap, int deliveryLastIndex) {

            for (int i = deliveryLastIndex; i >= 0; i--) {
                if (cap >= arr[i]) {
                    cap -= arr[i];
                    arr[i] = 0;
                } else {
                    arr[i] -= cap;
                    return i;
                }
            }
            return -1;
        }

        private int getLastIndex(int[] arr) {

            return IntStream.rangeClosed(0, arr.length - 1)
                    .map(i -> arr.length - i - 1)
                    .filter(idx -> arr[idx] != 0)
                    .findFirst()
                    .orElse(-1);
        }
    }
}
