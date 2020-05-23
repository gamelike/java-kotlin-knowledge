package Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PostfixExpression {
    public static void main(String[] args) {
        // 后缀表达式 (3 + 4) * 5 - 6
        // 3 4 + 5 * 6 -
        //["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
        String postfixExpression = "10 6 9 3 + -11 * / 17 + 5 +";

        List<String> list = toArray(postfixExpression);
        System.out.println(list);

        int calculate = Calculate(list);
        System.out.println(calculate);
    }

    public static List<String> toArray(String str){
        String[] split = str.split(" ");

        return new ArrayList<>(Arrays.asList(split));
    }

    public static int Calculate(List<String> list){
        Stack<String> stack = new Stack<>();

        for (String s : list) {
            if (s.matches("\\d+") || s.matches("-\\d+")){
                stack.push(s);
            }else{
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int result = 0;
                switch (s){
                    case "+" : result = num1 + num2; break;
                    case "*" : result = num2 * num1; break;
                    case "-" : result = num2 - num1; break;
                    case "/" : result = num2 / num1; break;
                }
                stack.push("" + result);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
