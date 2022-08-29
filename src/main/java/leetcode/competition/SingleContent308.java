package leetcode.competition;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * @author gjd3
 */
public class SingleContent308 {


  public int[] answerQueries(int[] nums, int[] queries) {
    Arrays.sort(nums);
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < queries.length; i++) {
      //临时最大值
      int sm = 0;
      for (int j = 0; j < nums.length; j++) {
        sm += nums[j];
        if (sm > queries[i]) {
          res.add(j - 1 + 1);
          break;
        }
        if (sm == queries[i]) {
          res.add(j + 1);
          break;
        }
      }
      if (sm < queries[i]) {
        res.add(nums.length - 1 + 1);
      }
    }
    return res.stream().mapToInt(Integer::intValue).toArray();
  }


  @Test
  public void answerQueriesRunTest() {
    answerQueries(new int[]{736411, 184882, 914641, 37925, 214915}
        , new int[]{331244, 273144, 118983, 118252, 305688, 718089, 665450});
  }

  public String removeStars(String s) {
    Deque<Character> deque = new ArrayDeque<>();
    for (char ch : s.toCharArray()) {
      if (ch == '*') {
        deque.removeLast();
      } else {
        deque.addLast(ch);
      }
    }
    StringBuilder stb = new StringBuilder();
    while (!deque.isEmpty()) {
      stb.append(deque.pollFirst());
    }
    return stb.toString();
  }

  @Test
  public void removeStarsRunTest() {
    removeStars("leet**cod*e");
  }
}
