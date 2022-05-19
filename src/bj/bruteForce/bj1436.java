package bj.bruteForce;

import java.util.Scanner;

public class bj1436 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int start = 666;
        while(true){
            String startString = Integer.toString(start);

            if(startString.contains("666")){
                n--;
                if(n==0) break;
            }
            start++;
        }
        System.out.println(start);

    }
}
