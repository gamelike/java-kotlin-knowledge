package leetcode.binarySearch;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author gjd
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/search-insert-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC35 {
  public int searchInsert(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {
      if (left > right) {
        return left;
      }
      int mid = (right + left) / 2;
      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return right + 1;
  }

  @Test
  public void run() {
    Assert.assertEquals(searchInsert(new int[]{1, 3, 5, 6}, 5), 2);
    Assert.assertEquals(searchInsert(new int[]{1, 3, 5, 6}, 2), 1);
    Assert.assertEquals(searchInsert(new int[]{1, 3, 5, 6}, 7), 4);
  }
}