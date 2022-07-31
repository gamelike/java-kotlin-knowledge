package leetcode.competition;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author gjd
 */
public class SingleContent304 {
  //6132
  public int minimumOperations(int[] nums) {
    //每次移动出来最小值，并且所有值减去，当值小于0移除
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        queue.offer(nums[i]);
      }
    }
    int result = 0;
    while (!queue.isEmpty()) {
      int min = queue.poll();
      if (min == 0) {
        continue;
      }
      result++;
      //每个值都减去最小值
      PriorityQueue<Integer> temp = new PriorityQueue<>();
      while (!queue.isEmpty()) {
        int num = queue.poll() - min;
        if (num != 0) {
          temp.offer(num);
        }
      }
      queue = temp;
    }
    return result;
  }

  //6133 无关具体分数，无关排序，从1开始做等差数列，d=1,后一位的数字必然小于前面
  public int maximumGroups(int[] grades) {
    int length = grades.length;
    int i = 1;
    while (length - i >= 0) {
      length -= i;
      i++;
    }
    return i - 1;
  }

  //6134
  public int closestMeetingNode(int[] edges, int node1, int node2) {
    if (node1 == node2) {
      return node1;
    }
    int length = edges.length;
    int min = Integer.MAX_VALUE;
    int result = -1;
    for (int i = 0; i < edges.length; i++) {
      int current = i;
      //两个dfs
      boolean[] flags = new boolean[length];
      int left = dfs(node1, current, edges, length, 0, flags);
      if (left == -1) {
        continue;
      }
      flags = new boolean[length];
      int right = dfs(node2, current, edges, length, 0, flags);
      if (right == -1) {
        continue;
      }
      int temp = Math.max(left, right);
      if (temp < min) {
        min = temp;
        result = i;
      }
    }
    return result;
  }

  //6135
  private int dfs(int start, int end, int[] edges, int length, int depth, boolean[] flags) {
    if (start == end) {
      return depth;
    }
    if (edges[start] != -1) {
      flags[edges[start]] = true;
      return dfs(edges[start], end, edges, length, depth + 1, flags);
    }
    return -1;
  }


  public int longestCycle(int[] edges) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < edges.length; i++) {
      int num = 0;
      Queue<Integer> queue = new LinkedList<>();
      queue.add(edges[i]);
    }
    return -1;
  }

}
