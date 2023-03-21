package programmers.kakao.tech2022;

import java.util.Map;
import java.util.TreeMap;

public class 성격_유형_검사하기 {
    public static void main(String[] args) {
        /**
         * survey	choices	result
         * ["AN", "CF", "MJ", "RT", "NA"]	[5, 3, 2, 7, 5]	"TCMA"
         * ["TR", "RT", "TR"]	[7, 1, 3]	"RCJA"
         */
        Solution a = new Solution();
        String solutionA = a.solution(new String[]{"AN", "CF", "MJ", "RT", "NA"}, new int[]{5, 3, 2, 7, 5});
        String solutionB = a.solution(new String[]{"TR", "RT", "TR"}, new int[]{7, 1, 3});

        System.out.println("solutionA = " + solutionA);
        System.out.println("solutionB = " + solutionB);
    }

    static class Solution {
        public String solution(String[] survey, int[] choices) {
            StringBuilder answer = new StringBuilder();
            Map<Type, Integer> typeMap = new TreeMap<>();

            for (Type value : Type.values()) {
                typeMap.put(value, 0);
            }

            for (int i = 0; i < survey.length; i++) {
                String surveyQuestion = survey[i];
                int choiceValue = choices[i] - 4;

                switch (surveyQuestion.charAt(0)) {
                    case 'R':
                        typeMap.put(Type.RT, typeMap.get(Type.RT) + choiceValue);
                        break;
                    case 'T':
                        typeMap.put(Type.RT, typeMap.get(Type.RT) - choiceValue);
                        break;
                    case 'C':
                        typeMap.put(Type.CF, typeMap.get(Type.CF) + choiceValue);
                        break;
                    case 'F':
                        typeMap.put(Type.CF, typeMap.get(Type.CF) - choiceValue);
                        break;
                    case 'J':
                        typeMap.put(Type.JM, typeMap.get(Type.JM) + choiceValue);
                        break;
                    case 'M':
                        typeMap.put(Type.JM, typeMap.get(Type.JM) - choiceValue);
                        break;
                    case 'A':
                        typeMap.put(Type.AN, typeMap.get(Type.AN) + choiceValue);
                        break;
                    case 'N':
                        typeMap.put(Type.AN, typeMap.get(Type.AN) - choiceValue);
                        break;
                }
            }

            typeMap.forEach((type, point) -> {
                answer.append(point > 0 ? type.getLast() : type.getFirst());
            });

            return answer.toString();
        }

        private enum Type {
            RT("R", "T"), CF("C", "F"), JM("J", "M"), AN("A", "N");

            private final String first;
            private final String last;

            Type(String first, String last) {
                this.first = first;
                this.last = last;
            }

            public String getFirst() {
                return first;
            }

            public String getLast() {
                return last;
            }
        }
    }
}
