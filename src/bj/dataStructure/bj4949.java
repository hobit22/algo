package bj.dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class bj4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringBuilder sb = new StringBuilder();
        String s;

        while (true) {
            s = br.readLine();

            if (s.equals(".")) {    // 종료 조건문
                break;
            }
            System.out.println(solve(s));
        }
    }

    public static String solve(String s) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            String check = s.substring(i, i + 1);

            if (check.equals("(") || check.equals("[")) {
                stack.push(check);
            }

            else if (check.equals(")")) {
                if (stack.empty() || !stack.peek().equals("(")) {
                    return "no";
                } else {
                    stack.pop();
                }
            }

            else if (check.equals("]")) {
                if (stack.empty() || !stack.peek().equals("[")) {
                    return "no";
                } else {
                    stack.pop();
                }
            }
        }

        if (stack.empty()) {
            return "yes";
        } else {
            return "no";
        }

    }
}
