package leetcode.array.doublePoint;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 */
public class LC283 {

  public void moveZeroes(int[] nums) {
    int slowIndex = 0;
    for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
      if (nums[fastIndex] != 0) {
        swap(nums, slowIndex++, fastIndex);
//        nums[slowIndex++] = nums[fastIndex];
      }
    }
//    for (; slowIndex < nums.length; slowIndex++) {
//      nums[slowIndex] = 0;
//    }
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
}
