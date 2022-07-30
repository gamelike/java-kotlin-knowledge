package leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author gjd
 */
public class LC101 {
  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }
    Deque<TreeNode> deque = new ArrayDeque<>();
    deque.offerLast(root.left);
    deque.offerLast(root.right);
    while (!deque.isEmpty()) {
      TreeNode leftNode = deque.pollFirst();
      TreeNode rightNode = deque.pollFirst();
      if (leftNode == null && rightNode == null) { //同时为空 则为true
        continue;
      }
      // 左右一个节点不为空，或者都不为空但数值不相同，返回false
      if ((leftNode == null && rightNode != null) || (leftNode != null && rightNode == null) || (leftNode.val != rightNode.val)) {
        return false;
      }
      deque.offerLast(leftNode.left);
      deque.offerLast(rightNode.right);
      deque.offerLast(leftNode.right);
      deque.offerLast(rightNode.left);
    }
    return true;
  }


}
