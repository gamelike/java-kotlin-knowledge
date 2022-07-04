package leetcode.array.doublePoint;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author gjd
 * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
 * <p>
 * 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么 nums 的前 k 个元素应该保存最终结果。
 * <p>
 * 将最终结果插入 nums 的前 k 个位置后返回 k 。
 * <p>
 * 不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-duplicates-from-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC26 {
  //重复元素必然相邻
  public int removeDuplicates(int[] nums) {
    int slowIndex = 1;
    for (int fastIndex = 1; fastIndex < nums.length; fastIndex++) {
      if (nums[fastIndex - 1] != nums[fastIndex]) {
        nums[slowIndex++] = nums[fastIndex];
      }
    }
    return slowIndex;
  }

  @Test
  public void run() {
    Assert.assertEquals(removeDuplicates(new int[]{1, 1, 2}), 2);
  }
}
