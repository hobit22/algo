package bj;

import java.util.Scanner;

public class bj11279 {
    public static int[] heap = new int[100001];
    public static int size = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        while (n-- > 0) {
            int x = sc.nextInt();

            if (x == 0) {
                if (size == 0) System.out.println(0);
                else
                    pop();
            } else {
                push(x);
            }
        }
    }

    public static void push(int x) {
        heap[++size] = x;
        for (int i = size; i > 1; i /= 2) {
            if (heap[i / 2] < heap[i]) {
                int tmp = heap[i / 2];
                heap[i / 2] = heap[i];
                heap[i] = tmp;
            } else {
                break;
            }
        }
    }

    public static void pop() {
        System.out.println(heap[1]);
        heap[1] = heap[size];
        heap[size] = 0;
        size--;
        for (int i = 1; i * 2 <= size; ) {
            if (heap[i] > heap[i * 2] && heap[i] > heap[i * 2 + 1]) {
                break;
            } else if (heap[i * 2] > heap[i * 2 + 1]) {
                int tmp = heap[i];
                heap[i] = heap[i * 2];
                heap[i * 2] = tmp;
                i = i * 2;
            } else {
                int tmp = heap[i];
                heap[i] = heap[i * 2 + 1];
                heap[i * 2 + 1] = tmp;
                i = i * 2 + 1;
            }
        }
    }
}
