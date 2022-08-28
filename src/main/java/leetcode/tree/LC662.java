package leetcode.tree;

import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gjd3
 */
public class LC662 {
  public int widthOfBinaryTree(TreeNode root) {
    //遍历
    int res = 0;
    List<Pair<TreeNode, Integer>> arr = new ArrayList<>();
    int index = 1;
    arr.add(new Pair<>(root, index));
    while (!arr.isEmpty()) {
      List<Pair<TreeNode, Integer>> tmp = new ArrayList<>();
      for (Pair<TreeNode, Integer> element : arr) {
        TreeNode curNode = element.getKey();
        index = element.getValue();
        if (curNode.left != null) {
          tmp.add(new Pair<>(curNode.left, 2 * index));
        }
        if (curNode.right != null) {
          tmp.add(new Pair<>(curNode.right, 2 * index + 1));
        }
      }
      res = Math.max(res, arr.get(arr.size() - 1).getValue() - arr.get(0).getValue() + 1);
      arr = tmp;
    }
    return res;
  }
}
