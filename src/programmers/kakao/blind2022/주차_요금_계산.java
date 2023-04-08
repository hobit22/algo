package programmers.kakao.blind2022;

import java.util.*;

public class 주차_요금_계산 {
    /*
    fees	records	result
[180, 5000, 10, 600]	["05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"]	[14600, 34400, 5000]
[120, 0, 60, 591]	["16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"]	[0, 591]
[1, 461, 1, 10]	["00:00 1234 IN"]	[14841]
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] test1 = solution.solution(new int[]{180, 5000, 10, 600}, new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"});
        int[] test2 = solution.solution(new int[]{120, 0, 60, 591}, new String[]{"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"});
        int[] test3 = solution.solution(new int[]{1, 461, 1, 10}, new String[]{"00:00 1234 IN"});

        System.out.println("test1 = " + test1);
        System.out.println("test2 = " + test2);
        System.out.println("test3 = " + test3);

    }
    static class Solution {
        public int[] solution(int[] fees, String[] records) {
            int[] answer = {};

            Map<String, String> carMap = new TreeMap<>();
            Map<String, Integer> feeMap = new TreeMap<>();

            for (String record : records) {
                feeMap.put(record.split(" ")[1], 0);
            }

            for (String record : records) {
                String[] infos = record.split(" ");

                if (carMap.containsKey(infos[1])) {
                    String[] inTime = carMap.remove(infos[1]).split(":");
                    String[] outTime = infos[0].split(":");

                    int hour = Integer.parseInt(outTime[0]) - Integer.parseInt(inTime[0]);
                    int minute = Integer.parseInt(outTime[1]) - Integer.parseInt(inTime[1]);

                    feeMap.replace(infos[1], feeMap.get(infos[1]) + 60 * hour + minute);

                } else {
                    carMap.put(infos[1], infos[0]); // 차 번호, 시간
                }
            }

            for(String key : carMap.keySet()){
                String[] inTime = carMap.get(key).split(":");

                int hour = 23 - Integer.parseInt(inTime[0]);
                int minute = 59 -Integer.parseInt(inTime[1]);

                feeMap.replace(key, feeMap.get(key) + 60 * hour + minute);
            }

            List<Map.Entry<String, Integer>> list = new ArrayList(feeMap.entrySet());

            answer = new int[list.size()];

            for(int i = 0; i < answer.length; i++){
                if(list.get(i).getValue() > fees[0]){
                    answer[i] = fees[1] + (int) Math.ceil((list.get(i).getValue() - fees[0]) / (double)fees[2]) * fees[3];
                }else{
                    answer[i] = fees[1];
                }
            }

            return answer;
        }
    }
}
