package leetcode.stackAndQueue;

import org.junit.Test;

import java.util.Stack;

/**
 * @author gjd3
 */
public class LC946 {
  public boolean validateStackSequences(int[] pushed, int[] popped) {
    Stack<Integer> stack = new Stack<>();
    int n = popped.length;
    int pushedIndex = 0;
    int popedIndex = 0;
    while (pushedIndex < n) {
      if (!stack.isEmpty() && stack.peek() == popped[popedIndex]) {
        stack.pop();
        popedIndex++;
      } else {
        stack.push(pushed[pushedIndex]);
        pushedIndex++;
      }
    }
    while (popedIndex < n) {
      if (!stack.isEmpty() && stack.peek() == popped[popedIndex]) {
        stack.pop();
        popedIndex++;
      } else {
        break;
      }
    }
    return stack.isEmpty();
  }

  @Test
  public void run() {
    System.out.println(validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}));
  }
}
