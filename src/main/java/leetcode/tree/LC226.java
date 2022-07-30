package leetcode.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author gjd
 */
public class LC226 {
  public TreeNode invertTree(TreeNode root) {
    Deque<TreeNode> deque = new LinkedList<>();
    if (root != null) {
      deque.offerLast(root);
    }
    while (!deque.isEmpty()) {
      int size = deque.size();
      while (size-- > 0) {
        //先交换
        TreeNode node = deque.poll();
        swap(node);
        if (node.left != null) {
          deque.offer(node.left);
        }
        if (node.right != null) {
          deque.offer(node.right);
        }
      }
    }
    return root;
  }

  public void swap(TreeNode root) {
    TreeNode temp = root.left;
    root.left = root.right;
    root.right = temp;
  }
}
