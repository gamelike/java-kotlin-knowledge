package leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author gjd
 */
public class LC623 {
  public TreeNode addOneRow(TreeNode root, int val, int depth) {
    //层序遍历 找到指定节点
    //左子树向左 右子树向右
    //bfs
    if (depth == 1) {
      TreeNode temp = new TreeNode(val);
      temp.left = root;
      return temp;
    }
    Deque<TreeNode> deque = new ArrayDeque<>();
    if (root != null) {
      deque.offerLast(root);
    }
    int preDepth = 0;
    while (!deque.isEmpty()) {
      //计算层数
      int size = deque.size();
      if (preDepth == depth) { //取到前一层
        break;
      }
      while (size-- > 0) {
        //弹出节点 入库
        TreeNode temp = deque.pollFirst();
        if (temp.left != null) {
          deque.offerLast(temp.left);
        }
        if (temp.right != null) {
          deque.offerLast(temp.right);
        }
      }
      preDepth++;
    }
    int size = deque.size();
    while (size-- > 0) {
      TreeNode temp = deque.pollFirst();
      TreeNode left = new TreeNode(val);
      TreeNode right = new TreeNode(val);
      if (temp.left != null) {
        left.left = temp.left;

      }
      if (temp.right != null) {
        right.right = temp.right;
      }
      temp.left = left;
      temp.right = right;
    }
    return root;
  }
}
