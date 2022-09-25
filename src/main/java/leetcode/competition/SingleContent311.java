package leetcode.competition;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import javax.management.Query;

/**
 * @author gjd3
 */
public class SingleContent311 {
  public int smallestEvenMultiple(int n) {
    return (2 * n) / gcd(2, n);
  }

  private static int gcd(int a, int b) {
    int t;
    while (b != 0) {
      t = b;
      b = a % b;
      a = t;
    }
    return a;
  }

  //双指针
  public int longestContinuousSubstring(String s) {
    int max = Integer.MIN_VALUE;
    int slowIndex = 0;
    int quickIndex = 0;
    char[] chars = s.toCharArray();
    while (quickIndex < chars.length) {
      if (quickIndex + 1 < chars.length && chars[quickIndex + 1] - chars[quickIndex] == 1) {
        quickIndex++;
      } else {
        max = Math.max(max, quickIndex - slowIndex + 1);
        slowIndex = quickIndex + 1; //更新到下一位置等待
        quickIndex += 1;
      }
    }
    return max;
  }

  @Test
  public void run2() {
    longestContinuousSubstring("awy");
  }

  //层序遍历 反转偶数层
  public TreeNode reverseOddLevels(TreeNode root) {
    //
    Deque<TreeNode> deque = new ArrayDeque<>();
    deque.offerLast(root);
    int depth = 1; //首层
    while (!deque.isEmpty()) {
      int size = deque.size();
      Stack<TreeNode> curFloorTreeNode = new Stack<>();
      Queue<Integer> curFloorRetrogradeVal = new LinkedList<>();
      for (int i = 1; i <= size; i++) {
        TreeNode temp = deque.pollFirst();
        if (temp.left != null) {
          curFloorTreeNode.push(temp.left);
          curFloorRetrogradeVal.offer(temp.left.val);
          deque.offerLast(temp.left);
        }
        if (temp.right != null) {
          curFloorTreeNode.push(temp.right);
          curFloorRetrogradeVal.offer(temp.right.val);
          deque.offerLast(temp.right);
        }
      }
      depth++;
      if (depth % 2 == 0) {
        while (!curFloorTreeNode.isEmpty()) {
          TreeNode temp = curFloorTreeNode.pop();
          temp.val = curFloorRetrogradeVal.poll();
        }
      }

    }
    return root;
  }

  @Test
  public void run() {
    TreeNode root = new TreeNode(7);
    TreeNode left = new TreeNode(13);
    TreeNode right = new TreeNode(11);
    root.left = left;
    root.right = right;
    reverseOddLevels(root);

  }
}
