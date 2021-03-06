package programmers.level2;

import java.util.HashSet;
import java.util.LinkedList;

public class MaxFormula{
    static long maxAnswer;
    static LinkedList<Long> numList;
    static LinkedList<Character> opList;
    static LinkedList<Character> opSetList;
    static HashSet<Character> opSet;
    public static void main(String[] args) {
        System.out.println(solution("50*6-3*2"));
        System.out.println(solution("100-200*300-500+20"));
    }
    public static long solution(String exp){
        numList = new LinkedList<>();
        opList = new LinkedList<>();
        opSetList = new LinkedList<>();
        opSet = new HashSet<>();

        splitExp(exp);

        getOperator(new LinkedList<>(), new boolean[opSetList.size()]);

        return maxAnswer;
    }

    private static void getOperator(LinkedList<Character> list, boolean[] picked) {
        if(list.size() == opSetList.size()){
            LinkedList<Long> copyNumList = new LinkedList<>(numList);
            LinkedList<Character> copyOpList = new LinkedList<>(opList);

            for(char tmp : list){
                for (int i = 0; i < copyOpList.size(); i++) {
                    if(copyOpList.get(i) != tmp) continue;

                    long l1 = copyNumList.get(i);
                    long l2 = copyNumList.get(i + 1);

                    copyNumList.remove(i+1);
                    copyNumList.remove(i);

                    copyOpList.remove(i);

                    copyNumList.add(i, calcNumber(l1, l2, tmp));
                    i--;
                }
            }
            long flagAnswer = Math.abs(copyNumList.get(0));
            maxAnswer = Math.max(flagAnswer, maxAnswer);

        }

        for(int i = 0 ; i < opSetList.size();i++){
            if(!picked[i]){
                picked[i] = true;
                list.add(opSetList.get(i));

                getOperator(list,picked);

                picked[i] = false;
                list.removeLast();
            }
        }
    }

    private static Long calcNumber(long l1, long l2, char op) {
        long result = 0;
        switch (op){
            case '*':
                result = l1 * l2;
                break;
            case '+':
                result = l1 + l2;
                break;
            default:
                result = l1 - l2;
                break;
        }
        return result;
    }

    private static void splitExp(String exp) {
        char[] arr = exp.toCharArray();
        StringBuilder numberStr = new StringBuilder();
        for(int i = 0 ; i < arr.length; i++){
            if(arr[i] == '*'|| arr[i]=='-'|| arr[i]=='+'){
                long number = Long.valueOf(numberStr.toString());
                numList.add(number);
                opList.add(arr[i]);
                opSet.add(arr[i]);
                numberStr = new StringBuilder();
            }else{
                numberStr.append(arr[i]);
            }
        }
        numList.add(Long.valueOf(numberStr.toString()));

        opSetList = new LinkedList<>(opSet);
    }
}