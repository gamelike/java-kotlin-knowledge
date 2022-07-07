package leetcode.hashTable;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LC18 {

  public List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> result = new LinkedList<>();
    Arrays.sort(nums);
    for (int i = 0; i < nums.length; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      if (nums[i] > target && (nums[i] > 0 || target > 0)) {
        break;
      }
      for (int j = i + 1; j < nums.length; j++) {
        if (j > 0 && nums[j - 1] == nums[j]) {
          continue;
        }
        if (nums[j] + nums[i] > target && (nums[i] + nums[j] > 0 || target > 0)) {
          break;
        }
        int left = j + 1;
        int right = nums.length - 1;
        while (left < right) {
          long sum = nums[i] + nums[j] + nums[left] + nums[right];
          if (sum > target) {
            right--;
          } else if (sum < target) {
            left++;
          } else {
            result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
            while (left < right && nums[left + 1] == nums[left]) {
              left++;
            }
            while (left < right && nums[right - 1] == nums[right]) {
              right--;
            }
            left++;
            right--;
          }
        }
      }
    }
    return result;
  }
}
