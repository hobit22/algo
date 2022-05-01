package programmers.level2;

class ThatSong {
    public String solution(String m, String[] musicinfos) {

        String answer = "";
        int answerTime = 0;

        for (String musicinfo : musicinfos) {
            String[] musicInfoSplit = musicinfo.split(",");
            String[] startTime = musicInfoSplit[0].split(":");
            String[] endTime = musicInfoSplit[1].split(":");

            String title = musicInfoSplit[2];
            String detail = musicInfoSplit[3];
            detail = detail.replaceAll("C#", "c");
            detail = detail.replaceAll("D#", "d");
            detail = detail.replaceAll("F#", "f");
            detail = detail.replaceAll("G#", "g");
            detail = detail.replaceAll("A#", "a");


            int end = Integer.parseInt(endTime[0]) * 60 + Integer.parseInt(endTime[1]);
            int start = Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]);

            if (start > end) {
                end = 24 * 60;
            }

            int musicTime = end - start;

            int detailLength = detail.length();

            int repeat = musicTime / detailLength;
            int remain = musicTime % detailLength;
            String totalDetail = "";
            for (int i = 0; i < repeat; i++) {
                totalDetail = totalDetail.concat(detail);
            }
            totalDetail = totalDetail.concat(detail.substring(0, remain));

            m = m.replaceAll("C#", "c");
            m = m.replaceAll("D#", "d");
            m = m.replaceAll("F#", "f");
            m = m.replaceAll("G#", "g");
            m = m.replaceAll("A#", "a");

            if (totalDetail.contains(m) && musicTime > answerTime) {
                answer = title;
                answerTime = musicTime;
            }
        }

        if (answer.equals("")) {
            return "(None)";
        } else {
            return answer;
        }
    }
}