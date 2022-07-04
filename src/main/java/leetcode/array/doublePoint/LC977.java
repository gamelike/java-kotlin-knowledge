package leetcode.array.doublePoint;

import java.util.Arrays;
import org.junit.Test;

/**
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 */
public class LC977 {

  public int[] sortedSquares(int[] nums) {
    int left = 0;
    int right = nums.length - 1;
    int pos = nums.length - 1; //负责赋值的指针位置
    int[] ant = new int[nums.length];
    for (; left <= right; pos--) {
      if (nums[left] * nums[left] < nums[right] * nums[right]) {
        ant[pos] = nums[right] * nums[right];
        right--;
      } else {
        ant[pos] = nums[left] * nums[left];
        left++;
      }
    }
    return ant;
  }

  @Test
  public void run() {
    System.out.println(Arrays.toString(sortedSquares(new int[]{
        -7, -3, 2, 3, 11})));

  }
}
