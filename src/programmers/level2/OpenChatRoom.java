package programmers.level2;

import java.util.*;

class OpenChatRoom {
    public String[] solution(String[] record) {
        ArrayList<String> logList = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();

        for(String x : record){
            StringTokenizer token = new StringTokenizer(x);
            String action = token.nextToken();
            String userId = token.nextToken();
            String nickname = "";

            if(!action.equals("Leave")) nickname = token.nextToken();

            switch(action){
                case "Enter":
                    map.put(userId, nickname);
                    logList.add(userId + "님이 들어왔습니다.");
                    break;
                case "Leave":
                    logList.add(userId + "님이 나갔습니다.");
                    break;
                case "Change":
                    map.put(userId, nickname);
                    break;
            }

        }
        String[] answer =new String[logList.size()];

        for(int i = 0 ; i < logList.size(); i++){
            int idx = logList.get(i).indexOf("님");

            String userId = logList.get(i).substring(0, idx);

            answer[i] = logList.get(i).replace(userId, map.get(userId));
        }

        return answer;
    }
}