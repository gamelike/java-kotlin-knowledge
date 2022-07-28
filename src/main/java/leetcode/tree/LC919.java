package leetcode.tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class LC919 {

  Queue<TreeNode> insertNodes;
  TreeNode root;

  public LC919(TreeNode root) {
    this.root = root;
    Queue<TreeNode> treeNodes = new ArrayDeque<>();
    insertNodes = new ArrayDeque<>();
    treeNodes.offer(root);
    while (!treeNodes.isEmpty()) {
      TreeNode node = treeNodes.poll();
      if (node.left == null || node.right == null) {
        insertNodes.offer(node);
      }
      if (node.left != null) {
        treeNodes.offer(node.left);
      }
      if (node.right != null) {
        treeNodes.offer(node.right);
      }
    }
  }

  public int insert(int val) {
    TreeNode node = new TreeNode(val);
    insertNodes.offer(node);//添加在最后
    TreeNode insertNode = insertNodes.peek(); //获取到第一个
    if (insertNode.left == null) {
      insertNode.left = node;
    } else {  //右节点，链接一下，删除节点，此节点左右都绑定了值
      insertNode.right = node;
      insertNodes.poll();
    }
    return insertNode.val;
  }

  public TreeNode get_root() {
    return root;
  }

}
