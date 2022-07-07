package leetcode.hashTable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
 * <p>
 * 0 <= i, j, k, l < n nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/4sum-ii 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC454 {

  public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int num1 : nums1) {
      for (int num2 : nums2) {
        int tmp = num1 + num2;
        map.put(tmp, map.getOrDefault(tmp, 0) + 1);
      }
    }
    int count = 0;
    for (int num3 : nums3) {
      for (int num4 : nums4) {
        count += map.getOrDefault(-(num3 + num4), 0);
      }
    }

    return count;
  }
}
