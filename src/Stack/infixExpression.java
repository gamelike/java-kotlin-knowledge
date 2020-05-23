package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: gameLike
 * @Date: 2020/5/20
 */
public class infixExpression {
    public static List<String> toArrayList(String str){
        List<String> postfix = new ArrayList<>();
        int i = 0;
        String[] strs = str.split("");
        StringBuilder temp = new StringBuilder();
        for (String s : strs) {
            if(!s.matches("\\d")){
                if (!temp.toString().equals("")) {
                    postfix.add(temp.toString());
                    temp = new StringBuilder();
                }
                postfix.add(s);
            }else {
                temp.append(s);
            }
        }
        postfix.add(temp.toString());
        return postfix;
    }

    public static List<String> toPostfix(List<String> list){
        Stack<String> symbol = new Stack<>();
        List<String> postfix = new ArrayList<>();
        Operation operation = new Operation();

        for (String s : list) {
            if (s.matches("\\d+")){
                postfix.add(s);
            }else if (s.equals("(")){
                symbol.push(s);
            }else if (s.equals(")")){
                while(!symbol.peek().equals("(")){
                    postfix.add(symbol.pop());
                }
                symbol.pop();
            }else {
                while(symbol.size() != 0 && (operation.getValue(symbol.peek()) >= operation.getValue(s))){
                    postfix.add(symbol.pop());
                }
                symbol.push(s);
            }
        }

        while (symbol.size() != 0){
            postfix.add(symbol.pop());
        }

        return postfix;
    }

    public static void main(String[] args) {
        String infix = "1+(12*2)-5*10";
        List<String> list = toArrayList(infix);
        System.out.println(list);
        List<String> postfix = toPostfix(list);
        System.out.println(postfix);
    }
}

class Operation{
    private final int ADD = 1;
    private final int SUB = 1;
    private final int TIM = 2;
    private final int DIV = 2;

    public int getValue(String str){
        switch (str){
            case "+": return ADD;
            case "-": return SUB;
            case "*": return TIM;
            case "/": return DIV;
            default: return 0;
        }
    }
}