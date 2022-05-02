package bj;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class bj9012 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < tc ; i++){
            String ps = br.readLine();
            Stack<String> stack = new Stack<>();
            String answer = "";
            for(int j = 0 ; j < ps.length(); j++){
                String check = ps.substring(j,j+1);
                if(check.equals("(")){
                    stack.push(check);
                }else if(check.equals(")")){
                    if(stack.isEmpty()){
                        answer = "NO";
                        break;
                    }
                    stack.pop();
                }
            }
            if(stack.isEmpty() && answer.equals("")){
                System.out.println("YES");
            }else {
                System.out.println("NO");
            }
        }
    }
}
