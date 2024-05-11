class Solution {
    int[] dx = {1,-1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    public int solution(String[][] board, int h, int w) {
        int answer = 0;

        String target = board[h][w];

        for (int i = 0; i < 4; i++) {
            int nextH = h + dx[i];
            int nextW = w + dy[i];

            if (nextW < 0 || nextH < 0 || nextW >= board.length || nextH >= board[0].length) continue;

            String nextTarget = board[nextH][nextW];

            if (target.equals(nextTarget)) answer++;

        }

        return answer;
    }
}