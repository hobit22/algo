package bj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String inputString = bf.readLine();

        String[] stArr = inputString.split("-");

        int sum = 0;

        for (int i = 0; i < stArr.length; i++) {
            String st = stArr[i];
//            System.out.println("st = " + st);
            String[] plusArr = st.split("\\+");
            int tmp = 0;
            for (String x : plusArr) {
                tmp += Integer.parseInt(x);
            }
//            System.out.println("tmp = " + tmp);
            if(i==0) sum +=tmp;
            else sum-=tmp;
        }
//        System.out.println("sum = " + sum);
        System.out.print(sum);
    }

}
