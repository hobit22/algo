package skillCheck.lv3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Incentive {
    public static void main(String[] args) {

        Solution solution = new Solution();
        int solution1 = solution.solution(new int[][]{{4, 1}, {2, 4}, {3, 5}});
        System.out.println("solution1 = " + solution1);

    }

    static class Solution {
        public int solution(int[][] scores) {
            int answer = 0;
            List<Person> personList = new ArrayList<>();

            for (int i = 0; i < scores.length; i++) {
                personList.add(new Person(i, scores[i]));
            }

            Person targetPerson = personList.get(0);

            Collections.sort(personList);

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
                } else {
                    targetPerson2.rank = i + 2;
                }
            }

            return targetPerson.incentive ? targetPerson.rank : -1;
        }

        private int checkIncentive(List<Person> personList) {

            int start = 0;
            int end = personList.size() - 1;
            int mid;

            while (start <= end) {
                mid = (start + end) / 2;

                Person leftP = personList.get(start);
                Person rightP = personList.get(mid);
                System.out.println("============================");
                System.out.println("leftP.sum = " + leftP.sum);
                System.out.println("rightP.sum = " + rightP.sum);

                if (leftP.score[0] > rightP.score[0] && leftP.score[1] > rightP.score[1]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            System.out.println("start = " + start);
            System.out.println("end = " + end);
            return end + 1;
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
                if (o.sum == this.sum) {
                    return o.score[0] - this.score[0];
                } else {
                    return o.sum - this.sum;
                }
            }

            public void noIncentive() {
                this.incentive = false;
            }
        }
    }

}
