package leetcode.string;

/**
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 * @author gjd
 */
public class LC05 {
  public String replaceSpace(String s) {
    char[] chars = new char[s.toCharArray().length * 3];
    char[] sChars = s.toCharArray();
    int index = chars.length - 1;
    int right = sChars.length-1;

    while (right >= 0) {
      if (sChars[right] == ' ') {
        chars[index--] = '0';
        chars[index--] = '2';
        chars[index--] = '%';
      } else {
        chars[index--] = sChars[right];
      }
      right--;
    }
    return new String(chars, index + 1, chars.length - index - 1);

  }
}
