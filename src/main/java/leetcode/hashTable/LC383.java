package leetcode.hashTable;

import java.util.Arrays;
import org.junit.Test;

/**
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * <p>
 * 如果可以，返回 true ；否则返回 false 。
 * <p>
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/ransom-note 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC383 {

  public boolean canConstruct(String ransomNote, String magazine) {
    int[] num = new int[26];
    for (char ch : magazine.toCharArray()) {
      num[ch - 'a']++;
    }
    for (char ch : ransomNote.toCharArray()) {
      if (num[ch - 'a'] == 0) {
        return false;
      } else {
        num[ch - 'a']--;
      }
    }
    return true;

  }

  @Test
  public void run() {
    System.out.println(canConstruct("aa", "aab"));
    System.out.println("aab".contains("aa"));
  }
}
