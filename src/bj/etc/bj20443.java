package bj.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 점화식
 * f(n) = (n-1)(f(n-1) + f(n-2))
 */

public class bj20443 {

    private static Map<Long, Long> map = new HashMap<>();
    private static int[][] combinationCache = new int[100][4];


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        map.put(0L, 0L);
        map.put(1L, 0L);
        map.put(2L, 1L);
        int n = Integer.parseInt(st.nextToken());

        int x = n % 4; // 나머지

        int m = n - x; // 번호를 뽑을 참가자 수

        long result = recursive(m);

        int com = calculateCombination(n, x);

        long ans = result * com;

        System.out.println(ans % 1000000007);

    }

    private static int calculateCombination(int n, int r) {
        if (r == 0 || r == n) {
            return 1;
        } else if (r == 1) {
            return n;
        } else {
            // 이전에 계산한 결과가 있는지 확인하여 있으면 재사용
            if (combinationCache[n][r] != 0) {
                return combinationCache[n][r];
            }

            // 조합 개수 계산
            int result = calculateCombination(n - 1, r - 1) + calculateCombination(n - 1, r);

            // 결과를 캐시에 저장
            combinationCache[n][r] = result;

            return result;
        }
    }

    private static long recursive(long n) {
        // 캐시에 저장된 값이 있는지 확인
        if (map.containsKey(n)) {
            return map.get(n);
        }

        // 계산이 필요한 경우 재귀적으로 계산하고 결과를 캐시에 저장
        long result = (n - 1) * (recursive(n - 1) + recursive(n - 2)) % 1000000007;
        map.put(n, result);
        return result;
    }

}
