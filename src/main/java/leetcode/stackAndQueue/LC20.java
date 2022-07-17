package leetcode.stackAndQueue;

import java.util.Stack;

/**
 * @author gjd
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC20 {
  public boolean isValid(String s) {
    char[] chs = s.toCharArray();
    Stack<Character> stack = new Stack<>();
    for (char ch : chs) {
      if (ch == '(') {
        stack.push(')');
      } else if (ch == '[') {
        stack.push(']');
      } else if (ch == '{') {
        stack.push('}');
      } else {
        if (stack.empty() || stack.pop() != ch) {
          return false;
        }
      }
    }
    return stack.empty();
  }
}
