package leetcode.day;

import org.junit.Test;

/**
 * 索引从0开始长度为N的数组A，包含0到N - 1的所有整数。找到最大的集合S并返回其大小，其中 S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }且遵守以下的规则。
 * <p>
 * 假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，之后是A[A[A[i]]]... 以此类推，不断添加直到S出现重复的元素。
 * <p>
 * A中不含有重复的元素。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/array-nesting
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gjd
 */
public class LC565 {
  //由于a中不含有重复元素，因此不存在交叉情况，即数组不可能存在两个环相交，可以将已经遍历过的元素进行标记
  public int arrayNesting(int[] nums) {
    int result = 0;
    for (int i = 0; i < nums.length; i++) {
      int num = i;
      int ant = 0;
      while (nums[num] != -1) {
        int temp = nums[num];
        nums[num] = -1;
        num = temp;
        ant = ant + 1;
      }
      result = Math.max(result, ant);
    }
    return result;
  }

  @Test
  public void run(){
    System.out.println(arrayNesting(new int[]{1,0,3,4,2}));
  }

  public int dfs(int[] nums, int index) {
    if (nums[index] == -1) {
      return 0;
    }
    int num = nums[index];
    nums[index] = -1;
    return 1 + dfs(nums, num);
  }
}
