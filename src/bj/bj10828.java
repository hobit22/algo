package bj;

import java.io.*;
import java.util.Stack;

public class bj10828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0 ; i < tc ; i++){
            String ps = br.readLine();

            switch (ps) {
                case "top":
                    if (stack.empty()) {
                        System.out.println(-1);
                    } else {
                        System.out.println(stack.peek());
                    }
                    break;
                case "size":
                    System.out.println(stack.size());
                    break;
                case "pop":
                    if (stack.empty()) {
                        System.out.println(-1);
                    } else {
                        System.out.println(stack.pop());
                    }
                    break;
                case "empty":
                    if(stack.empty()) System.out.println(1);
                    else System.out.println(0);
                    break;
                default:
                    String[] st = ps.split(" ");
                    int x = Integer.parseInt(st[1]);
                    stack.push(x);
                    break;
            }
        }
        br.close();
    }
}
