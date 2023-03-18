package programmers.kakao.tech2022;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class 코딩_테스트_공부 {
    public static void main(String[] args) {
        /**
         * alp	cop	problems	result
         * 10	10	[[10,15,2,1,2],[20,20,3,3,4]]	15
         * 0	0	[[0,0,2,1,2],[4,5,3,1,2],[4,11,4,0,2],[10,4,0,4,2]]	13
         */

        Solution a = new Solution();
        int result1 = a.solution(10, 10, new int[][]{{10, 15, 2, 1, 2}, {20, 20, 3, 3, 4}});
        int result2 = a.solution(0, 0, new int[][]{{0, 0, 2, 1, 2}, {4, 5, 3, 1, 2}, {4, 11, 4, 0, 2}, {10, 4, 0, 4, 2}});

        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);
    }

    static class Solution {
        static int myAlp, myCop;
        static boolean[] solved;

        public int solution(int alp, int cop, int[][] problems) {
            int answer = 0;
            myAlp = alp;
            myCop = cop;
            solved = new boolean[problems.length + 1];

            // 문제 세팅
            List<Problem> problemList = new ArrayList<>();
            for (int[] problem : problems) {
                problemList.add(new Problem(problem[0], problem[1], problem[2], problem[3]));
            }

            problemList.stream().sorted();

            System.out.println("problemList = " + problemList);


            while (problemList.size() > 0) {
                // 현재 풀 수 있는 문제
                List<Problem> canSolve = new ArrayList<>();
                canSolve = problemList.stream()
                        .filter(problem -> problem.alp_req < myAlp && problem.cop_req < myCop)
                        .collect(Collectors.toList());

                if (canSolve.size() == 0) {
                    Problem problem = problemList.get(0);

                    answer += problem.alp_req - myAlp + problem.cop_req - myCop;
                    myAlp = problem.alp_req;
                    myCop = problem.cop_req;
                }
            }


            return answer;
        }

        private class Problem implements Comparator<Problem> {
            int alp_req;
            int cop_req;
            int alp_rwd;
            int cop_rwd;

            public Problem(int alp_req, int cop_req, int alp_rwd, int cop_rwd) {
                this.alp_req = alp_req;
                this.cop_req = cop_req;
                this.alp_rwd = alp_rwd;
                this.cop_rwd = cop_rwd;
            }

            @Override
            public int compare(Problem o1, Problem o2) {
                if (o1.alp_req >= o2.alp_req) {
                    if (o1.cop_req >= o2.cop_rwd) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
                return -1;

            }
        }
    }
}
