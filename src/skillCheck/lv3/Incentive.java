package skillCheck.lv3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Incentive {
    public static void main(String[] args) {

        Solution solution = new Solution();
        int solution1 = solution.solution(new int[][]{{2, 2}, {1, 4}, {3, 2}, {3, 2}});
        System.out.println("solution1 = " + solution1);

    }

    static class Solution {
        public int solution(int[][] scores) {
            int answer = 0;
            List<Person> personList = new ArrayList<>();

            for (int i = 0; i < scores.length; i++) {
                personList.add(new Person(i, scores[i]));
            }

            Collections.sort(personList);

            Person targetPerson = personList.stream().filter(person -> person.pos == 1).findFirst().get();

            int firstPersonNoIncentivePosition = checkIncentive(personList);
            System.out.println("firstPersonNoIncentivePosition = " + firstPersonNoIncentivePosition);

            for (int i = 0; i < personList.size(); i++) {
                if (i < firstPersonNoIncentivePosition) continue;
                personList.get(i).noIncentive();
            }

            for (int i = 0; i < personList.size() - 1; i++) {
                Person targetPerson1 = personList.get(i);
                Person targetPerson2 = personList.get(i + 1);

                if (targetPerson1.rank == 0) {
                    targetPerson1.rank = i + 1;
                }

                if (targetPerson1.sum == targetPerson2.sum) {
                    targetPerson2.rank = targetPerson1.rank;
                }
            }

            return targetPerson.incentive || targetPerson.rank == 0 ? targetPerson.rank : -1;
        }

        private int checkIncentive(List<Person> personList) {

            int left = 0;
            int right = personList.size();
            int mid = (left + right) / 2;

            while (left < mid) {
                int cnt = 0;

                cnt++;

                if (cnt > 10) {
                    System.out.println("cnt = " + cnt);
                }
                int newLeft;
                int newMid;
                int newRight;

                Person leftP = personList.get(left);
                Person rightP = personList.get(mid);
                System.out.println("============================");
                System.out.println("leftP.sum = " + leftP.sum);
                System.out.println("rightP.sum = " + rightP.sum);

                if (leftP.score[0] > rightP.score[0] && leftP.score[1] > rightP.score[1]) {
                    newLeft = left;
                    newRight = mid;
                } else {
                    newLeft = mid;
                    newRight = right;
                }

                newMid = (newLeft + newRight) / 2;
                left = newLeft;
                mid = newMid;
                right = newRight;
            }
            System.out.println("left = " + left);
            System.out.println("mid = " + mid);
            System.out.println("right = " + right);
            return right;
        }


        private class Person implements Comparable<Person> {
            int pos;
            int[] score;
            int sum;
            boolean incentive = true;
            int rank = 0;

            public Person(int pos, int[] score) {
                this.pos = pos + 1;
                this.score = score;
                this.sum = score[0] + score[1];
            }

            @Override
            public int compareTo(Person o) {
                return o.sum - this.sum;
            }

            public void noIncentive() {
                this.incentive = false;
            }
        }
    }

}
