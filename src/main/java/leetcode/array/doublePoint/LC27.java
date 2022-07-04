package leetcode.array.doublePoint;

/**
 * @author gjd
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * <p>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC27 {
  public int removeElement(int[] nums, int val) {
    int slowIndex = 0;
    for (int quickIndex : nums) {
      if (quickIndex != val) { //不相等时，慢指针移动一次
        nums[slowIndex++] = quickIndex;
      }
    }
    return slowIndex;
  }

  public int removeElement1(int[] nums, int val) {
    int size = nums.length;
    for (int i = 0; i < size; i++) {
      if (val == nums[i]) {
        for (int j = i + 1; j < size; j++) {
          nums[j - 1] = nums[j];
        }
        i--;
        size--;
      }
    }
    return size;
  }

}
