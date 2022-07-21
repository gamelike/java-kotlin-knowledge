package leetcode.tree;

import java.util.LinkedList;
import java.util.List;

public class LC144 {


  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> result = new LinkedList<>();
    preorder(root, result);
    return result;
  }

  public void preorder(TreeNode root, List<Integer> result) {
    if (root == null) {
      return;
    }
    result.add(root.val);
    preorder(root.left, result);
    preorder(root.right, result);
  }

  public void inorder(TreeNode root, List<Integer> result) {
    if (root == null) {
      return;
    }
    inorder(root.left, result);
    result.add(root.val);
    inorder(root.right, result);
  }


  public void postorder(TreeNode root, List<Integer> result) {
    if (root == null) {
      return;
    }
    inorder(root.left, result);
    inorder(root.right, result);
    result.add(root.val);
  }

}

class TreeNode {

  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {
  }

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}
