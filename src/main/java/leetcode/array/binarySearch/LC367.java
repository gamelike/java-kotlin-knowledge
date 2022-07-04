package leetcode.array.binarySearch;

/**
 * @author gjd
 */
public class LC367 {
  public boolean isPerfectSquare(int num) {
    int left = 0;
    int right = num / 2 + 1;
    while (left <= right) {
      int mid = (left + right) / 2;
      long midSquare = (long) mid * mid;
      if (midSquare == num) {
        return true;
      } else if (midSquare < num) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return false;
  }
}
