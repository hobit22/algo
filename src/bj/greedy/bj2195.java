package bj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bj2195 {
    static char[] str1, str2;
    static int len1, len2;
    static Map<Character, List<Integer>> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new HashMap<>();

        str1 = br.readLine().toCharArray();
        len1 = str1.length;
        for (int i = 0; i < len1; i++) {
            char c = str1[i];

            if (!map.containsKey(c)) {
                map.put(c, new ArrayList<>());
            }

            List<Integer> list = map.get(c);
            list.add(i);
            map.put(c, list);
        }

        str2 = br.readLine().toCharArray();
        len2 = str2.length;

        int i = 0, ans = 0;
        while (i < len2) {
            List<Integer> list = map.get(str2[i]);

            int ret = 0;
            for (int idx : list) {
                int cnt = 0;
                int j = i;
                while (j < len2 && idx < len1) {
                    if (str2[j] == str1[idx]) {
                        j++;
                        idx++;
                        cnt++;
                        continue;
                    }
                    break;
                }
                ret = Math.max(ret, cnt);
            }
            i += ret;
            ans++;
        }
        System.out.println(ans);
    }
}
