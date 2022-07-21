package leetcode.stackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;
import org.junit.Test;

public class LC150 {

  public int evalRPN(String[] tokens) {
    Deque<String> caculator = new LinkedBlockingDeque<>();
    for (String token : tokens) {
      if (token.equals("+")) {
        caculator.offerLast(String.valueOf(
            Integer.valueOf(caculator.pollLast()) + Integer.valueOf(caculator.pollLast())));
      } else if (token.equals("-")) {
        int right = Integer.valueOf(caculator.pollLast());
        int left = Integer.valueOf(caculator.pollLast());
        caculator.offerLast(String.valueOf(
            left - right));
      } else if (token.equals("*")) {
        caculator.offerLast(String.valueOf(
            Integer.valueOf(caculator.pollLast()) * Integer.valueOf(caculator.pollLast())));
      } else if (token.equals("/")) {
        int right = Integer.valueOf(caculator.pollLast());
        int left = Integer.valueOf(caculator.pollLast());
        caculator.offerLast(String.valueOf(
            left / right));
      } else {
        caculator.offerLast(token);
      }
    }
    return Integer.valueOf(caculator.pollLast());
  }

  @Test
  public void run(){
    evalRPN(new String[]{"4","13","5","/","+"});
  }
}
