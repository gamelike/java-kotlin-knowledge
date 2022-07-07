package leetcode.hashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LC15 {

  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> result = new LinkedList<>();
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > 0) { //剪纸
        return result;
      }
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      int left = i + 1;
      int right = nums.length - 1;
      while (left < right) {
        int sum = nums[i] + nums[left] + nums[right];
        if (nums[i] + nums[left] + nums[right] == 0) {
          result.add(Arrays.asList(nums[i], nums[left], nums[right]));
          while (right > left && nums[right] == nums[right - 1]) {
            right--;
          }
          while (right > left && nums[left + 1] == nums[left]) {
            left++;
          }
          left++;
          right--;
        } else if (sum > 0) {
          right--;
        } else {
          left++;
        }
      }
    }
    return result;
  }
}
