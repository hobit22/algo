package bj.bruteForce;

import java.util.Scanner;

public class bj2231 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int answer = 1;
        while(true){
            int sum = 0;
            int tmp = answer;
            while(tmp != 0){
                sum += tmp % 10;
                tmp = tmp / 10;
            }
            if(sum + answer == n) break;
            answer++;
            if(answer >= n){
                System.out.println(0);
                return;
            }
        }
        System.out.println(answer);
    }
}
