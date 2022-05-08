package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class bj1927 {

    static int N;
    static PriorityQueue<Integer> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x == 0) {
                if (queue.isEmpty()) sb.append(0).append("\n");
                else {
                    sb.append(queue.poll()).append("\n");
                }
            } else {
                queue.offer(x);
            }
        }

        System.out.println(sb);
    }
}