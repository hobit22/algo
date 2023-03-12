package programmers.kakao.blind2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 표_병합 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] answerA = solution.solution(new String[]{"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"});
        String[] answerB = solution.solution(new String[]{"UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2", "MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"});

        System.out.println("answerA = " + Arrays.toString(answerA));
        System.out.println("answerB = " + Arrays.toString(answerB));
    }

    static class Solution {
        Cell[][] cellTable = new Cell[51][51];

        public String[] solution(String[] commands) {
            List<String> answer = new ArrayList<>();

            for (int x = 1; x < 51; x++) {
                for (int y = 1; y < 51; y++) {
                    cellTable[x][y] = new Cell();
                }
            }

            for (String command : commands) {
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
            int commandX = Integer.parseInt(s[1]);
            int commandY = Integer.parseInt(s[2]);

            Cell targetCell = cellTable[commandX][commandY];
            Cell parentCell = findParentCell(targetCell);

            targetCell.value = parentCell.value;
            targetCell.parentCell = null;

            for (int x = 1; x < 51; x++) {
                for (int y = 1; y < 51; y++) {
                    Cell searchCell = cellTable[x][y];
                    if (searchCell.parentCell == parentCell) {
                        searchCell.value = "";
                        searchCell.parentCell = null;
                    }
                }
            }

        }

        private void mergeCell(String[] s) {
            int x1 = Integer.parseInt(s[1]);
            int y1 = Integer.parseInt(s[2]);
            Cell cell1 = cellTable[x1][y1];
            Cell parentCell1 = cell1.parentCell != null ? findParentCell(cell1) : null;

            Cell tmp1 = parentCell1 == null ? cell1 : parentCell1;

            int x2 = Integer.parseInt(s[3]);
            int y2 = Integer.parseInt(s[4]);
            Cell cell2 = cellTable[x2][y2];
            Cell parentCell2 = cell2.parentCell != null ? findParentCell(cell2) : null;

            Cell tmp2 = parentCell2 == null ? cell2 : parentCell2;

            if (cell1 == cell2) {
            } else if (tmp1.value != "") {
                tmp2.value = "";
                tmp2.parentCell = tmp1;
            } else if (tmp2.value == "") {
                tmp1.value = "";
                tmp1.parentCell = tmp2;
            }
        }

        private Cell findParentCell(Cell cell) {
            if (cell.value != "") return cell;

            return findParentCell(cell.parentCell);
        }

        private String printCell(String[] s) {
            Cell cell = cellTable[Integer.parseInt(s[1])][Integer.parseInt(s[2])];

            if (cell.parentCell != null) {
                return findParentCell(cell).value;
            } else if (cell.value != "") {
                return cell.value;
            } else {
                return "EMPTY";
            }
        }

        private void updateCell(String[] s) {
            if (s.length == 3) {
                changeValue(s[1], s[2]);
            } else {
                insertValue(Integer.parseInt(s[1]), Integer.parseInt(s[2]), s[3]);
            }
        }

        private void insertValue(int x, int y, String value) {
            //check
            Cell cell = cellTable[x][y];
            Cell parentCell = cell.parentCell != null ? findParentCell(cell) : null;

            if (parentCell == null) {
                cell.value = value;
            } else {
                parentCell.value = value;
            }
        }

        private void changeValue(String searchStr, String replaceStr) {
            for (int x = 1; x < 51; x++) {
                for (int y = 1; y < 51; y++) {
                    if (cellTable[x][y].value.equals(searchStr)) {
                        cellTable[x][y].value = replaceStr;
                    }
                }
            }

        }


        private class Cell {
            String value = "";
            Cell parentCell = null;
        }
    }
}
