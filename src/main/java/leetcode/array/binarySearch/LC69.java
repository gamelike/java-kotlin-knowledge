package leetcode.array.binarySearch;

import org.junit.Test;

/**
 * @author gjd
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * <p>
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * <p>
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sqrtx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC69 {
  public int mySqrt(int x) {
    int left = 0;
    int right = x / 2 + 1;
    int ant = -1;
    while (left <= right) {
      int mid = (left + right) / 2;
      long midSquare = (long)mid * mid;
      if (midSquare <= x) {  //合并左侧和等于
        ant = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return ant;
  }

  @Test
  public void run(){
    System.out.println(mySqrt(2147395599));
  }
}
