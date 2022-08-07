package leetcode.backtracking;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC17 {
    private Map<Character, String> map = new HashMap<>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    private List<String> result = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits.length() != 0) {
            backTrack(digits, 0, new StringBuilder());
        }
        return result;
    }

    public void backTrack(String digits, int index, StringBuilder combination) {
        if (index == digits.length()) {  //到边界处
            result.add(combination.toString());
        } else {
            String letter = map.get(digits.charAt(index));
            for (char ch : letter.toCharArray()) {
                combination.append(ch);
                backTrack(digits, index + 1, combination);
                combination.deleteCharAt(combination.length() - 1);
            }
        }
    }

    @Test
    public void run() {
        letterCombinations("23").stream().forEach(System.out::println);
    }
}
