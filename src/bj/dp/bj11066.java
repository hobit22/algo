package bj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 파일 합치기
 * dp
 */
public class bj11066 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(bf.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(bf.readLine());

            int[] arr = new int[n + 1];
            int[] sum = new int[n + 1];
            int[][] d = new int[n + 1][n + 1];

            st = new StringTokenizer(bf.readLine());

            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i - 1] + arr[i];
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j + i <= n; j++) {
                    int tmp = j + i;
                    d[j][tmp] = Integer.MAX_VALUE;
                    for (int k = j; k < tmp; k++) {
                        d[j][tmp] = Math.min(d[j][tmp], d[j][k] + d[k + 1][tmp] + sum[tmp] - sum[j - 1]);
                    }
                }
            }
            System.out.println(d[1][n]);
        }
    }
}
