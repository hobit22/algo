package bj.dataStructure;

import java.util.Scanner;
import java.util.Stack;

public class bj10773 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        Stack<Integer> stack = new Stack<>();
        int answer = 0;
        while(tc-- > 0){
            int num = sc.nextInt();
            if(num != 0){
                stack.push(num);
            }else{
                stack.pop();
            }
        }
        while(!stack.empty()) {
            answer += stack.pop();
        }
        System.out.println(answer);
    }
}
