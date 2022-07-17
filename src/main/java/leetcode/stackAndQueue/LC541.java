package leetcode.stackAndQueue;

/**
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
 * <p>
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reverse-string-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gjd
 */
public class LC541 {
  public String reverseStr(String s, int k) {
    char[] chars = s.toCharArray();

    for (int i = 0; i < chars.length; i += 2 * k) {
      int left = i;
      int right = Math.min(chars.length - 1, left + k - 1); //寻找最后一组，如果不满足到k，则取length长度
      while (left < right) {
        chars[left] ^= chars[right]; //临时存在left中
        chars[right] ^= chars[left]; //取出right
        chars[left] ^= chars[right]; //取出left
        left++;
        right--;
      }
    }
    return new String(chars);
  }
}
