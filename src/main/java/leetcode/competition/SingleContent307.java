package leetcode.competition;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * @author gjd3
 */
public class SingleContent307 {
  public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
    int people = energy.length;
    int curEnergy = initialEnergy;
    int curExperience = initialExperience;
    int res = 0;
    for (int i = 0; i < people; i++) {
      if (curEnergy < energy[i] + 1) {
        int gap = Math.abs(curEnergy - energy[i] - 1);
        res += gap;
        curEnergy += gap;
        System.out.println(i + ":训练" + res + "能量");
      }
      curEnergy = curEnergy - energy[i];

      if (curExperience < experience[i] + 1) {
        int gap = Math.abs(curExperience - experience[i] - 1);
        res += gap;
        curExperience += gap;
        System.out.println(i + ":训练" + res + "经验");
      }
      curExperience = curExperience + experience[i];
    }
    return res;
  }

  public String largestPalindromic(String num) {
    int[] cns = new int[10];
    for (char ch : num.toCharArray()) {
      cns[ch - '0']++;
    }
    StringBuilder ans = new StringBuilder();
    for (int i = 9; i >= 0; i--) {
      if (i == 0 && ans.isEmpty()) {
        break;
      }
      int t = cns[i] / 2;
      for (int j = 1; j <= t; j++) {
        ans.append(i);
      }
      cns[i] = cns[i] - t * 2;
    }

    StringBuilder ans2 = new StringBuilder(ans);
    for (int i = 9; i >= 0; i--) {
      if (cns[i] != 0) {
        ans.append(i);
        break;
      }
    }
    return ans.append(ans2.reverse()).toString();
  }

  public int amountOfTime(TreeNode root, int start) {
    Map<Integer, List<Integer>> adj = new HashMap<>();
    Deque<TreeNode> deque = new ArrayDeque<>();
    deque.offerLast(root);
    while (!deque.isEmpty()) {
      int size = deque.size();
      for (int i = 0; i < size; i++) {
        TreeNode cur = deque.pollFirst();
        if (cur.left != null) {
          adj.computeIfAbsent(cur.val, integer -> new ArrayList<>()).add(cur.left.val);
          adj.computeIfAbsent(cur.left.val, integer -> new ArrayList<>()).add(cur.val);
          deque.offerLast(cur.left);
        }
        if (cur.right != null) {
          adj.computeIfAbsent(cur.val, integer -> new ArrayList<>()).add(cur.right.val);
          adj.computeIfAbsent(cur.right.val, integer -> new ArrayList<>()).add(cur.val);
          deque.offerLast(cur.right);
        }
      }
    }
    //bfs 层序遍历图
    Deque<int[]> nodeDeque = new ArrayDeque<>();
    Set<Integer> visit = new HashSet<>();
    visit.add(start);
    nodeDeque.offerLast(new int[]{start, 0});  //0代表当前节点的深度
    int depth = 0;
    while (!nodeDeque.isEmpty()) {
      int[] cur = nodeDeque.pollFirst();
      for (int node : adj.getOrDefault(cur[0], new ArrayList<>())) {
        if (!visit.contains(node)) {
          nodeDeque.offerLast(new int[]{node, cur[1] + 1}); //记录层数
          visit.add(node);
          //遍历邻接表 获取到深度最大的值
          depth = Math.max(depth, cur[1] + 1);
        }
      }
    }
    return depth;
  }

  public long kSum(int[] nums, int k) {
    allSub(nums, 0, 0);
    Collections.sort(result, Comparator.reverseOrder());
    System.out.println(result);
    return result.get(k - 1);
  }

  List<Long> result = new LinkedList<>();

  public void allSub(int[] nums, int i, long res) {
    if (i == nums.length) {
      result.add(res);
      return;
    }
    allSub(nums, i + 1, res + nums[i]);
    allSub(nums, i + 1, res);
  }
}
