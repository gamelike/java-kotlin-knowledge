package leetcode.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author gjd
 */
public class LC102 {
  //从上往下层序遍历
  public List<List<Integer>> levelOrder(TreeNode root) {
    Deque<TreeNode> deque = new LinkedList<>();
    if (root != null) {
      deque.offerFirst(root);
    }

    List<List<Integer>> result = new LinkedList<>();
    while (!deque.isEmpty()) {
      int size = deque.size();
      List<Integer> mid = new LinkedList<>();
      while (size-- > 0) {
        TreeNode cur = deque.pollFirst();
        mid.add(cur.val);
        if (cur.left != null) {
          deque.offerLast(cur.left);
        }
        if (cur.right != null) {
          deque.offerLast(cur.right);
        }
      }
      result.add(mid);
    }
    return result;
  }

  //从下往上层序遍历
  public List<List<Integer>> levelOrderBottom(TreeNode root) {
    List<List<Integer>> resultOrderBotton = new LinkedList<>();
    List<List<Integer>> result = levelOrder(root);
    for (int i = result.size() - 1; i >= 0; i--) {
      resultOrderBotton.add(result.get(i));
    }
    return resultOrderBotton;
  }

  //返回右边
  public List<Integer> rightSideView(TreeNode root) {
    Deque<TreeNode> deque = new LinkedList<>();
    if (root != null) {
      deque.offerFirst(root);
    }

    List<Integer> result = new LinkedList<>();
    while (!deque.isEmpty()) {
      int size = deque.size();
      for (int i = 0; i < size; i++) {
        TreeNode cur = deque.pollFirst();
        if (cur.left != null) {
          deque.offerLast(cur.left);
        }
        if (cur.right != null) {
          deque.offerLast(cur.right);
        }
        if (size - 1 == i) {
          result.add(cur.val);
        }
      }
    }
    return result;
  }

  //平均值  637. 二叉树的层平均值
  public List<Double> averageOfLevels(TreeNode root) {
    Deque<TreeNode> deque = new LinkedList<>();
    if (root != null) {
      deque.offerFirst(root);
    }

    List<Double> result = new LinkedList<>();
    while (!deque.isEmpty()) {
      int size = deque.size();
      double num = 0.0;
      for (int i = 0; i < size; i++) {
        TreeNode cur = deque.pollFirst();
        if (cur.left != null) {
          deque.offerLast(cur.left);
        }
        if (cur.right != null) {
          deque.offerLast(cur.right);
        }
        num += cur.val;
      }
      result.add(num / size);

    }
    return result;
  }

  //515. 在每个树行中找最大值
  public List<Integer> largestValues(TreeNode root) {
    Deque<TreeNode> deque = new LinkedList<>();
    if (root != null) {
      deque.offerFirst(root);
    }
    List<Integer> result = new LinkedList<>();
    while (!deque.isEmpty()) {
      int size = deque.size();
      int max = Integer.MIN_VALUE;
      while (size-- > 0) {
        TreeNode cur = deque.pollFirst();
        if (cur.left != null) {
          deque.offerLast(cur.left);
        }
        if (cur.right != null) {
          deque.offerLast(cur.right);
        }
        max = Math.max(max, cur.val);
      }
      result.add(max);
    }
    return result;
  }

  //116. 填充每个节点的下一个右侧节点指针
  public Node116 connect(Node116 root) {
    Deque<Node116> deque = new LinkedList<>();
    if (root != null) {
      deque.offerFirst(root);
    }
    while (!deque.isEmpty()) {
      int size = deque.size();
      int max = Integer.MIN_VALUE;
      Node116 curPre = deque.pollFirst();
      if (curPre.left != null) {
        deque.offerLast(curPre.left);
      }
      if (curPre.right != null) {
        deque.offerLast(curPre.right);
      }
      for (int i = 1; i <= size - 1; i++) {
        Node116 cur = deque.pollFirst();
        if (cur.left != null) {
          deque.offerLast(cur.left);
        }
        if (cur.right != null) {
          deque.offerLast(cur.right);
        }
        curPre.next = cur;
        curPre = cur;
      }
      curPre.next = null;
    }
    return root;
  }

  //104. 二叉树的最大深度
  public int maxDepth(TreeNode root) {
    Deque<TreeNode> deque = new LinkedList<>();
    if (root != null) {
      deque.offerFirst(root);
    }
    int result = 0;
    while (!deque.isEmpty()) {
      int size = deque.size();
      while (size-- > 0) {
        TreeNode cur = deque.pollFirst();
        if (cur.left != null) {
          deque.offerLast(cur.left);
        }
        if (cur.right != null) {
          deque.offerLast(cur.right);
        }
      }
      result++;
    }
    return result;
  }

  //111. 二叉树的最小深度
  public int minDepth(TreeNode root) {
    Deque<TreeNode> deque = new LinkedList<>();
    if (root != null) {
      deque.offerFirst(root);
    }
    int min = 0;
    while (!deque.isEmpty()) {
      int size = deque.size();
      min++;
      while (size-- > 0) {
        TreeNode cur = deque.pollFirst();
        if (cur.left != null) {
          deque.offerLast(cur.left);
        }
        if (cur.right != null) {
          deque.offerLast(cur.right);
        }
        if (cur.left==null && cur.right==null){
          return min;
        }
      }
    }
    return min;
  }
}

class Node116 {
  public int val;
  public Node116 left;
  public Node116 right;
  public Node116 next;

  public Node116() {
  }

  public Node116(int _val) {
    val = _val;
  }

  public Node116(int _val, Node116 _left, Node116 _right, Node116 _next) {
    val = _val;
    left = _left;
    right = _right;
    next = _next;
  }
};
