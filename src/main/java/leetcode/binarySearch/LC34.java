package leetcode.binarySearch;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author gjd
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC34 {
  public int[] searchRange(int[] nums, int target) {
    //findLeft
    int left = binarySearch(nums, target) + 1; //小于target的元素+1即可
    //findRight
    int right = binarySearch(nums, target + 1);  //找到比target+1还小的元素，就是最右边
    if (left <= right && nums[right] == target && nums[left] == target) {
      return new int[]{left, right};
    }
    return new int[]{-1, -1};
  }

  //寻找元素小于 target的值 找最接近target的元素
  int binarySearch(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    int ans = -1;
    while (left <= right) {
      int mid = (right + left) / 2;
      if (nums[mid] < target) {
        left = mid + 1;
        ans = mid;
      } else {
        right = mid - 1;
      }
    }
    return ans;
  }

  @Test
  public void run() {
//    System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
//    System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));
//    System.out.println(Arrays.toString(searchRange(new int[]{1, 1, 2}, 1)));
    System.out.println(Arrays.toString(searchRange(new int[]{}, 1)));
    Assert.assertEquals(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)), Arrays.toString(new int[]{3, 4}));
    Assert.assertEquals(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)), Arrays.toString(new int[]{-1, -1}));
    Assert.assertEquals(Arrays.toString(searchRange(new int[]{}, 0)), Arrays.toString(new int[]{-1, -1}));
  }
}