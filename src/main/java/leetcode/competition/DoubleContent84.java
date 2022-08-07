package leetcode.competition;


import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author gjd
 */
public class DoubleContent84 {
  public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
    Map<Integer, Integer> map = new TreeMap<>(Comparator.comparingInt(o -> o));
    for (int i = 0; i < items1.length; i++) {
      map.put(items1[i][0], items1[i][1]);
    }
    for (int i = 0; i < items2.length; i++) {
      if (map.containsKey(items2[i][0])) {
        map.put(items2[i][0], map.get(items2[i][0]) + items2[i][1]);
      } else {
        map.put(items2[i][0], items2[i][1]);
      }
    }
    //根据value排序
    List<List<Integer>> result = new LinkedList<>();
    for (Integer value : map.keySet()) {

      List<Integer> temp = Arrays.asList(value, map.get(value));
      result.add(temp);
    }
    return result;
  }

  public long countBadPairs(int[] nums) {
    long count = 0;
    int length = nums.length;

    Map<Integer, Integer> map = new HashMap<>();//维护非坏数对的值
    long ans = 1L * length * (length - 1) / 2;
    //对于value，每个
    for (int i = 0; i < length; i++) {
      int temp = nums[i] - i;
      int noError = map.getOrDefault(temp, 0);
      ans -= noError;
      map.put(temp, noError + 1);
    }
    return ans;
  }


  public long taskSchedulerII(int[] tasks, int space) {
    int length = tasks.length;
    long day = 0L;
    Map<Integer, Long> map = new HashMap<>();
    for (int i = 0; i < length; i++) {
      //day - map.getOrDeault(task[i],Int.MIN) >= space 更新
      Long temp = map.getOrDefault(tasks[i], Long.MIN_VALUE);
      if (day >= space+temp) {
        day++;
        map.put(tasks[i], day);
      } else {
        // day -               < space  更新值  day = oldDay+ space     day++
        day = temp + space;
        day++;
        map.put(tasks[i], day);
      }
    }
    return day;
  }


}
