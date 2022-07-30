package leetcode.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class LC144 {

  //前序遍历递归
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> result = new LinkedList<>();
    preorder(root, result);
    return result;
  }

  //前序遍历迭代
  List<Integer> preOrderIterator(TreeNode root) {
    Stack<TreeNode> st = new Stack<>();
    if (root != null) {
      st.push(root);
    }
    List<Integer> result = new LinkedList<>();
    while (!st.empty()) {
      TreeNode now = st.pop();
      result.add(now.val);
      if (now.right != null) {
        st.push(now.right);
      }
      if (now.left != null) {
        st.push(now.left);
      }
    }
    return result;
  }

  //前序遍历
  public void preorder(TreeNode root, List<Integer> result) {
    if (root == null) {
      return;
    }
    result.add(root.val);
    preorder(root.left, result);
    preorder(root.right, result);
  }

  //中序遍历
  public void inorder(TreeNode root, List<Integer> result) {
    if (root == null) {
      return;
    }
    inorder(root.left, result);
    result.add(root.val);
    inorder(root.right, result);
  }


  //后序遍历
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
