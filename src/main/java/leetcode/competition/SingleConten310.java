package leetcode.competition;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author gjd3
 */
public class SingleConten310 {
  public int mostFrequentEven(int[] nums) {
    TreeMap<Integer, Integer> map = new TreeMap(new Comparator() {
      @Override
      public int compare(Object o1, Object o2) {
        return (Integer) o2 - (Integer) o1;
      }
    });

    int res = Integer.MAX_VALUE;
    int count = -1;
    for (Integer num : nums) {
      if (num % 2 == 0) {
        map.put(num, map.getOrDefault(num, 0) + 1);
        if (map.get(num) == count && num <= res) {
          count = map.get(num);
          res = num;
        } else if (map.get(num) > count) {
          count = map.get(num);
          res = num;
        }
      }
    }
    return res == Integer.MAX_VALUE ? -1 : res;
  }

  public int partitionString(String s) {

    int count = 0;
    char[] chars = s.toCharArray();
    Set<Character> set = new HashSet<>();
    for (char ch : chars) {
      if (set.add(ch)) {

      } else {
        count++;
        set = new HashSet<>();
        set.add(ch);
      }
    }
    return count + 1;
  }

  public int minGroups(int[][] intervals) {

    Arrays.sort(intervals, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
      }
    });
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1 - o2;
      }
    });
    //由于之前拍过序，故每次遍历都是求的最小的可以放置
    for (int i = 0; i < intervals.length; i++) {
      //如果可以放置 弹出更新
      if (!priorityQueue.isEmpty() && priorityQueue.peek() < intervals[i][0]) {
        priorityQueue.poll();
        priorityQueue.offer(intervals[i][1]);
      } else {
        priorityQueue.offer(intervals[i][1]);
      }
    }
    return priorityQueue.size();
  }

  @Test
  public void run3() {
    minGroups(new int[][]{{935387, 971021}, {866648, 882725}, {617324, 668847}, {207490, 229649}, {678354, 738786}});
  }

  public int specialArray(int[] nums) {
    Arrays.sort(nums);
    int n = nums.length;
//    if (nums[0] >= n) {
//      return n;
//    }
    for (int i = 1; i < n; i++) {
      if (nums[n - i] >= i && nums[n - i - 1] < i) {
        return i;
      }
    }
    return -1;
  }
}
