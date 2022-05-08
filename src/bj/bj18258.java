package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj18258 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        Queue queue = new Queue();
        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            switch (st.nextToken()) {
                case "push":
                    queue.push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(queue.pop()).append("\n");
                    break;
                case "size":
                    sb.append(queue.size()).append("\n");
                    break;
                case "empty":
                    sb.append(queue.empty()).append("\n");
                    break;
                case "front":
                    sb.append(queue.front()).append("\n");
                    break;
                case "back":
                    sb.append(queue.back()).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }

    public static class Queue {
        int first = 0;
        int last = 0;
        int[] q = new int[2000001];

        public int empty() {
            return (first == last) ? 1 : 0;
        }

        public int front() {
            if (first == last) return -1;
            return q[first];
        }

        public int back() {
            if (first == last) return -1;
            return q[last - 1];
        }

        public int size() {
            return last - first;
        }

        public void push(int x) {
            q[last++] = x;
        }

        public int pop() {
            if (first == last) return -1;
            return q[first++];
        }
    }
}
