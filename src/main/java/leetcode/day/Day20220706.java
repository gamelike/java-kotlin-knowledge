package leetcode.day;

import java.util.*;

public class Day20220706 {

    int start = 0;
    Map<String, Deque<Integer>> scope = new HashMap<>();

    public int evaluate(String expression) {
        return evaluateBacktrace(expression);
    }

    public int evaluateBacktrace(String expression) {
        char ch = expression.charAt(start);
        if (ch != '(') {
            if (Character.isLowerCase(ch)) {
                String var = parseVar(expression); // 变量
                return scope.get(var).peek();
            } else {
                return parseInt(expression); // 整数
            }
        }
        int ret;
        // 跳过空格
        start++;
        if (expression.charAt(start) == 'l') {
            // 跳过let
            start += 4;
            List<String> vars = new ArrayList<>();
            while (true) {
                // 字母 变量名称
                if (!Character.isLowerCase(expression.charAt(start))) {
                    ret = evaluateBacktrace(expression);
                    break;
                }
                // 变量值
                String var = parseVar(expression);
                if (expression.charAt(start) == ')') {
                    ret = scope.get(var).peek();
                    break;
                }
                // 添加
                vars.add(var);
                start++;
                int e = evaluateBacktrace(expression);
                scope.putIfAbsent(var, new ArrayDeque<>());
                scope.get(var).push(e);
                start++;
            }
            for (String v : vars) {
                scope.get(v).pop();
            }
        } else if (expression.charAt(start) == 'a') {
            // 跳过add
            start += 4;
            int e1 = evaluateBacktrace(expression);
            start++;
            int e2 = evaluateBacktrace(expression);
            ret = e1 + e2;
        } else {
            // 跳过mult
            start += 5;
            int e1 = evaluateBacktrace(expression);
            start++;
            int e2 = evaluateBacktrace(expression);
            ret = e1 * e2;
        }
        //跳过 )
        start++;
        return ret;
    }

    private int parseInt(String str) {
        int ret = 0, sign = 1;
        if (str.charAt(start) == '-') {
            sign = -1;
            start++;
        }
        while (start < str.length() && Character.isDigit(str.charAt(start))) {
            ret *= 10;
            ret += str.charAt(start) - '0';
            start++;
        }
        return sign * ret;
    }

    private String parseVar(String num) {
        int n = num.length();
        StringBuilder ret = new StringBuilder();
        while (start < n && num.charAt(start) != ' ' && num.charAt(start) != ')') {
            ret.append(num.charAt(start));
            start++;
        }
        return ret.toString();
    }
}
