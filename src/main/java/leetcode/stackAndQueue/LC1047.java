package leetcode.stackAndQueue;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * <p>
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 * <p>
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gjd
 */
public class LC1047 {
  public String removeDuplicates(String s) {
    char[] chars = s.toCharArray();
    Stack<Character> stack = new Stack<>();
    for (char ch : chars) {
      if (!stack.empty() && stack.peek() == ch) {
        stack.pop();
      } else {
        stack.push(ch);
      }
    }
    StringBuilder stb = new StringBuilder();
    while (!stack.empty()) {
      stb.append(stack.pop());
    }
    return stb.reverse().toString();
  }
}
