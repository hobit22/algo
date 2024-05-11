import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        List<int[]> list = new ArrayList<>();
        String[] s = {"code", "date", "maximum", "remain"};
        int extIdx = 0, sortIdx = 0;

        for (int i = 0; i < 4; i++) {
            if (ext.equals(s[i])) extIdx = i;
            if (sort_by.equals(s[i])) sortIdx = i;
        }

        final int si = sortIdx;

        for (int[] datum : data) {
            if (datum[extIdx] < val_ext) list.add(datum);
        }

        list.sort((o1, o2) -> o1[si] - o2[si]);

        return list.toArray(new int[0][]);
    }
}