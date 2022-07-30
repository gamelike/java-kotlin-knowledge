package leetcode.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author gjd
 */
public class LC429 {
  public List<List<Integer>> levelOrder(Node root) {
    Deque<Node> deque = new LinkedList<>();
    if (root != null) {
      deque.offerLast(root);
    }
    List<List<Integer>> result = new LinkedList<>();
    while (!deque.isEmpty()) {
      List<Integer> mid = new LinkedList<>();
      int size = deque.size();
      while (size-- > 0) {
        Node cur = deque.pollFirst();
        List<Node> childern = cur.children;
        if (childern != null) {
          for (int k = 0; k < childern.size(); k++) {
            deque.offerLast(childern.get(k));
          }
        }
        mid.add(cur.val);
      }
      result.add(mid);
    }
    return result;
  }
}

class Node {
  public int val;
  public List<Node> children;

  public Node() {
  }

  public Node(int _val) {
    val = _val;
  }

  public Node(int _val, List<Node> _children) {
    val = _val;
    children = _children;
  }
};
