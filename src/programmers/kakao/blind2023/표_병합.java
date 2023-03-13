package programmers.kakao.blind2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 표_병합 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] answerA = solution.solution(new String[]{
                "UPDATE 1 1 menu",
                "UPDATE 1 2 category",
                "UPDATE 2 1 bibimbap",
                "UPDATE 2 2 korean",
                "UPDATE 2 3 rice",
                "UPDATE 3 1 ramyeon",
                "UPDATE 3 2 korean",
                "UPDATE 3 3 noodle",
                "UPDATE 3 4 instant",
                "UPDATE 4 1 pasta",
                "UPDATE 4 2 italian",
                "UPDATE 4 3 noodle",

                "MERGE 1 2 1 3",
                "MERGE 1 3 1 4",

                "UPDATE korean hansik",
                "UPDATE 1 3 group",

                "UNMERGE 1 4",

                "PRINT 1 3", "PRINT 1 4"});
        String[] answerB = solution.solution(new String[]{
                "UPDATE 1 1 a",
                "UPDATE 1 2 b",
                "UPDATE 2 1 c",
                "UPDATE 2 2 d",

                "MERGE 1 1 1 2",
                "MERGE 2 2 2 1",
                "MERGE 2 1 1 1",

                "PRINT 1 1",

                "UNMERGE 2 2",

                "PRINT 1 1"});

        System.out.println("answerA = " + Arrays.toString(answerA));
        System.out.println("answerB = " + Arrays.toString(answerB));
    }

    static class Solution {
        static String[][] map = new String[51][51];
        static Pos[][] ref = new Pos[51][51];

        public String[] solution(String[] commands) {
            List<String> answer = new ArrayList<>();

            for (int x = 1; x < 51; x++) {
                for (int y = 1; y < 51; y++) {
                    ref[x][y] = new Pos(x, y);
                }
            }

            for (String command : commands) {
                if (command.contains("group")) {
                    System.out.println("command = " + command);
                }
                String[] s = command.split(" ");

                switch (s[0]) {
                    case "UPDATE":
                        updateCell(s);
                        break;
                    case "MERGE":
                        mergeCell(s);
                        break;
                    case "UNMERGE":
                        unmergeCell(s);
                        break;
                    case "PRINT":
                        answer.add(printCell(s));
                        break;
                }
            }


            return answer.toArray(new String[0]);
        }

        private void unmergeCell(String[] s) {
            int x = Integer.parseInt(s[1]);
            int y = Integer.parseInt(s[2]);

            int newx = ref[x][y].x;
            int newy = ref[x][y].y;

            String str = map[newx][newy];

            for (int i = 1; i < 51; i++) {
                for (int j = 1; j < 51; j++) {

                    if (ref[i][j].x == newx && ref[i][j].y == newy) {
                        ref[i][j] = new Pos(i, j);
                        map[i][j] = null;
                    }

                }
            }

            map[x][y] = str;

        }

        private void mergeCell(String[] s) {
            int x1 = Integer.parseInt(s[1]);
            int y1 = Integer.parseInt(s[2]);

            int x2 = Integer.parseInt(s[3]);
            int y2 = Integer.parseInt(s[4]);

            int newx1 = ref[x1][y1].x;
            int newy1 = ref[x1][y1].y;
            int newx2 = ref[x2][y2].x;
            int newy2 = ref[x2][y2].y;

            for (int i = 1; i < 51; i++) {
                for (int j = 1; j < 51; j++) {
                    if (ref[i][j].x == newx2 && ref[i][j].y == newy2) {
                        ref[i][j] = new Pos(newx1, newy1);
                    }
                }
            }

            if (newx1 != newx2 || newy1 != newy2) {
                // 두 셀 중 한 셀이 값을 가지고 있을 경우 병합된 셀은 그 값을 가짐
                if (map[newx1][newy1] != null && map[newx2][newy2] == null) {
                    map[newx2][newy2] = map[newx1][newy1];
                } else if (map[newx1][newy1] == null && map[newx2][newy2] != null) {
                    map[newx1][newy1] = map[newx2][newy2];
                }

                // 두 셀 모두 값을 가지고 있을 경우 병합된 셀은 r1,c1 위치의 셀 값 가짐
                else if (map[newx1][newy1] != null && map[newx2][newy2] != null) {
                    map[newx2][newy2] = map[newx1][newy1];
                }
            }

        }


        private String printCell(String[] s) {
            int x = Integer.parseInt(s[1]);
            int y = Integer.parseInt(s[2]);

            int newx = ref[x][y].x;
            int newy = ref[x][y].y;

            String str = map[newx][newy];

            if (str == null) return "EMPTY";
            else return str;
        }

        private void updateCell(String[] s) {
            if (s.length == 4) {
                int x = Integer.parseInt(s[1]);
                int y = Integer.parseInt(s[2]);
                String str = s[3];

                map[ref[x][y].x][ref[x][y].y] = str;
            } else {
                String str1 = s[1];
                String str2 = s[2];

                for (int i = 1; i < 51; i++) {
                    for (int j = 1; j < 51; j++) {
                        if (map[i][j] == null) continue;
                        if (map[i][j].equals(str1)) {
                            map[i][j] = str2;
                        }
                    }
                }
            }
        }

        private static class Pos {
            int x;
            int y;

            public Pos(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }
}
