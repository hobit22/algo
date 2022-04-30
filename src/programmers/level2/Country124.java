package programmers.level2;

class Country124 {
    public String solution(int n) {
        String[] numbers = {"4", "1", "2"};
        String answer = "";

        while(n>0){
            int tmp = n % 3;
            n = n / 3;
            if(tmp == 0) n--;
            answer = numbers[tmp] + answer;
        }

        return answer;
    }
}