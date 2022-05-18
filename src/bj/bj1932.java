package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj1932 {
    static int[][] triangle = new int[501][501];
    static int[][] max_sum = new int[501][501];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tri_size = Integer.parseInt(br.readLine());
        for (int i = 0; i < tri_size; i++) {
            String ps = br.readLine();
            String[] stArr = ps.split(" ");
            for (int j = 0; j < stArr.length; j++) {
                triangle[i][j] = Integer.parseInt(stArr[j]);
            }
        }

        max_sum[0][0] = triangle[0][0];

        for (int i = 1; i < tri_size; i++) {
            for (int j = 0; j < i+1 ; j++) {
                if(j == 0){
                    max_sum[i][j] = max_sum[i - 1][0] + triangle[i][j];
                }else if(j == i){
                    max_sum[i][j] = max_sum[i - 1][j-1] + triangle[i][j];
                }else{
                    max_sum[i][j] = Math.max(max_sum[i - 1][j - 1], max_sum[i - 1][j]) + triangle[i][j];
                }
            }
        }
        int max = 0;
        for(int i = 0 ; i < tri_size; i++){
            if(max_sum[tri_size-1][i] > max){
                max = max_sum[tri_size-1][i];
            }
        }
        System.out.println(max);

    }
}
