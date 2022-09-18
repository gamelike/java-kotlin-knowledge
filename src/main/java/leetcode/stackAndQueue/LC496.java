package leetcode.stackAndQueue;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author gjd3
 */
public class LC496 {


  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    Stack<Integer> stack = new Stack<>();
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = nums2.length - 1; i >= 0; i--) {
      while (!stack.isEmpty() && stack.peek() < nums2[i]) {
        stack.pop();
      }
      map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
      stack.push(nums2[i]);
    }
    int[] res = new int[nums1.length];
    for (int i = 0; i < nums1.length; i++) {
      res[i] = map.get(nums1[i]);
    }
    return res;
  }

  //单调递增
  private Stack singStack(int[] nums2) {
    Stack<Integer> stack = new Stack<>();
    for (int i = nums2.length - 1; i >= 0; i--) {
      while (!stack.isEmpty() && stack.peek() < nums2[i]) {
        stack.pop();
      }
      stack.push(nums2[i]);
    }
    return stack;
  }
}
