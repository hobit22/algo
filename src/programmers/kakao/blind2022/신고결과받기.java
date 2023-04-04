package programmers.kakao.blind2022;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class 신고결과받기 {
    /**
     * id_list	report	k	result
     * ["muzi", "frodo", "apeach", "neo"]	["muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"]	2	[2,1,1,0]
     * ["con", "ryan"]	["ryan con", "ryan con", "ryan con", "ryan con"]	3	[0,0]
     */
    public static void main(String[] args) {
        Solution test = new Solution();

        int[] solutionA = test.solution(new String[]{"muzi", "frodo", "apeach", "neo"}, new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"}, 2);
        int[] solutionB = test.solution(new String[]{"con", "ryan"}, new String[]{"ryan con", "ryan con", "ryan con", "ryan con"}, 3);

        for (int i : solutionB) {
            System.out.println("i = " + i);
        }
    }

    static class Solution {
        public int[] solution(String[] id_list, String[] report, int k) {
            List<User> users = IntStream.range(0, id_list.length)
                    .mapToObj(i -> new User(i, id_list[i]))
                    .collect(Collectors.toList());

            List<Report> reports = Arrays.stream(report)
                    .map(rawReport -> ReportParser.parse(users, rawReport))
                    .collect(Collectors.toList());

            List<Mail> mails = sendMail(reports, users, k);

            return users.stream()
                    .mapToInt(user -> (int) mails.stream().filter(mail -> mail.isSameUser(user)).count()).toArray();
        }

        private List<Mail> sendMail(List<Report> reports, List<User> users, int threshold) {
            List<User> sortedUsers = users.stream().sorted().collect(Collectors.toList());

            return sortedUsers.stream().map(user -> checkMailCount(reports, user, threshold))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        }

        private List<Mail> checkMailCount(List<Report> reports, User user, int threshold) {
            List<Report> userReports = reports.stream()
                    .filter(report -> report.reported.equals(user)).distinct().collect(Collectors.toList());

            if (userReports.size() >= threshold) {
                return userReports.stream()
                        .map(report -> report.reporter)
                        .map(Mail::new)
                        .collect(Collectors.toList());
            }
            return Collections.emptyList();
        }

        private static class Mail {
            private final User recipient;

            public Mail(User recipient) {
                this.recipient = recipient;
            }

            public boolean isSameUser(User user) {
                return recipient.userId.equals(user.userId);
            }
        }

        private static class ReportParser {
            private static final String DELIMITER = " ";

            public static Report parse(List<User> users, String report) {
                String[] splitted = report.split(DELIMITER);
                User reporter = users.stream().filter(user -> user.userId.equals(splitted[0])).findAny().orElse(null);
                User reported = users.stream().filter(user -> user.userId.equals(splitted[1])).findAny().orElse(null);

                return new Report(reporter, reported);
            }
        }

        private static class User implements Comparable<User> {
            public final Integer sequence;
            public final String userId;

            public User(Integer sequence, String userId) {
                this.sequence = sequence;
                this.userId = userId;
            }

            @Override
            public int compareTo(User other) {
                return this.sequence.compareTo(other.sequence);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                User user = (User) o;
                return Objects.equals(sequence, user.sequence) && Objects.equals(userId, user.userId);
            }

            @Override
            public int hashCode() {
                return Objects.hash(sequence, userId);
            }
        }

        private static class Report {
            public final User reporter;
            public final User reported;

            public Report(User reporter, User reported) {
                this.reporter = reporter;
                this.reported = reported;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Report report = (Report) o;
                return Objects.equals(reporter, report.reporter) && Objects.equals(reported, report.reported);
            }

            @Override
            public int hashCode() {
                return Objects.hash(reporter, reported);
            }
        }
    }
}
