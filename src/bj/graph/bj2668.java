package bj.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class bj2668 {
    static ArrayList<Integer> list;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        arr = new int[n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i < n + 1; i++) {
            arr[i] = sc.nextInt();
        }

        list = new ArrayList<>();
        for (int i = 1; i < n + 1; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        Collections.sort(list);
        System.out.println(list.size());
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    private static void dfs(int start, int target) {
        if (!visited[arr[start]]) {
            visited[arr[start]] = true;
            dfs(arr[start], target);
            visited[arr[start]] = false;
        }
        if(arr[start] == target) list.add(target);
    }
}
