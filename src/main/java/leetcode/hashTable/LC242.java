package leetcode.hashTable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import org.junit.Test;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/valid-anagram 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC242 {

  public boolean isAnagram(String s, String t) {
    Map<Character, Integer> map = new HashMap<>();
    for (char ch : s.toCharArray()) {
      map.merge(ch, 1, (oldV, newV) -> oldV + newV);
    }

    for (char ch : t.toCharArray()) {
      if (map.containsKey(ch)) {
        map.put(ch, map.get(ch) - 1);
        if (map.get(ch) == 0) {
          map.remove(ch);
        }
      } else {
        return false;
      }
    }
    if (map.keySet().size() == 0) {
      return true;
    }
    return false;
  }

  @Test
  public void run() {
    System.out.println(isAnagram("anagram", "nagaram"));
  }
}
