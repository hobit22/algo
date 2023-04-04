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
        public int[] solution(String[] idList, String[] report, int k) {
            int[] answer = new int[idList.length];
            HashMap<String, HashSet<String>> reporterInfoMap = new HashMap<>();
            HashMap<String, Integer> reportedCountInfoMap = new HashMap<>();
            HashSet<String> reportSet = new HashSet<>(Arrays.asList(report));

            for (String reportInfo : reportSet) {
                String reporter = reportInfo.split(" ")[0];  // 신고 한 사람
                String reported = reportInfo.split(" ")[1];  // 신고 당한 사람
                reporterInfoMap.putIfAbsent(reporter, new HashSet<String>() {{
                    add(reported);
                }});
                reporterInfoMap.get(reporter).add(reported);
                reportedCountInfoMap.put(reported, reportedCountInfoMap.getOrDefault(reported, 0) + 1);
            }

            for (String reported : reportedCountInfoMap.keySet()) {
                int reportedCount = reportedCountInfoMap.get(reported);
                if (reportedCount >= k) {
                    // 메일 발송 대상
                    for (int i = 0; i < idList.length; i++) {
                        if (reporterInfoMap.containsKey(idList[i]) && reporterInfoMap.get(idList[i]).contains(reported)) {
                            answer[i]++;
                        }
                    }
                }
            }
            return answer;
        }
    }
}
