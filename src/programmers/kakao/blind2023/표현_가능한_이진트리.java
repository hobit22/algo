package programmers.kakao.blind2023;

import java.util.Arrays;

public class 표현_가능한_이진트리 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] answerA = solution.solution(new long[]{7, 42, 5});
        int[] answerB = solution.solution(new long[]{63, 111, 95});

        System.out.println("answerA = " + Arrays.toString(answerA));
        System.out.println("answerB = " + Arrays.toString(answerB));
    }
    static class Solution {
        public int[] solution(long[] numbers) {
            int[] answer = new int[numbers.length];

            for (int i = 0; i < numbers.length; i++) {
                long targetNumber = numbers[i];

                String binaryTargetNumber = "";
                while (targetNumber != 0) {
                    if (targetNumber % 2 == 1) {
                        binaryTargetNumber = "1" + binaryTargetNumber;
                    } else {
                        binaryTargetNumber = "0" + binaryTargetNumber;
                    }
                    targetNumber /= 2;
                }

                int n = 1;
                while (true) {
                    if (binaryTargetNumber.length() <= Math.pow(2, n) - 1) {
                        break;
                    }
                    n++;
                }

                int add = (int) (Math.pow(2, n) - 1) - binaryTargetNumber.length();
                for (int j = 0; j < add; j++) {
                    binaryTargetNumber = "0" + binaryTargetNumber;
                }

                answer[i] = isBinaryTree(binaryTargetNumber) ? 1 : 0;
            }

            return answer;
        }

        private boolean isBinaryTree(String binaryTargetNumber) {
            if (binaryTargetNumber.length() == 1) {
                return true;
            }

            int mid = binaryTargetNumber.length() / 2;
            int root = binaryTargetNumber.charAt(mid);

            String left = binaryTargetNumber.substring(0, mid);
            String right = binaryTargetNumber.substring(mid + 1);

            int leftRoot = left.charAt(left.length() / 2);
            int rightRoot = right.charAt(right.length() / 2);

            if (isBinaryTree(left) && isBinaryTree(right)) {
                if (root == '1') {
                    return true;
                }
                if (leftRoot == '0' && rightRoot == '0') {
                    return true;
                }
            }
            return false;
        }
    }
}
