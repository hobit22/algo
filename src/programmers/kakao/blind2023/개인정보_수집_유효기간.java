package programmers.kakao.blind2023;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

/**
 * today 	terms 	privacies 	result
 * "2022.05.19" 	["A 6", "B 12", "C 3"] 	["2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"] 	[1, 3]
 * "2020.01.01" 	["Z 3", "D 5"] 	["2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"] 	[1, 4, 5]
 */

public class 개인정보_수집_유효기간 {

    static Map<String, Integer> termMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        InputDto inputA = new InputDto("2022.05.19", new String[]{"A 6", "B 12", "C 3"}, new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"});
        InputDto inputB = new InputDto("2020.01.01", new String[]{"Z 3", "D 5"}, new String[]{"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"});

        List<Integer> solutionA = solution(inputA.today, inputA.terms, inputA.privacies);
        List<Integer> solutionB = solution(inputB.today, inputB.terms, inputB.privacies);

        System.out.println(solutionA);
        System.out.println(solutionB);
    }


    public static List<Integer> solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();

        Arrays.stream(terms).forEach(termStr -> {
            Term term = new Term(termStr);
            termMap.put(term.name, term.dueMonth);
        });

//        Arrays.stream(terms).forEach(term -> termList.add(new Term(term)));
        List<Privacy> privacyList = new ArrayList<>();

        Arrays.stream(privacies).forEach(privacy -> privacyList.add(new Privacy(privacy)));

        IntStream.range(0, privacyList.size())
                .filter(i -> checkPrivacyDestroy(privacyList.get(i), today))
                .forEach(i -> answer.add(i + 1));

        return answer;
    }

    private static boolean checkPrivacyDestroy(Privacy privacy, String todayStr) {
        String termName = privacy.termName;

        CustomDate today = new CustomDate(todayStr);
        Integer dueMonth = termMap.get(termName);

        // plus date
        CustomDate expireDate = privacy.date.addMonth(dueMonth);

        // check date
        int year = today.year - expireDate.year;
        int month = today.month - expireDate.month;
        int day = today.day - expireDate.day;

        int todalDay = (year * 12 * 28) + (month * 28) + day;

        return todalDay > 0;
    }

    public static class CustomDate {
        Integer year;
        Integer month;
        Integer day;

        public CustomDate(String str) {
            String[] split = str.split("\\.");
            this.year = Integer.valueOf(split[0]);
            this.month = Integer.valueOf(split[1]);
            this.day = Integer.valueOf(split[2]);
        }

        public CustomDate addMonth(Integer plus) {
            this.day--;
            if (this.day < 1) {
                this.day = 28;
                this.month--;
            }
            this.month = this.month + plus;
            if (this.month > 12) {
                this.year++;
                this.month = this.month - 12;
            }

            return this;
        }
    }

    private static class Term {
        String name;
        Integer dueMonth;

        public Term(String term) {
            String[] s = term.split(" ");
            this.name = s[0];
            this.dueMonth = Integer.valueOf(s[1]);
        }
    }

    private static class Privacy {
        CustomDate date;
        String termName;

        public Privacy(String element) {
            String[] s = element.split(" ");
            this.date = new CustomDate(s[0]);
            this.termName = s[1];
        }
    }


    public static class InputDto {
        String today;
        String[] terms;
        String[] privacies;

        public InputDto(String today, String[] terms, String[] privacies) {
            this.today = today;
            this.terms = terms;
            this.privacies = privacies;
        }
    }
}
