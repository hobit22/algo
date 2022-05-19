package bj.dataStructure;


import java.util.Scanner;
import java.util.Stack;

public class bj1874 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = in.nextInt();
        int check = 0;
        Stack<Integer> stack = new Stack<>();
        while(n-- > 0){
            int target = in.nextInt();
            if(target > check){
                for(int i = check + 1 ; i <= target ; i++){
                    stack.push(i);
                    sb.append("+").append("\n");
                }
                check = target;
            }else if(stack.peek() != target){
                System.out.println("NO");
                return;
            }
            stack.pop();
            sb.append("-").append("\n");
        }
        System.out.println(sb);

    }
}
