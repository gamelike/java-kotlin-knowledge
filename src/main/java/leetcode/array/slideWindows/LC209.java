package leetcode.array.slideWindows;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0
 * 。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC209 {

  public int minSubArrayLen(int target, int[] nums) {
    int result = Integer.MAX_VALUE;
    int subLength = 0;
    int sum = 0;
    int slowIndex = 0;
    for (int quickIndex = 0; quickIndex < nums.length; quickIndex++) {
      sum += nums[quickIndex];
      subLength++;
      while (sum >= target) { //移动慢指针  多次移动慢指针
        result = Math.min(result, subLength);
        //处理慢指针移动导致的一系列变化
        sum -= nums[slowIndex++];
        subLength--;
      }
    }
    return result == Integer.MAX_VALUE ? 0 : result;
  }


  public int minSubArrayLen2(int target, int[] nums) {
    int result = Integer.MAX_VALUE;
    int subLength;
    for (int i = 0; i < nums.length; i++) {
      int sum = 0;
      for (int j = i; j < nums.length; j++) {
        sum = sum + nums[j];  //每次加上更新的值
        if (sum >= target) {
          subLength = j - i + 1;
          result = Math.min(result, subLength);
          break;
        }
      }
    }
    return result == Integer.MAX_VALUE ? 0 : result;
  }
}
