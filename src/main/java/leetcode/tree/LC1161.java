package leetcode.tree;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @author gjd
 */
public class LC1161 {
  public int maxLevelSum(TreeNode root) {
    Deque<TreeNode> deque = new LinkedList<>();
    if (root != null) {
      deque.offerFirst(root);
    }
    int max = Integer.MIN_VALUE;
    int depth = -1; //默认层
    int currentDepth = -1;
    while (!deque.isEmpty()) {
      int size = deque.size();
      int num = 0;
      while (size-- > 0) {
        TreeNode cur = deque.pollFirst();
        currentDepth++; //当前层深度
        if (cur.left != null) {
          deque.offerLast(cur.left);
        }
        if (cur.right != null) {
          deque.offerLast(cur.right);
        }
        num += cur.val;
      }
      if (max < num) {
        max = num;
        depth = currentDepth;
      }

    }
    return depth;
  }
}
