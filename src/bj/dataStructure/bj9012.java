package bj.dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class bj9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        // Test Case 만큼 for 문 돌기
        for (int i = 0; i < tc; i++) {
            String ps = br.readLine();

            // TC 마다 stack 새로 생성
            Stack<String> stack = new Stack<>();
            String answer = "";
            for (int j = 0; j < ps.length(); j++) {

                //pc 에서 문자1개씩 잘라서 저장
                String check = ps.substring(j, j + 1);
                if (check.equals("(")) {
                    stack.push(check);
                } else if (check.equals(")")) {

                    // ) 인데 스택이 비어있는 경우 break
                    if (stack.isEmpty()) {
                        answer = "NO";
                        break;
                    }
                    stack.pop();
                }
            }

            if (stack.isEmpty() && answer.equals("")) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
