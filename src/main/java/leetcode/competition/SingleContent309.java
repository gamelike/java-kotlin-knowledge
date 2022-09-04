package leetcode.competition;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gjd3
 */
public class SingleContent309 {
  public boolean checkDistances(String s, int[] distance) {
    char[] chars = s.toCharArray();
    Map<Character, Integer> storeChar = new HashMap<>();
    for (int i = 0; i < chars.length; i++) {
      if (storeChar.containsKey(chars[i])) {
        int eralyCharIndex = storeChar.get(chars[i]);
        if (distance[chars[i] - 'a'] != i - eralyCharIndex - 1) {
          return false;
        }
      } else {
        storeChar.put(chars[i], i);
      }
    }
    return true;
  }

  public int numberOfWays(int startPos, int endPos, int k) {
    return 0;
  }

  public int longestNiceSubarray(int[] nums) {
    int res = 0;
    for (int i = 0; i < nums.length; i++) {
      int bits = 0;
      for (int j = i + 1; j < nums.length && (bits & nums[j]) == 0; j++) {
        bits = bits | nums[j];
        res = Math.max(j - i, res);
      }
    }
    return res;
  }

  public int longestNiceSubarray2(int[] nums) {
    int in = 0;
    int slowIndex = 0;
    int quickIndex = 0;
    int res = 0;
    int n = nums.length;
    while (quickIndex < n) {
      int cur = nums[quickIndex];
      while (slowIndex < quickIndex && (in & cur) != 0) {
        in = in ^ nums[slowIndex];
        slowIndex++;
      }
      res = Math.max(res, quickIndex - slowIndex + 1);
      in = in | cur;
      quickIndex++;
    }
    return res;
  }
}
